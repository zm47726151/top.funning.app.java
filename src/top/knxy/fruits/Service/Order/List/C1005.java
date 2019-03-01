package top.knxy.fruits.Service.Order.List;

import com.google.gson.Gson;
import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.Table.Order;
import top.knxy.fruits.DataBase.MyBatisUtils;
import top.knxy.fruits.Service.BaseService;
import top.knxy.fruits.Service.Order.DBOperation;
import top.knxy.fruits.Service.ServicelUtils;
import top.knxy.fruits.Utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class C1005 extends BaseService {

    public String userId;

    @Override
    public void run() throws Exception {
        this.userId = userId;

        SqlSession session = MyBatisUtils.getSession();
        DBOperation mapper = session.getMapper(DBOperation.class);
        List<Order> orders = mapper.getList(userId);
        this.data = new Data(orders);

        session.close();
        ServicelUtils.createSuccess(this);
    }


    public static class Data {
        public List<Order> orders;

        public Data(List<top.knxy.fruits.DataBase.Table.Order> orders) {
            this.orders = new ArrayList<>();
            Gson gson = new Gson();
            for (top.knxy.fruits.DataBase.Table.Order o : orders) {
                String json = "{ \"goodList\" : " + o.getGoods() + "}";
                Order order = gson.fromJson(json, Order.class);
                order.setData(o);
                this.orders.add(order);
            }
        }

        public static class Order {
            public String id;
            public String price;
            public String poster;
            public String priceAmount;

            public String provinceName;
            public String cityName;
            public String countyName;
            public String detailInfo;
            public String telNumber;
            public String userName;
            public String nationalCode;
            public String postalCode;

            public String note;
            public String state;
            public String stateStr;
            public String userId;
            public String createDT;
            public String payDT;
            public String goodAmount;

            public List<Good> goodList;

            public void setData(top.knxy.fruits.DataBase.Table.Order o) {
                this.id = o.getId();

                this.price = o.getPrice();
                this.poster = o.getPoster();
                this.priceAmount = o.getAmount();

                this.provinceName = o.getProvinceName();
                this.cityName = o.getCityName();
                this.countyName = o.getCountyName();
                this.detailInfo = o.getDetailInfo();
                this.telNumber = o.getTelNumber();
                this.userName = o.getUserName();
                this.nationalCode = o.getNationalCode();
                this.postalCode = o.getPostalCode();

                this.note = o.getNote();
                this.state = String.valueOf(o.getState());
                this.stateStr = ServicelUtils.getStateStr(o.getState());
                this.userId = o.getUserId();
                if (o.getCreateDT() != null)
                    this.createDT = DateUtils.dateToString(o.getCreateDT(), DateUtils.dateStringType2);
                if (o.getPayDT() != null)
                    this.payDT = DateUtils.dateToString(o.getPayDT(), DateUtils.dateStringType2);

                int goodAmount = 0;
                for (Order.Good good : this.goodList) {
                    goodAmount += Integer.valueOf(good.amount);
                }
                this.goodAmount = String.valueOf(goodAmount);
            }

            public static class Good {

                /**
                 * amount : 1
                 * body : {"price":"40.00","imageUrl":"http://127.0.0.1:8080/430bc1e8f1ae4862a26b5e70a8090460.jpg","name":"雪梨","description":"3.0rmb/kg","id":53,"type":"1","stock":"300"}
                 */
                public String amount;
                public BodyEntity body;

                public class BodyEntity {
                    /**
                     * price : 40.00
                     * imageUrl : http://127.0.0.1:8080/430bc1e8f1ae4862a26b5e70a8090460.jpg
                     * name : 雪梨
                     * description : 3.0rmb/kg
                     * id : 53
                     * type : 1
                     * stock : 300
                     */
                    public String id;
                    public String price;
                    public String imageUrl;
                    public String name;
                    public String description;
                }
            }
        }
    }
}
