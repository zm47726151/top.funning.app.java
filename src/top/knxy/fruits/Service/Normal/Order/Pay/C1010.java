package top.knxy.fruits.Service.Normal.Order.Pay;

import com.google.gson.Gson;
import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.Config.S;
import top.knxy.fruits.DataBase.DAL.OrderDAL;
import top.knxy.fruits.DataBase.DAL.UserDAL;
import top.knxy.library.Vehicle.WeChat.Pay.OrderInfo;
import top.knxy.fruits.DataBase.Table.Order;
import top.knxy.fruits.DataBase.Table.User;
import top.knxy.library.BaseService;
import top.knxy.library.ServiceException;
import top.knxy.library.Utils.*;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.TreeMap;

public class C1010 extends BaseService {
    public static final String TAG = "Order.Pay.C1010";

    private final static double locationLat = 23.091333102548455;
    private final static double locationLng = 113.33717442876233;


    public String userId;
    public String id;
    public String note;
    public Address address;

    public static class Address {
        public String provinceName;
        public String cityName;
        public String countyName;
        public String detailInfo;
        public String telNumber;
        public String userName;
        public String nationalCode;
        public String postalCode;
    }


    @Override
    public void run() throws Exception {
        if (address == null) {
            ServiceUtils.createError(this, "没有地址");
            return;
        }
        if (TextUtils.isEmpty(address.userName)) {
            ServiceUtils.createError(this, "没有姓名");
            return;
        }
        if (TextUtils.isEmpty(address.telNumber)) {
            ServiceUtils.createError(this, "没有电话号码");
            return;
        }
        if (TextUtils.isEmpty(address.detailInfo)) {
            ServiceUtils.createError(this, "没有详细地址");
            return;
        }
        if (TextUtils.isEmpty(address.provinceName)) {
            ServiceUtils.createError(this, "没有区域");
            return;
        }
        if (TextUtils.isEmpty(address.cityName)) {
            ServiceUtils.createError(this, "没有城市");
            return;
        }
        if (TextUtils.isEmpty(address.provinceName)) {
            ServiceUtils.createError(this, "没有省份");
            return;
        }


        //查询订单
        SqlSession session = getSqlSession();
        OrderDAL mapper = session.getMapper(OrderDAL.class);

        Order order = new Order();
        order.setId(id);
        order.setUserId(userId);
        order = mapper.getOrderByUser(order);
        if (order == null) {

            throw new ServiceException("没有订单 id = " + id);
        }

        //计算运费
        String url = "http://api.map.baidu.com/geocoder/v2/?address=" +
                URLEncoder.encode(address.provinceName + address.provinceName + address.cityName + address.detailInfo, "UTF-8") +
                "&output=json&ak=tj3qu8wHTAFgQ3OmZbl8CLzTznki2VGR";
        LocationInfo locationInfo = new Gson().fromJson(WebUtils.get(url), LocationInfo.class);

        if (locationInfo.status != 0) {

            throw new ServiceException("get location fail. " + url);
        }


        double distance = LocationUtils.getDistance(
                locationInfo.result.location.lat,
                locationInfo.result.location.lng,
                locationLat,
                locationLng
        );
        distance = distance / 1000;

        LogUtils.i(TAG,String.format("price = %s , distance = %s , lat1 = %s , lng2 = %s , lat2 = %s , lng2 = %s",
                order.getPrice(), distance,
                locationInfo.result.location.lat, locationInfo.result.location.lng,
                locationLat, locationLng));

        String poster = "6";
        if (distance < 10 && new BigDecimal(order.getPrice()).compareTo(new BigDecimal(30)) >= 0) {
            poster = "0";
        }

        order.setNote(note);
        order.setPoster(poster);
        order.setAmount(new BigDecimal(poster).add(new BigDecimal(order.getPrice())).toString());

        order.setProvinceName(address.provinceName);
        order.setCityName(address.cityName);
        order.setCountyName(address.countyName);
        order.setDetailInfo(address.detailInfo);
        order.setTelNumber(address.telNumber);
        order.setUserName(address.userName);
        order.setNationalCode(address.nationalCode);
        order.setPostalCode(address.postalCode);

        int result = mapper.update(order);
        session.commit();
        if (result < 1) {

            throw new ServiceException("订单修改失败 order id = " + id);
        }

        //支付
        UserDAL dal = session.getMapper(UserDAL.class);
        User user = dal.getUser(userId);
        if (user == null) {

            throw new ServiceException("没有用户 user id = " + userId);
        }


        OrderInfo orderInfo;
        {
            TreeMap<Object, Object> map = new TreeMap<>();
            map.clear();
            map.put("appid", S.WeChat.appid);
            map.put("mch_id", S.WCPay.mchId);
            map.put("nonce_str", ServiceUtils.getUUid());
            map.put("sign_type", "MD5");
            map.put("body", "购买商品");
            map.put("out_trade_no", order.getId());
            map.put("total_fee", new BigDecimal(order.getAmount()).multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_UP));
            map.put("openid", user.getOpenId());
            map.put("spbill_create_ip", "39.106.114.227");
            map.put("notify_url", "https://fruits.knxy.top/pay/confirm");
            map.put("trade_type", "JSAPI");
            map.put("attach","normal");
            map.put("sign", ServiceUtils.getWXPaySignValue(map,S.WCPay.apiKey));

            String data = XmlUtils.mapToXmlStr(map, false);
            orderInfo = WebUtils.postXml("https://api.mch.weixin.qq.com/pay/unifiedorder", data, OrderInfo.class);
        }

        if (!"SUCCESS".equals(orderInfo.return_code)) {

            throw new ServiceException(String.format("code = %s, msg = %s, order id = %s",
                    orderInfo.return_code,
                    orderInfo.return_msg,
                    order.getId()));
        }

        if (!"SUCCESS".equals(orderInfo.result_code)) {

            throw new ServiceException(String.format("err_code = %s, err_code_des = %s, order id = %s",
                    orderInfo.err_code,
                    orderInfo.err_code_des,
                    order.getId()));
        }

        {
            TreeMap<Object, Object> map = new TreeMap<>();
            map.clear();
            map.put("appId", S.WeChat.appid);
            map.put("timeStamp", String.valueOf(new Date().getTime()));
            map.put("nonceStr", ServiceUtils.getUUid());
            map.put("package", "prepay_id=" + orderInfo.prepay_id);
            map.put("signType", "MD5");
            map.put("sign", ServiceUtils.getWXPaySignValue(map,S.WCPay.apiKey));

            map.remove("appId");
            this.data = map;
        }

        ServiceUtils.createSuccess(this);

    }

    public static class LocationInfo {


        public int status;
        public Result result;

        public static class Result {

            public Location location;
            public int precise;
            public int confidence;
            public int comprehension;
            public String level;

            public static class Location {
                public double lng;
                public double lat;
            }
        }
    }

}
