package top.knxy.fruits.WxMiniApi.Service.Order.Refund;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.WxMiniApi.DataBase.Table.Order;
import top.knxy.fruits.WxMiniApi.DataBase.MyBatisUtils;
import top.knxy.fruits.WxMiniApi.Service.BaseService;
import top.knxy.fruits.WxMiniApi.Service.Order.DBOperation;
import top.knxy.fruits.WxMiniApi.Service.ServicelUtils;
import top.knxy.fruits.WxMiniApi.Utils.StrUtils;

public class C1008 extends BaseService {

    public String userId;

    public String id;


    @Override
    public void run() throws Exception {
        if (StrUtils.isEmpty(id)) {
            ServicelUtils.createError(this);
            return;
        }

        Order order = new Order();
        order.setState(6);
        order.setUserId(userId);
        order.setId(id);

        SqlSession session = MyBatisUtils.getSession();
        int result = session.getMapper(DBOperation.class).changeState(order);
        session.commit();
        session.close();

        if (result < 1) {
            ServicelUtils.createError(this, "取消订单失败");
            return;
        }

        ServicelUtils.createSuccess(this);
        session.close();
    }


}
