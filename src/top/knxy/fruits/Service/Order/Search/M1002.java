package top.knxy.fruits.Service.Order.Search;

import com.google.gson.Gson;
import org.apache.ibatis.session.SqlSession;
import top.knxy.library.Utils.MyBatisUtils;
import top.knxy.fruits.DataBase.Table.Order;
import top.knxy.library.BaseService;
import top.knxy.fruits.DataBase.DAL.OrderDAL;
import top.knxy.fruits.Service.Order.List.OrderCollection;
import top.knxy.library.Utils.ServiceUtils;
import top.knxy.library.Utils.TextUtils;

public class M1002 extends BaseService {

    public String id;

    @Override
    protected void run() throws Exception {
        if (TextUtils.isEmpty(id)) {
            ServiceUtils.createError(this);
            return;
        }

        SqlSession session = MyBatisUtils.getSession();
        OrderDAL operation = session.getMapper(OrderDAL.class);
        Order order = operation.getOrder(id);
        session.close();
        this.data = OrderCollection.createOrder(order,new Gson());

        ServiceUtils.createSuccess(this);
    }
}
