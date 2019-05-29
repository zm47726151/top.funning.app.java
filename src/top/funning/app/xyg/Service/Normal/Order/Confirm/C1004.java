package top.funning.app.xyg.Service.Normal.Order.Confirm;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.OrderDAL;
import top.funning.app.xyg.DataBase.Table.Order;
import top.knxy.library.BaseService;
import top.knxy.library.ServiceException;
import top.knxy.library.Utils.ServiceUtils;
import top.knxy.library.Utils.TextUtils;

public class C1004 extends BaseService {

    public String userId;
    public String id;

    @Override
    public void run() throws Exception {
        if (TextUtils.isEmpty(id)) {
            throw new ServiceException("没有地址");
        }
        if (TextUtils.isEmpty(userId)) {
            throw new ServiceException("没有姓名");
        }


        SqlSession session = getSqlSession();
        OrderDAL mapper = session.getMapper(OrderDAL.class);

        Order order = new Order();
        order.setId(id);
        order.setUserId(userId);
        order = mapper.getOrderByUser(order);
        if (order == null) {

            throw new ServiceException("没有订单 id = " + id);
        }

        if (order.getState() == 1) {
            order.setState(7);
            int result = mapper.update(order);
            session.commit();
            if (result < 1) {

                throw new ServiceException("订单修改失败 order id = " + id);
            }
        }

        ServiceUtils.createSuccess(this);

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
