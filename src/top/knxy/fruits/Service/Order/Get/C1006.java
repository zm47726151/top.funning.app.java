package top.knxy.fruits.Service.Order.Get;

import com.google.gson.Gson;
import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.Table.Order;
import top.knxy.fruits.DataBase.MyBatisUtils;
import top.knxy.fruits.Service.BaseService;
import top.knxy.fruits.DataBase.DAL.OrderDAL;
import top.knxy.fruits.Service.Order.List.OrderCollection;
import top.knxy.fruits.Utils.ServiceUtils;
import top.knxy.fruits.Utils.TextUtils;

public class C1006 extends BaseService {

    public String userId;

    public String id;


    @Override
    public void run() throws Exception {
        if (TextUtils.isEmpty(id)) {
            ServiceUtils.createError(this);
            return;
        }

        SqlSession session = MyBatisUtils.getSession();

        Order order = new Order();
        order.setUserId(userId);
        order.setId(id);

        order = session.getMapper(OrderDAL.class).getOrderByUser(order);

        this.data = OrderCollection.createOrder(order,new Gson());

        if (this.data == null) {
            ServiceUtils.createError(this);
            return;
        }

        ServiceUtils.createSuccess(this);
        session.close();
    }


}
