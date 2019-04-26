package top.knxy.fruits.Service.Group.Get;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.DAL.GroupGoodDAL;
import top.knxy.fruits.DataBase.Table.Group111Good;
import top.knxy.library.BaseService;
import top.knxy.library.ServiceException;
import top.knxy.library.Utils.MyBatisUtils;
import top.knxy.library.Utils.ServiceUtils;
import top.knxy.library.Utils.TextUtils;

public class C1015 extends BaseService {

    public String id;

    @Override
    protected void run() throws Exception {
        if (TextUtils.isEmpty(id)) {
            throw new ServiceException();
        }

        SqlSession session = MyBatisUtils.getSession();
        GroupGoodDAL dal = session.getMapper(GroupGoodDAL.class);
        Group111Good g = dal.get(id);
        this.data = g;
        session.close();
        ServiceUtils.createSuccess(this);
    }

}
