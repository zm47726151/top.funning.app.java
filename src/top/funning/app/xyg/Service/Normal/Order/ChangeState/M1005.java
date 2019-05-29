package top.funning.app.xyg.Service.Normal.Order.ChangeState;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.OrderDAL;
import top.funning.app.xyg.DataBase.Table.Order;
import top.funning.library.BaseService;
import top.funning.library.Utils.ServiceUtils;
import top.funning.library.Utils.TextUtils;

public class M1005 extends BaseService {

    public String id;
    public String state;


    @Override
    protected void run() throws Exception {
        if (TextUtils.isEmpty(id)) {
            ServiceUtils.createError(this);
            return;
        }

        if (!TextUtils.isNumeric(state)) {
            ServiceUtils.createError(this);
            return;
        }

        SqlSession session = getSqlSession();
        OrderDAL operation = session.getMapper(OrderDAL.class);
        Order order = operation.getState(id);
        int s = Integer.valueOf(state);

        if (!((order.getState() == 2 && s == 3) ||
                (order.getState() == 4 && s == 6) ||
                (order.getState() == 2 && s == 5))) {
            throw new Exception("修改不合法");
        }

        order.setState(s);
        operation.changeState(order);
        session.commit();


        ServiceUtils.createSuccess(this);
    }
}
