package top.knxy.fruits.Service.Order.Undo;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.Model.Page;
import top.knxy.fruits.DataBase.MyBatisUtils;
import top.knxy.fruits.Service.BaseService;
import top.knxy.fruits.DataBase.DAL.OrderDAL;
import top.knxy.fruits.Utils.DateUtils;
import top.knxy.fruits.Utils.ServiceUtils;

import java.util.ArrayList;
import java.util.List;

public class M1003 extends BaseService {

    public String page;

    @Override
    public void run() throws Exception {
        Page pModel = new Page(page);
        SqlSession session = MyBatisUtils.getSession();
        this.data = new Data(session.getMapper(OrderDAL.class).getUndoList(pModel));

        session.close();
        ServiceUtils.createSuccess(this);
    }


    public static class Data {
        private List<Order> orders;

        public Data(List<top.knxy.fruits.DataBase.Table.Order> orders) {
            this.orders = new ArrayList<>();
            for (top.knxy.fruits.DataBase.Table.Order o : orders) {
                Order order = new Order(o);
                this.orders.add(order);
            }
        }

        public List<Order> getOrders() {
            return orders;
        }

        public void setOrders(List<Order> orders) {
            this.orders = orders;
        }

        public static class Order {
            private String id;
            private String price;
            private String poster;
            private String priceAmount;

            private String telNumber;
            private String userName;

            private String stateStr;
            private String userId;
            private String createDT;
            private String payDT;

            public Order(top.knxy.fruits.DataBase.Table.Order o) {
                this.id = o.getId();

                this.price = o.getPrice();
                this.poster = o.getPoster();
                this.priceAmount = o.getAmount();

                this.telNumber = o.getTelNumber();
                this.userName = o.getUserName();

                this.stateStr = ServiceUtils.getStateStr(o.getState());
                this.userId = o.getUserId();

                if (o.getCreateDT() != null) {
                    this.createDT = DateUtils.dateToString(o.getCreateDT(), DateUtils.dateStringType2);
                }

                if (o.getPayDT() != null) {
                    this.payDT = DateUtils.dateToString(o.getPayDT(), DateUtils.dateStringType2);
                }
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getPoster() {
                return poster;
            }

            public void setPoster(String poster) {
                this.poster = poster;
            }

            public String getPriceAmount() {
                return priceAmount;
            }

            public void setPriceAmount(String priceAmount) {
                this.priceAmount = priceAmount;
            }

            public String getTelNumber() {
                return telNumber;
            }

            public void setTelNumber(String telNumber) {
                this.telNumber = telNumber;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getStateStr() {
                return stateStr;
            }

            public void setStateStr(String stateStr) {
                this.stateStr = stateStr;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getCreateDT() {
                return createDT;
            }

            public void setCreateDT(String createDT) {
                this.createDT = createDT;
            }

            public String getPayDT() {
                return payDT;
            }

            public void setPayDT(String payDT) {
                this.payDT = payDT;
            }
        }
    }
}
