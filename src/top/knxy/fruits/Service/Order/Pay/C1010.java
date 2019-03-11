package top.knxy.fruits.Service.Order.Pay;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.Config.S;
import top.knxy.fruits.DataBase.DAL.LoginDAL;
import top.knxy.fruits.DataBase.DAL.OrderDAL;
import top.knxy.fruits.DataBase.MyBatisUtils;
import top.knxy.fruits.DataBase.Table.Order;
import top.knxy.fruits.DataBase.Table.User;
import top.knxy.fruits.Service.BaseService;
import top.knxy.fruits.Service.ServiceException;
import top.knxy.fruits.Utils.ServiceUtils;
import top.knxy.fruits.Utils.TextUtils;
import top.knxy.fruits.Utils.WebUtils;
import top.knxy.fruits.Utils.XmlUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.TreeMap;

public class C1010 extends BaseService {

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

        SqlSession session = MyBatisUtils.getSession();
        OrderDAL mapper = session.getMapper(OrderDAL.class);

        String poster = "6";
        Order order = new Order();
        order.setId(id);
        order.setUserId(userId);
        order = mapper.getOrderByUser(order);
        if (order == null) {
            session.close();
            throw new ServiceException("没有订单 id = " + id);
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
            session.close();
            throw new ServiceException("订单修改失败 order id = " + id);
        }

        LoginDAL dal = session.getMapper(LoginDAL.class);
        User user = dal.getUser(userId);
        if (user == null) {
            session.close();
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
            ;
            map.put("total_fee", new BigDecimal(order.getAmount()).multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_UP));
            map.put("openid", user.getOpenId());
            map.put("spbill_create_ip", "39.106.114.227");
            map.put("notify_url", "https://fruits.knxy.top/pay/confirm");
            map.put("trade_type", "JSAPI");
            map.put("sign", ServiceUtils.getWXPaySignValue(map));

            String data = XmlUtils.mapToXmlStr(map, false);
            orderInfo = WebUtils.requestPost("https://api.mch.weixin.qq.com/pay/unifiedorder", data, OrderInfo.class);
        }

        if (!"SUCCESS".equals(orderInfo.result_code)) {
            session.close();
            throw new ServiceException(orderInfo.return_msg);
        }

        {
            TreeMap<Object, Object> map = new TreeMap<>();
            map.clear();
            map.put("appId", S.WeChat.appid);
            map.put("timeStamp", String.valueOf(new Date().getTime()));
            map.put("nonceStr", ServiceUtils.getUUid());
            map.put("package", "prepay_id=" + orderInfo.prepay_id);
            map.put("signType", "MD5");
            map.put("sign", ServiceUtils.getWXPaySignValue(map));

            map.remove("appId");
            this.data = map;
        }

        ServiceUtils.createSuccess(this);
        session.close();
    }


    public static class OrderInfo {
        public String return_code;
        public String return_msg;
        public String appid;
        public String mch_id;
        public String device_info;
        public String nonce_str;
        public String sign;
        public String result_code;
        public String err_code;
        public String err_code_des;
        public String trade_type;
        public String prepay_id;
        public String code_url;
    }
}
