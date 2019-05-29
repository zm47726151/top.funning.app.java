package top.funning.app.xyg.Service.Group.Order.Get;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.GroupOrderDAL;
import top.funning.app.xyg.DataBase.Table.GroupOrder;
import top.funning.library.BaseService;
import top.funning.library.ServiceException;
import top.funning.library.Utils.ServiceUtils;
import top.funning.library.Utils.TextUtils;

import java.util.Date;

public class M1026 extends BaseService {


    public String id;

    @Override
    protected void run() throws Exception {
        if (TextUtils.isEmpty(id)) {
            throw new ServiceException();
        }

        SqlSession session = getSqlSession();
        GroupOrderDAL dal = session.getMapper(GroupOrderDAL.class);
        this.data = new Data(dal.getById(id));

        ServiceUtils.createSuccess(this);
    }


    public static class Data {

        private String id;
        private String price;
        private Date getTimeStart;
        private Date getTimeStop;
        private String groupNum;
        private int state;
        private String stateStr;
        private String userId;
        private Date createDT;
        private Date payDT;
        private String teamId;

        private String GroupGoodId;
        private String name;
        private String description;
        private String imageUrl;

        public Data(GroupOrder o) {
            this.id = o.getId();
            this.price = o.getPrice();
            this.getTimeStart = o.getGetTimeStart();
            this.getTimeStop = o.getGetTimeStop();
            this.groupNum = o.getGroupNum();

            this.state = o.getState();
            this.stateStr = ServiceUtils.getGroupOrderStateText(o.getState());
            this.userId = o.getUserId();
            this.createDT = o.getCreateDT();
            this.payDT = o.getPayDT();
            this.teamId = o.getTeamId();
            this.GroupGoodId = o.getGroupGoodId();
            this.name = o.getName();
            this.description = o.getDescription();
            this.imageUrl = o.getImageUrl();
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

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;

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

        public String getTeamId() {
            return teamId;
        }

        public void setTeamId(String teamId) {
            this.teamId = teamId;
        }

        public String getGroupGoodId() {
            return GroupGoodId;
        }

        public void setGroupGoodId(String groupGoodId) {
            GroupGoodId = groupGoodId;
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
    }

}
