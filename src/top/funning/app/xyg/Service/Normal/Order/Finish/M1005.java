package top.funning.app.xyg.Service.Normal.Order.Finish;

import net.sf.oval.constraint.Length;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.OrderDAL;
import top.funning.app.xyg.DataBase.Table.Order;
import top.knxy.library.BaseService;
import top.knxy.library.Utils.ServiceUtils;


public class M1005 extends BaseService {
    @Length(max = 32, min = 32)
    public String id;

    @Override
    protected void run() throws Exception {

        SqlSession session = getSqlSession();
        OrderDAL operation = session.getMapper(OrderDAL.class);
        Order order = operation.getState(id);

        if (order.getState() != 2) {
            throw new Exception("修改不合法");
        }

        order.setState(3);
        operation.changeState(order);
        session.commit();

        ServiceUtils.createSuccess(this);
    }
}
