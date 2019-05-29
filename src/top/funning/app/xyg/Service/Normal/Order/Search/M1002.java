package top.funning.app.xyg.Service.Normal.Order.Search;

import com.google.gson.Gson;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.OrderDAL;
import top.funning.app.xyg.DataBase.Table.Order;
import top.funning.app.xyg.Service.Normal.Order.List.OrderCollection;
import top.knxy.library.BaseService;
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

        SqlSession session = getSqlSession();
        OrderDAL operation = session.getMapper(OrderDAL.class);
        Order order = operation.getOrder(id);

        this.data = OrderCollection.createOrder(order,new Gson());

        ServiceUtils.createSuccess(this);
    }
}
