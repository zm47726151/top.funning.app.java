package top.funning.app.xyg.Service.Normal.Good.Delete;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.Cache.Good;
import top.funning.app.xyg.DataBase.DAL.GoodDAL;
import top.funning.app.xyg.DataBase.DAL.GoodDetailDAL;
import top.knxy.library.BaseService;
import top.knxy.library.ServiceException;
import top.knxy.library.Utils.ServiceUtils;
import top.knxy.library.Utils.TextUtils;

public class M1012 extends BaseService {

    public String id;

    @Override
    protected void run() throws Exception {
        if (TextUtils.isEmpty(id)) {
            ServiceUtils.createError(this);
            return;
        }

        int result;
        SqlSession session = getSqlSession();
        GoodDAL gDal = session.getMapper(GoodDAL.class);
        result = gDal.delete(id);
        session.commit();
        if (result < 1) {

            throw new ServiceException("删除失败 good id = " + id);
        }

        //删除缓存
        Good.clear();

        GoodDetailDAL gdDal = session.getMapper(GoodDetailDAL.class);
        result = gdDal.deleteByGoodId(id);
        session.commit();
        if (result < 1) {

            throw new ServiceException("删除失败 good id = " + id);
        }


        ServiceUtils.createSuccess(this);
    }


}
