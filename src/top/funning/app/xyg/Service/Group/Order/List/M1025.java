package top.funning.app.xyg.Service.Group.Order.List;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.GroupOrderDAL;
import top.funning.app.xyg.DataBase.Model.Page;
import top.funning.library.BaseService;
import top.funning.library.Utils.ServiceUtils;

import java.util.Date;

public class M1025 extends BaseService {

    public String page;

    @Override
    protected void run() throws Exception {
        Page page = new Page(this.page);
        SqlSession session = getSqlSession();
        GroupOrderDAL dal = session.getMapper(GroupOrderDAL.class);

        Data data = new Data();
        data.dataList = dal.getListByAdmin(page);
        this.data = data;
        ServiceUtils.createSuccess(this);
    }

    public static class Data {

        public java.util.List<GroupOrder> dataList;

        public java.util.List<GroupOrder> getDataList() {
            return dataList;
        }

        public void setDataList(java.util.List<GroupOrder> dataList) {
            this.dataList = dataList;
        }

        public static class GroupOrder {
            private String id;
            private String price;
            private Date getTimeStart;
            private Date getTimeStop;
            private String name;
            private String state;
            private String stateStr;
            private String createDT;
            private String payDT;

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
                this.stateStr = ServiceUtils.getGroupOrderStateText(state);
            }

            public String getStateStr() {
                return stateStr;
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
