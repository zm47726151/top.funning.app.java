package top.knxy.fruits.Service.Order.Normal.Cancel;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.Table.Order;
import top.knxy.library.Utils.MyBatisUtils;
import top.knxy.library.BaseService;
import top.knxy.fruits.DataBase.DAL.OrderDAL;
import top.knxy.library.Utils.ServiceUtils;
import top.knxy.library.Utils.TextUtils;

public class C1007 extends BaseService {

    public String userId;

    public String id;


    @Override
    public void run() throws Exception {
        if (TextUtils.isEmpty(id)) {
            ServiceUtils.createError(this);
            return;
        }


        Order order = new Order();
        order.setState(5);
        order.setUserId(userId);
        order.setId(id);

        SqlSession session = getSqlSession();
        int result = session.getMapper(OrderDAL.class).changeStateByUser(order);
        session.commit();


        if (result < 1) {
            ServiceUtils.createError(this, "申请退款失败");
            return;
        }

        ServiceUtils.createSuccess(this);

    }


}
