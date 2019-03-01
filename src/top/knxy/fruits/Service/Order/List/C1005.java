package top.knxy.fruits.Service.Order.List;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.Table.Order;
import top.knxy.fruits.DataBase.MyBatisUtils;
import top.knxy.fruits.Service.BaseService;
import top.knxy.fruits.DataBase.DAL.OrderDAL;
import top.knxy.fruits.Utils.ServiceUtils;

import java.util.List;

public class C1005 extends BaseService {

    public String userId;

    @Override
    public void run() throws Exception {

        SqlSession session = MyBatisUtils.getSession();
        OrderDAL mapper = session.getMapper(OrderDAL.class);
        List<Order> orders = mapper.getListByUserId(userId);
        this.data = new OrderCollection(orders);

        session.close();
        ServiceUtils.createSuccess(this);
    }

}
