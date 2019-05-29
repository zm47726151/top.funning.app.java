package top.funning.app.xyg.Service.Group.Good.Get;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.GroupGoodDAL;
import top.funning.app.xyg.DataBase.Table.GroupGood;
import top.funning.library.BaseService;
import top.funning.library.ServiceException;
import top.funning.library.Utils.LogUtils;
import top.funning.library.Utils.ServiceUtils;
import top.funning.library.Utils.TextUtils;

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

        LogUtils.i("C1015", g.getState());
        if ("2".equals(g.getState())) {
            ServiceUtils.response(this, 1002, "商品已经下架");
            return;
        }

        if (g.getStopTime().getTime() < new Date().getTime()) {
            ServiceUtils.response(this, 1001, "活动已经过期了 -.-!");
            return;
        }
        this.data = g;
        ServiceUtils.createSuccess(this);
    }
}
