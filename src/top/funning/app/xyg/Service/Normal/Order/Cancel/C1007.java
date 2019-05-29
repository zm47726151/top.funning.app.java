package top.funning.app.xyg.Service.Normal.Order.Cancel;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.OrderDAL;
import top.funning.app.xyg.DataBase.Table.Order;
import top.knxy.library.BaseService;
import top.knxy.library.Utils.ServiceUtils;
import top.knxy.library.Utils.TextUtils;

public class C1007 extends BaseService {

    public String userId;

    public String id;


    @Override
    public void run() throws Exception {
        if (TextUtils.isEmpty(id)) {
            ServiceUtils.createError(this);
            return;
        }


        Order order = new Order();
        order.setState(5);
        order.setUserId(userId);
        order.setId(id);

        SqlSession session = getSqlSession();
        int result = session.getMapper(OrderDAL.class).changeStateByUser(order);
        session.commit();


        if (result < 1) {
            ServiceUtils.createError(this, "申请退款失败");
            return;
        }

        ServiceUtils.createSuccess(this);

    }


}
