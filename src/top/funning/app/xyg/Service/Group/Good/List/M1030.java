package top.funning.app.xyg.Service.Group.Good.List;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.GroupGoodDAL;
import top.funning.app.xyg.DataBase.Model.Page;
import top.funning.library.BaseService;
import top.funning.library.Utils.ServiceUtils;

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
                this.stateStr = ServiceUtils.getGroupGoodStateText(state);
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
