package top.knxy.fruits.Service.Order.Group.Pay;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.Config.S;
import top.knxy.fruits.DataBase.DAL.GroupOrderDAL;
import top.knxy.fruits.DataBase.DAL.UserDAL;
import top.knxy.fruits.DataBase.Table.User;
import top.knxy.fruits.Service.Order.Group.Get.C1017;
import top.knxy.fruits.Vehicle.WcPay.OrderInfo;
import top.knxy.library.BaseService;
import top.knxy.library.ServiceException;
import top.knxy.library.Utils.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.TreeMap;

public class C1018 extends BaseService {

    public String groupOrderId;
    public String userId;

    @Override
    protected void run() throws Exception {

        if (TextUtils.isEmpty(groupOrderId)) {
            throw new RuntimeException("id is empty");
        }

        SqlSession session = MyBatisUtils.getSession();
        UserDAL uDal = session.getMapper(UserDAL.class);
        User user = uDal.getUser(userId);
        if (user == null) {
            session.close();
            throw new RuntimeException("no user");
        }

        GroupOrderDAL gDal = session.getMapper(GroupOrderDAL.class);
        C1017.GroupOrder groupOrder = gDal.get(groupOrderId, userId);
        if (groupOrder == null) {
            session.close();
            throw new RuntimeException("no order");
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
            map.put("out_trade_no", groupOrder.getId());
            map.put("total_fee", new BigDecimal(groupOrder.getPrice()).multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_UP));
            map.put("openid", user.getOpenId());
            map.put("spbill_create_ip", "39.106.114.227");
            map.put("notify_url", "https://fruits.knxy.top/pay/confirm");
            map.put("trade_type", "JSAPI");
            map.put("sign", ServiceUtils.getWXPaySignValue(map, S.WCPay.apiKey));

            String data = XmlUtils.mapToXmlStr(map, false);
            orderInfo = WebUtils.postXml("https://api.mch.weixin.qq.com/pay/unifiedorder", data, OrderInfo.class);
        }

        if (!"SUCCESS".equals(orderInfo.return_code)) {
            session.close();
            throw new ServiceException(String.format("code = %s, msg = %s, order id = %s",
                    orderInfo.return_code,
                    orderInfo.return_msg,
                    groupOrder.getId()));
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
        session.close();
    }
}
