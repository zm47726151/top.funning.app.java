package top.funning.app.xyg.Service.Normal.Order.Get;

import com.google.gson.Gson;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.OrderDAL;
import top.funning.app.xyg.DataBase.Table.Order;
import top.funning.app.xyg.Service.Normal.Order.List.OrderCollection;
import top.funning.library.BaseService;
import top.funning.library.Utils.ServiceUtils;
import top.funning.library.Utils.TextUtils;

public class C1006 extends BaseService {

    public String userId;

    public String id;


    @Override
    public void run() throws Exception {
        if (TextUtils.isEmpty(id)) {
            ServiceUtils.createError(this);
            return;
        }

        SqlSession session = getSqlSession();

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

    }


}
