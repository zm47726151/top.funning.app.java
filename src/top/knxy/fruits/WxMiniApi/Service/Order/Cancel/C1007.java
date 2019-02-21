package top.knxy.fruits.WxMiniApi.Service.Order.Cancel;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.WxMiniApi.DataBase.Bean.Order;
import top.knxy.fruits.WxMiniApi.DataBase.MyBatisUtils;
import top.knxy.fruits.WxMiniApi.Service.BaseService;
import top.knxy.fruits.WxMiniApi.Service.Order.DBOperation;
import top.knxy.fruits.WxMiniApi.Service.ServicelUtils;
import top.knxy.fruits.WxMiniApi.Utils.StrUtils;

public class C1007 extends BaseService {

    public String userId;

    public String id;


    @Override
    public void run() throws Exception {
        if (StrUtils.isEmpty(id)) {
            ServicelUtils.createError(this);
            return;
        }


        Order order = new Order();
        order.setState(5);
        order.setUserId(userId);
        order.setId(id);

        SqlSession session = MyBatisUtils.getSession();
        int result = session.getMapper(DBOperation.class).changeState(order);
        session.commit();
        session.close();

        if (result < 1) {
            ServicelUtils.createError(this, "申请退款失败");
            return;
        }

        ServicelUtils.createSuccess(this);
    }


}
