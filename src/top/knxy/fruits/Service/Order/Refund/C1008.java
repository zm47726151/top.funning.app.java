package top.knxy.fruits.Service.Order.Refund;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.Table.Order;
import top.knxy.fruits.DataBase.MyBatisUtils;
import top.knxy.fruits.Service.BaseService;
import top.knxy.fruits.DataBase.DAL.OrderDAL;
import top.knxy.fruits.Utils.ServiceUtils;
import top.knxy.fruits.Utils.StrUtils;

public class C1008 extends BaseService {

    public String userId;

    public String id;


    @Override
    public void run() throws Exception {
        if (StrUtils.isEmpty(id)) {
            ServiceUtils.createError(this);
            return;
        }

        Order order = new Order();
        order.setState(4);
        order.setUserId(userId);
        order.setId(id);

        SqlSession session = MyBatisUtils.getSession();
        int result = session.getMapper(OrderDAL.class).changeStateByUser(order);
        session.commit();
        session.close();

        if (result < 1) {
            ServiceUtils.createError(this, "申请退款失败");
            return;
        }

        ServiceUtils.createSuccess(this);
        session.close();
    }


}
