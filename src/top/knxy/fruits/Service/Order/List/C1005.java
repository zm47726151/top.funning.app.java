package top.knxy.fruits.Service.Order.List;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.Table.Order;
import top.knxy.fruits.DataBase.MyBatisUtils;
import top.knxy.fruits.Service.BaseService;
import top.knxy.fruits.Service.Order.DBOperation;
import top.knxy.fruits.Utils.ServiceUtils;

import java.util.List;

public class C1005 extends BaseService {

    public String userId;

    @Override
    public void run() throws Exception {
        this.userId = userId;

        SqlSession session = MyBatisUtils.getSession();
        DBOperation mapper = session.getMapper(DBOperation.class);
        List<Order> orders = mapper.getList(userId);
        this.data = new OrderCollection(orders);

        session.close();
        ServiceUtils.createSuccess(this);
    }

}
