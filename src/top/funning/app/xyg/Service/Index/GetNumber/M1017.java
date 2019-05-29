package top.funning.app.xyg.Service.Index.GetNumber;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.GroupOrderDAL;
import top.funning.app.xyg.DataBase.DAL.OrderDAL;
import top.funning.library.BaseService;
import top.funning.library.Utils.ServiceUtils;

public class M1017 extends BaseService {
    @Override
    protected void run() throws Exception {
        SqlSession session = getSqlSession();
        Data data = new Data();
        this.data = data;

        OrderDAL oDal = session.getMapper(OrderDAL.class);
        data.normalUnDoCount = String.valueOf(oDal.getUnDoNumber());

        GroupOrderDAL goDal = session.getMapper(GroupOrderDAL.class);
        data.groupUnDoCount = String.valueOf(goDal.countUnDo());

        ServiceUtils.createSuccess(this);
    }

    public static class Data {
        public String normalUnDoCount;
        public String groupUnDoCount;
    }
}
