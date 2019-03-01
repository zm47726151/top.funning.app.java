package top.knxy.fruits.Service.Order.Comfirm;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.Config.S;
import top.knxy.fruits.DataBase.DAL.LoginDAL;
import top.knxy.fruits.DataBase.Table.Order;
import top.knxy.fruits.DataBase.MyBatisUtils;
import top.knxy.fruits.DataBase.Table.User;
import top.knxy.fruits.Service.BaseService;
import top.knxy.fruits.DataBase.DAL.OrderDAL;
import top.knxy.fruits.Service.ServiceException;
import top.knxy.fruits.Servlet.Admin.Remind;
import top.knxy.fruits.Utils.ServiceUtils;
import top.knxy.fruits.Utils.TextUtils;
import top.knxy.fruits.Utils.WebUtils;
import top.knxy.fruits.Utils.XmlUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.TreeMap;

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


        SqlSession session = MyBatisUtils.getSession();
        OrderDAL mapper = session.getMapper(OrderDAL.class);

        Order order = new Order();
        order.setId(id);
        order.setUserId(userId);
        order = mapper.getOrderByUser(order);
        if (order == null) {
            session.close();
            throw new ServiceException("没有订单 id = " + id);
        }

        if (order.getState() == 1) {
            order.setState(7);
            int result = mapper.update(order);
            session.commit();
            if (result < 1) {
                session.close();
                throw new ServiceException("订单修改失败 order id = " + id);
            }
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
