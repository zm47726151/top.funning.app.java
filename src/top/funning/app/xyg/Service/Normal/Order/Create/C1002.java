package top.funning.app.xyg.Service.Normal.Order.Create;

import com.google.gson.Gson;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.GoodDAL;
import top.funning.app.xyg.DataBase.DAL.OrderDAL;
import top.funning.app.xyg.DataBase.Table.Good;
import top.funning.app.xyg.DataBase.Table.Order;
import top.knxy.library.BaseService;
import top.knxy.library.Utils.ServiceUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class C1002 extends BaseService {

    public String userId;
    public List<Item> goodList;

    public static class Item {
        public Good body;
        public String amount;
    }

    @Override
    public void run() throws Exception {
        if (goodList.isEmpty()) {
            ServiceUtils.createError(this);
            return;
        }

        SqlSession session = getSqlSession();
        OrderDAL orderDAL = session.getMapper(OrderDAL.class);
        GoodDAL goodDAL = session.getMapper(GoodDAL.class);

        Data data = new Data();
        this.data = data;

        BigDecimal price = new BigDecimal(0);
        for (Item item : goodList) {
            Good good = goodDAL.get(String.valueOf(item.body.getId()));
            if (good == null) {
                ServiceUtils.createError(this, item.body.getName() + " 没货了");
                return;
            }
            item.body = good;
            price = new BigDecimal(good.getPrice()).multiply(new BigDecimal(item.amount)).add(price);
        }

        Gson gson = new Gson();

        Order order = new Order();
        order.setId(ServiceUtils.getUUid());
        order.setGoods(gson.toJson(goodList));
        order.setPrice(price.toString());
        order.setCreateDT(new Date());
        order.setUserId(userId);
        order.setState(1);

        int result = orderDAL.insert(order);
        session.commit();
        if (result < 1) {
            ServiceUtils.createError(this, "生成订单失败");
            return;
        }

        data.id = order.getId();
        ServiceUtils.createSuccess(this);

    }


    public static class Data {
        public String id;
    }
}
