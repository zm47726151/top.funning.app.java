package top.knxy.fruits.WxMiniApi.Service.Order.Create;

import com.google.gson.Gson;
import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.WxMiniApi.DataBase.Bean.Good;
import top.knxy.fruits.WxMiniApi.DataBase.Bean.Order;
import top.knxy.fruits.WxMiniApi.DataBase.MyBatisUtils;
import top.knxy.fruits.WxMiniApi.Service.BaseService;
import top.knxy.fruits.WxMiniApi.Service.Order.DBOperation;
import top.knxy.fruits.WxMiniApi.Service.ServicelUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class C1002 extends BaseService {

    public String userId;
    public List<Item> goodList;

    public static class Item {
        public Body body;
        public String amount;

        public static class Body {
            public String id;
            public String name;
        }
    }


    @Override
    public void run() throws Exception {
        if (goodList.isEmpty()) {
            ServicelUtils.createError(this);
            return;
        }

        SqlSession session = MyBatisUtils.getSession();
        DBOperation ds = session.getMapper(DBOperation.class);

        Data data = new Data();
        this.data = data;

        BigDecimal price = new BigDecimal(0);
        data.goodList = new ArrayList<>();
        for (Item item : goodList) {
            Good good = ds.getGood(item.body.id);
            if (good == null) {
                ServicelUtils.createError(this, item.body.name + " 没货了");
                return;
            }
            data.goodList.add(new Data.Item(good, item.amount));
            price = new BigDecimal(good.getPrice()).multiply(new BigDecimal(item.amount)).add(price);
        }

        Gson gson = new Gson();

        Order order = new Order();
        order.setId(ServicelUtils.getUUid());
        order.setGoods(gson.toJson(data.goodList));
        order.setPrice(price.toString());
        order.setCreateDT(new Date());
        order.setUserId(userId);
        order.setState(1);

        int result = ds.insert(order);
        session.commit();
        if (result < 1) {
            ServicelUtils.createError(this, "生成订单失败");
            return;
        }

        data.price = price.toString();
        data.id = order.getId();
        ServicelUtils.createSuccess(this);
        session.close();
    }


    public static class Data {

        public String id;
        public String price;
        public List<Item> goodList;

        public static class Item {
            public Good body;
            public String amount;

            public Item(Good body, String amount) {
                this.body = body;
                this.amount = amount;
            }
        }
    }
}
