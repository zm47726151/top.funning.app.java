package top.knxy.fruits.Service.Order.Normal.List;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.Table.Order;
import top.knxy.library.Utils.MyBatisUtils;
import top.knxy.library.BaseService;
import top.knxy.fruits.DataBase.DAL.OrderDAL;
import top.knxy.library.Utils.ServiceUtils;

import java.util.List;

public class C1005 extends BaseService {

    public String userId;

    @Override
    public void run() throws Exception {

        SqlSession session = getSqlSession();
        OrderDAL mapper = session.getMapper(OrderDAL.class);
        List<Order> orders = mapper.getListByUserId(userId);
        this.data = new OrderCollection(orders);


        ServiceUtils.createSuccess(this);
    }

}
