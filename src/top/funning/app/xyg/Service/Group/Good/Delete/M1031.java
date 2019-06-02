package top.funning.app.xyg.Service.Group.Good.Delete;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.GroupGoodDAL;
import top.knxy.library.BaseService;
import top.knxy.library.ServiceException;
import top.knxy.library.Utils.ServiceUtils;

public class M1031 extends BaseService {

    public String id;

    @Override
    protected void run() throws Exception {
        SqlSession sqlSession = getSqlSession();
        GroupGoodDAL ggDal = sqlSession.getMapper(GroupGoodDAL.class);
        int row = ggDal.delete(id);
        sqlSession.commit();
        if (row < 1) {
            throw new ServiceException("row < 1");
        }

        ServiceUtils.createSuccess(this);
    }
}