package top.funning.app.xyg.Service.Normal.Order.List;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.Table.Order;
import top.funning.library.BaseService;
import top.funning.app.xyg.DataBase.DAL.OrderDAL;
import top.funning.library.Utils.ServiceUtils;

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
