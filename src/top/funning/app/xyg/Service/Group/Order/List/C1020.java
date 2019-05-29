package top.funning.app.xyg.Service.Group.Order.List;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.GroupOrderDAL;
import top.funning.app.xyg.DataBase.DAL.UserDAL;
import top.funning.app.xyg.DataBase.Table.User;
import top.knxy.library.BaseService;
import top.knxy.library.Utils.LogUtils;
import top.knxy.library.Utils.ServiceUtils;

import java.util.Date;
import java.util.List;

public class C1020 extends BaseService {
    public static final String TAG = "C1020";

    public String userId;

    @Override
    protected void run() throws Exception {
        SqlSession session = getSqlSession();
        GroupOrderDAL goDal = session.getMapper(GroupOrderDAL.class);

        UserDAL uDal = session.getMapper(UserDAL.class);
        List<Data.GroupOrder> dataList = goDal.getList(userId);
        User user = uDal.getUser(userId);

        LogUtils.d(TAG, user.toString());
        this.data = new Data(dataList, user.getNickName());
        ServiceUtils.createSuccess(this);
    }


    public static class Data {
        public String nickName;
        public List<GroupOrder> groups;

        public Data(List<GroupOrder> groups, String nickName) {
            this.nickName = nickName;
            this.groups = groups;
        }

        public static class GroupOrder {
            private String id;
            private String price;
            private Date getTimeStart;
            private Date getTimeStop;
            private String groupNum;
            private String state;
            private String stateStr;
            private String userId;
            private Date createDT;
            private Date payDT;
            private String teamId;

            private String GroupGoodId;
            private String name;
            private String description;
            private String imageUrl;
            private String shareImageUrl;

            public String getShareImageUrl() {
                return shareImageUrl;
            }

            public void setShareImageUrl(String shareImageUrl) {
                this.shareImageUrl = shareImageUrl;
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
}