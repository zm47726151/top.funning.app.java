package top.knxy.fruits.Service.Manager.Order;

import com.google.gson.Gson;
import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.MyBatisUtils;
import top.knxy.fruits.DataBase.Table.Order;
import top.knxy.fruits.Service.BaseService;
import top.knxy.fruits.Service.Order.List.OrderCollection;
import top.knxy.fruits.Utils.ServiceUtils;
import top.knxy.fruits.Utils.StrUtils;

public class SearchService extends BaseService {

    public static String id;

    @Override
    protected void run() throws Exception {
        if (StrUtils.isEmpty(id)) {
            ServiceUtils.createError(this);
            return;
        }

        SqlSession session = MyBatisUtils.getSession();
        DBOperation operation = session.getMapper(DBOperation.class);
        Order order = operation.get(id);
        session.close();
        this.data = OrderCollection.createOrder(order,new Gson());

        ServiceUtils.createSuccess(this);
    }
}
