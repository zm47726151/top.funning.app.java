package top.funning.app.xyg.Service.Group.Order.GetResult;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.GroupGoodDAL;
import top.funning.app.xyg.DataBase.DAL.GroupOrderDAL;
import top.funning.app.xyg.DataBase.Table.GroupGood;
import top.funning.app.xyg.DataBase.Table.GroupOrder;
import top.funning.library.BaseService;
import top.funning.library.ServiceException;
import top.funning.library.Utils.ServiceUtils;
import top.funning.library.Utils.TextUtils;

import java.util.Date;
import java.util.List;

public class C1019 extends BaseService {
    public String userId;
    public String id;

    @Override
    protected void run() throws Exception {
        if (TextUtils.isEmpty(id)) {
            throw new ServiceException("id is empty");
        }

        SqlSession session = getSqlSession();
        GroupOrderDAL goDal = session.getMapper(GroupOrderDAL.class);

        GroupOrder go;
        go = goDal.getByUserId(id, userId);
        if (go == null) {
            throw new ServiceException("order is empty");
        }

        if (2 != go.getState()) {
            ServiceUtils.response(this, -1001, "未收款");
            return;
        }
        Data data = new Data();
        data.id = go.getId();
        data.teamId = go.getTeamId();
        data.goodName = go.getName();
        data.imageUrl = go.getImageUrl();
        data.price = go.getPrice();

        GroupGoodDAL ggDal = session.getMapper(GroupGoodDAL.class);
        GroupGood good = ggDal.get(go.getGroupGoodId());
        data.shareImageUrl = good.getShareImageUrl();
        data.stopTime = good.getStopTime();
        data.groupNum = good.getGroupNum();

        data.userList = goDal.getUserAvatarList(go.getTeamId());

        for (Data.User user : data.userList) {
            if (user.id.equals(userId)) {
                data.nickName = user.nickName;
                break;
            }
        }

        this.data = data;
        ServiceUtils.createSuccess(this);
    }


    public static class Data {
        public String id;
        public String teamId;

        public String goodName;
        public String shareImageUrl;
        public String imageUrl;
        public Date stopTime;
        public String price;

        public String groupNum;
        public String nickName;

        public List<User> userList;

        public static class User {
            public String id;
            public String avatarUrl;
            public String nickName;
        }
    }
}
