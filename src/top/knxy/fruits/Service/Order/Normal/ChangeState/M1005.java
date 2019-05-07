package top.knxy.fruits.Service.Order.Normal.ChangeState;

import org.apache.ibatis.session.SqlSession;
import top.knxy.library.Utils.MyBatisUtils;
import top.knxy.fruits.DataBase.Table.Order;
import top.knxy.library.BaseService;
import top.knxy.fruits.DataBase.DAL.OrderDAL;
import top.knxy.library.Utils.ServiceUtils;
import top.knxy.library.Utils.TextUtils;

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
