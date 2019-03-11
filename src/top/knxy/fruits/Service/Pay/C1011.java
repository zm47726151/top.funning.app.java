package top.knxy.fruits.Service.Pay;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.DAL.OrderDAL;
import top.knxy.fruits.DataBase.MyBatisUtils;
import top.knxy.fruits.DataBase.Table.Order;
import top.knxy.fruits.Service.BaseService;
import top.knxy.fruits.Service.ServiceException;
import top.knxy.fruits.Servlet.Admin.Remind;
import top.knxy.fruits.Utils.ServiceUtils;
import top.knxy.fruits.Utils.TextUtils;
import top.knxy.fruits.Utils.XmlUtils;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class C1011 extends BaseService {
    public String data;

    @Override
    protected void run() throws Exception {
        Map<String, Object> map = XmlUtils.xmlStrToMap(data);
        Set<String> keys = map.keySet();

        TreeMap<Object, Object> tMap = new TreeMap<>();
        for (String key : keys) {
            tMap.put(key, map.get(key));
        }

        String sign = ServiceUtils.getWXPaySignValue(tMap);

        if (!sign.equals(tMap.get("sign"))) {
            throw new ServiceException("签名失败");
        }

        RequestData requestData = XmlUtils.xmlStrToBean(data, RequestData.class);
        if (!"SUCCESS".equals(requestData.return_code)) {
            throw new ServiceException(requestData.return_msg);
        }

        if (TextUtils.isEmpty(requestData.out_trade_no) || requestData.out_trade_no.length() < 32) {
            throw new ServiceException("order id (out_trade_no) : " + requestData.out_trade_no + " is illegal");
        }

        String orderId = requestData.out_trade_no;
        SqlSession session = MyBatisUtils.getSession();
        OrderDAL dal = session.getMapper(OrderDAL.class);
        Order order = dal.getOrder(orderId);

        BigDecimal wcMoney = new BigDecimal(requestData.cash_fee);
        BigDecimal ownMoney = new BigDecimal(order.getAmount()).multiply(new BigDecimal(100));


        if (wcMoney.compareTo(ownMoney) != 0) {
            session.close();
            throw new ServiceException("交易金额不对等,order id = " + orderId);
        }

        order.setState(2);
        int result = dal.update(order);
        session.commit();
        if (result < 1) {
            session.close();
            throw new ServiceException("订单状态修改失败,order id = " + orderId);
        }

        session.close();

        Remind.broadcast();

        ServiceUtils.createSuccess(this);
    }

    /**
     * https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=9_7
     */
    public static class RequestData {
        public String return_code;
        public String return_msg;
        public String appid;
        public String mch_id;
        public String device_info;
        public String nonce_str;
        public String sign;
        public String sign_type;
        public String result_code;
        public String err_code;
        public String err_code_des;
        public String openid;
        public String is_subscribe;
        public String trade_type;
        public String bank_type;
        public String total_fee;
        public String settlement_total_fee;
        public String fee_type;
        public String cash_fee;
        public String cash_fee_type;
        public String coupon_fee;
        public String coupon_count;
        public String coupon_type_$n;
        public String coupon_id_$n;
        public String coupon_fee_$n;
        public String transaction_id;
        public String out_trade_no;
        public String attach;
        public String time_end;
    }
}
