package top.knxy.fruits.Service.Order.Group.Create;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.DAL.GroupGoodDAL;
import top.knxy.fruits.DataBase.DAL.GroupOrderDAL;
import top.knxy.fruits.DataBase.Table.GroupGood;
import top.knxy.fruits.DataBase.Table.GroupOrder;
import top.knxy.library.BaseService;
import top.knxy.library.ServiceException;
import top.knxy.library.Utils.MyBatisUtils;
import top.knxy.library.Utils.ServiceUtils;
import top.knxy.library.Utils.TextUtils;

import java.util.Date;

public class C1016 extends BaseService {
    public String id;
    public String teamId;
    public String userId;

    @Override
    protected void run() throws Exception {
        if (TextUtils.isEmpty(id)) {
            throw new ServiceException("id is empty");
        }

        SqlSession session = getSqlSession();
        GroupGoodDAL ggDal = session.getMapper(GroupGoodDAL.class);
        GroupOrderDAL goDal = session.getMapper(GroupOrderDAL.class);
        GroupGood model = ggDal.get(id);
        if (model == null) {

            throw new ServiceException("not such GroupGood, id = " + id + " , userId = " + userId);
        }

        if (!TextUtils.isEmpty(teamId)) {
            if (1 > goDal.exist(id, teamId)) {

                throw new ServiceException("not exist teamId, id = " + id +
                        " , userId = " + userId +
                        " , teamId = " + teamId);
            }
        } else {
            teamId = ServiceUtils.getUUid();
        }

        GroupOrder go = new GroupOrder();
        go.setId(ServiceUtils.getUUid());
        go.setPrice(model.getPrice());
        go.setGetTimeStart(model.getGetTimeStart());
        go.setGetTimeStop(model.getGetTimeStop());
        go.setGroupNum(model.getGroupNum());
        go.setUserId(userId);
        go.setCreateDT(new Date());
        go.setGroupGoodId(model.getId());
        go.setName(model.getName());
        go.setDescription(model.getDescription());
        go.setImageUrl(model.getImageUrl());
        go.setState(1);
        go.setTeamId(teamId);

        if (goDal.create(go) < 1) {

            throw new ServiceException("create order failure");
        }
        session.commit();

        Data data = new Data();
        data.id = go.getId();
        this.data = data;


        ServiceUtils.createSuccess(this);
    }

    public static class Data {
        public String id;
    }

    public static class Good {
        public String id;
        public String name;
        public String description;
        public String imageUrl;
        public String price;
    }
}
