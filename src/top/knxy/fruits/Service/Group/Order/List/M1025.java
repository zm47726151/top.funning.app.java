package top.knxy.fruits.Service.Group.Order.List;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.DAL.GroupOrderDAL;
import top.knxy.fruits.DataBase.Model.Page;
import top.knxy.library.BaseService;
import top.knxy.library.Utils.ServiceUtils;

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
            private String getTimeStart;
            private String getTimeStop;
            private String name;
            private String stateVal;
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

            public String getGetTimeStart() {
                return getTimeStart;
            }

            public void setGetTimeStart(String getTimeStart) {
                this.getTimeStart = getTimeStart;
            }

            public String getGetTimeStop() {
                return getTimeStop;
            }

            public void setGetTimeStop(String getTimeStop) {
                this.getTimeStop = getTimeStop;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getStateVal() {
                return stateVal;
            }

            public void setStateVal(String stateVal) {
                this.stateVal = stateVal;
            }

            public String getStateStr() {
                return stateStr;
            }

            public void setStateStr(String stateStr) {
                this.stateStr = stateStr;
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
