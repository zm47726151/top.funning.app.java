package top.knxy.fruits.Service.Group.Good.List;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.DAL.GroupGoodDAL;
import top.knxy.fruits.DataBase.Model.Page;
import top.knxy.library.BaseService;
import top.knxy.library.Utils.ServiceUtils;

import java.util.Date;
import java.util.List;

public class M1030 extends BaseService {

    public String page;


    @Override
    protected void run() throws Exception {
        SqlSession session = getSqlSession();
        GroupGoodDAL ggDal = session.getMapper(GroupGoodDAL.class);
        Data data = new Data();
        data.orders = ggDal.getListByAdmin(new Page(page));
        this.data = data;
        ServiceUtils.createSuccess(this);
    }

    public static class Data {
        private List<GroupGood> orders;

        public List<GroupGood> getOrders() {
            return orders;
        }

        public void setOrders(List<GroupGood> orders) {
            this.orders = orders;
        }

        public static class GroupGood {
            private String id;
            private String name;
            private String price;
            private String groupNum;
            private Date stopTime;
            private Date getTimeStart;
            private Date getTimeStop;
            private String state;
            private String stateStr;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getGroupNum() {
                return groupNum;
            }

            public void setGroupNum(String groupNum) {
                this.groupNum = groupNum;
            }

            public Date getStopTime() {
                return stopTime;
            }

            public void setStopTime(Date stopTime) {
                this.stopTime = stopTime;
            }

            public Date getGetTimeStart() {
                return getTimeStart;
            }

            public void setGetTimeStart(Date getTimeStart) {
                this.getTimeStart = getTimeStart;
            }

            public Date getGetTimeStop() {
                return getTimeStop;
            }

            public void setGetTimeStop(Date getTimeStop) {
                this.getTimeStop = getTimeStop;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;

                if ("1".equals(state)) {
                    stateStr = "上架中";
                } else if ("2".equals(state)) {
                    stateStr = "已下架";
                }
            }

            public String getStateStr() {
                return stateStr;
            }

            public void setStateStr(String stateStr) {
                this.stateStr = stateStr;
            }
        }
    }
}
