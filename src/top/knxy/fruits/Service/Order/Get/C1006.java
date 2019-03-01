package top.knxy.fruits.Service.Order.Get;

import com.google.gson.Gson;
import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.Table.Order;
import top.knxy.fruits.DataBase.MyBatisUtils;
import top.knxy.fruits.Service.BaseService;
import top.knxy.fruits.Service.Order.DBOperation;
import top.knxy.fruits.Service.Order.List.C1005;
import top.knxy.fruits.Service.ServicelUtils;
import top.knxy.fruits.Utils.StrUtils;

public class C1006 extends BaseService {

    public String userId;

    public String id;


    @Override
    public void run() throws Exception {
        if (StrUtils.isEmpty(id)) {
            ServicelUtils.createError(this);
            return;
        }

        SqlSession session = MyBatisUtils.getSession();

        Order order = new Order();
        order.setUserId(userId);
        order.setId(id);

        order = session.getMapper(DBOperation.class).getOrder(order);

        Gson gson = new Gson();
        String json = "{ \"goodList\" : " + order.getGoods() + "}";
        C1005.Data.Order o = gson.fromJson(json, C1005.Data.Order.class);
        o.setData(order);
        this.data = o;

        if (this.data == null) {
            ServicelUtils.createError(this);
            return;
        }

        ServicelUtils.createSuccess(this);
        session.close();
    }


}
