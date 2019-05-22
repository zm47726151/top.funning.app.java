package top.knxy.fruits.Service.Index.GetNumber;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.DAL.GroupOrderDAL;
import top.knxy.fruits.DataBase.DAL.OrderDAL;
import top.knxy.library.BaseService;
import top.knxy.library.Utils.ServiceUtils;

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
