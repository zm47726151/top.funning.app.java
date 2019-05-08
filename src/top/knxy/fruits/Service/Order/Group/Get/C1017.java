package top.knxy.fruits.Service.Order.Group.Get;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.DAL.GroupOrderDAL;
import top.knxy.fruits.DataBase.Table.GroupOrder;
import top.knxy.library.BaseService;
import top.knxy.library.Utils.MyBatisUtils;
import top.knxy.library.Utils.ServiceUtils;
import top.knxy.library.Utils.TextUtils;

import java.util.Date;

public class C1017 extends BaseService {
    public String id;
    public String userId;

    @Override
    protected void run() throws Exception {
        if (TextUtils.isEmpty(id) || TextUtils.isEmpty(userId)) {
            throw new NullPointerException();
        }

        SqlSession session = getSqlSession();
        GroupOrderDAL dal = session.getMapper(GroupOrderDAL.class);
        GroupOrder go = dal.get(id, userId);
        this.data = go;


        ServiceUtils.createSuccess(this);
    }


    public static class GroupOrder {
        private String id;
        private String price;
        private Date getTimeStart;
        private Date getTimeStop;
        private String groupNum;
        private String groupGoodId;
        private String name;
        private String description;
        private String imageUrl;
        private int state;
        private String stateStr;
        private Date createDT;
        private Date payDT;
        private String teamId;

        public String getStateStr() {
            return stateStr;
        }

        public void setStateStr(String stateStr) {
            this.stateStr = stateStr;
        }

        public String getTeamId() {
            return teamId;
        }

        public void setTeamId(String teamId) {
            this.teamId = teamId;
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

        public String getGroupNum() {
            return groupNum;
        }

        public void setGroupNum(String groupNum) {
            this.groupNum = groupNum;
        }

        public String getGroupGoodId() {
            return groupGoodId;
        }

        public void setGroupGoodId(String groupGoodId) {
            this.groupGoodId = groupGoodId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;

            if (1 == state){
                this.stateStr = "待付款";
            } else if (2 == state) {
                this.stateStr = "拼团中";
            } else if (3 == state) {
                this.stateStr = "待取货";
            } else if (4 == state) {
                this.stateStr = "已完成";
            } else if (5 == state) {
                this.stateStr = "退款中";
            } else if (6 == state) {
                this.stateStr = "已取消";
            } else if (7 == state) {
                this.stateStr = "已退款";
            }
        }

        public Date getCreateDT() {
            return createDT;
        }

        public void setCreateDT(Date createDT) {
            this.createDT = createDT;
        }

        public Date getPayDT() {
            return payDT;
        }

        public void setPayDT(Date payDT) {
            this.payDT = payDT;
        }

    }
}
