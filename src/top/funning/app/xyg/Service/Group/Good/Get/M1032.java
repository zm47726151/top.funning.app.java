package top.funning.app.xyg.Service.Group.Good.Get;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.GroupGoodDAL;
import top.knxy.library.BaseService;
import top.knxy.library.ServiceException;
import top.knxy.library.Utils.ServiceUtils;
import top.knxy.library.Utils.TextUtils;

import java.util.Date;

public class M1032 extends BaseService {

    public String id;

    @Override
    protected void run() throws Exception {
        if (TextUtils.isEmpty(id)) {
            throw new ServiceException("no id");
        }

        SqlSession session = getSqlSession();
        GroupGoodDAL ggDal = session.getMapper(GroupGoodDAL.class);
        GroupGood good = ggDal.getByAdmin(id);
        this.data = good;

        ServiceUtils.createSuccess(this);
    }

    public static class GroupGood {
        private String id;
        private String name;
        private String description;
        private String imageUrl;
        private String price;
        private String detail;
        private String groupNum;
        private Date stopTime;
        private Date getTimeStart;
        private Date getTimeStop;
        private String state;
        private String stateStr;
        private String shareImageUrl;
        private String goodImageUrl;

        public GroupGood() {

        }

        public void setStateStr(String stateStr) {
            this.stateStr = stateStr;
        }

        public String getGoodImageUrl() {
            return goodImageUrl;
        }

        public void setGoodImageUrl(String goodImageUrl) {
            this.goodImageUrl = goodImageUrl;
        }

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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
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

        public String getShareImageUrl() {
            return shareImageUrl;
        }

        public void setShareImageUrl(String shareImageUrl) {
            this.shareImageUrl = shareImageUrl;
        }
    }
}
