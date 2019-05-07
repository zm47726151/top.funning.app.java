package top.knxy.fruits.Service.Group.Get;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.DAL.GroupGoodDAL;
import top.knxy.fruits.DataBase.Table.GroupGood;
import top.knxy.library.BaseService;
import top.knxy.library.Config.Code;
import top.knxy.library.ServiceException;
import top.knxy.library.Utils.DateUtils;
import top.knxy.library.Utils.MyBatisUtils;
import top.knxy.library.Utils.ServiceUtils;
import top.knxy.library.Utils.TextUtils;

import java.util.Date;

public class C1015 extends BaseService {

    public String id;

    @Override
    protected void run() throws Exception {
        if (TextUtils.isEmpty(id)) {
            throw new ServiceException();
        }

        SqlSession session = getSqlSession();
        GroupGoodDAL dal = session.getMapper(GroupGoodDAL.class);
        GroupGood g = dal.get(id);


        if (g.getStopTime().getTime() < new Date().getTime()) {

            session.close();
            ServiceUtils.response(this, 1001,"活动已经过期了 -.-!");
            return;
        }

        this.data = g;
        session.close();
        ServiceUtils.createSuccess(this);
    }
}
