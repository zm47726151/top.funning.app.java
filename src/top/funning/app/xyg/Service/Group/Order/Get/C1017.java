package top.funning.app.xyg.Service.Group.Order.Get;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.GroupGoodDAL;
import top.funning.app.xyg.DataBase.DAL.GroupOrderDAL;
import top.funning.app.xyg.DataBase.DAL.UserDAL;
import top.funning.app.xyg.DataBase.Table.GroupGood;
import top.funning.app.xyg.DataBase.Table.GroupOrder;
import top.funning.app.xyg.DataBase.Table.User;
import top.funning.library.BaseService;
import top.funning.library.Utils.ServiceUtils;
import top.funning.library.Utils.TextUtils;

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
        GroupOrderDAL goDal = session.getMapper(GroupOrderDAL.class);
        GroupOrder go = goDal.getByUserId(id, userId);


        Data data = new Data(go);

        if (go.getState() == 2) {
            UserDAL uDal = session.getMapper(UserDAL.class);
            User user = uDal.getUser(userId);
            data.setNickName(user.getNickName());

            GroupGoodDAL ggDal = session.getMapper(GroupGoodDAL.class);
            GroupGood gg = ggDal.get(go.getGroupGoodId());
            data.setShareImageUrl(gg.getShareImageUrl());
        }

        this.data = data;


        ServiceUtils.createSuccess(this);
    }


    public static class Data {
        private String id;
        private String price;
        private Date getTimeStart;
        private Date getTimeStop;
        private String groupNum;
        private String groupGoodId;
        private String name;
        private String description;
        private String imageUrl;
        private String state;
        private String stateStr;
        private Date createDT;
        private Date payDT;
        private String teamId;

        private String shareImageUrl;
        private String nickName;

        public Data(GroupOrder go) {
            setId(go.getId());
            setPrice(go.getPrice());
            setGetTimeStart(go.getGetTimeStart());
            setGetTimeStop(go.getGetTimeStop());
            setGroupNum(go.getGroupNum());
            setGroupGoodId(go.getGroupGoodId());
            setName(go.getName());
            setDescription(go.getDescription());
            setImageUrl(go.getImageUrl());
            setState(go.getState());
            setCreateDT(go.getCreateDT());
            setPayDT(go.getPayDT());
            setTeamId(go.getTeamId());
        }

        public String getShareImageUrl() {
            return shareImageUrl;
        }

        public void setShareImageUrl(String shareImageUrl) {
            this.shareImageUrl = shareImageUrl;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

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

        public String getState() {
            return state;
        }

        public void setState(int state) {
            this.state = String.valueOf(state);
            this.stateStr = ServiceUtils.getGroupOrderStateText(state);
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
