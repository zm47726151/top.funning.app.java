package top.knxy.fruits.Service.Good.Delete;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.Cache.Good;
import top.knxy.fruits.DataBase.DAL.GoodDAL;
import top.knxy.fruits.DataBase.DAL.GoodDetailDAL;
import top.knxy.library.Utils.MyBatisUtils;
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
        SqlSession session = MyBatisUtils.getSession();
        GoodDAL gDal = session.getMapper(GoodDAL.class);
        result = gDal.delete(id);
        session.commit();
        if (result < 1) {
            session.close();
            throw new ServiceException("删除失败 good id = " + id);
        }

        //删除缓存
        Good.clear();

        GoodDetailDAL gdDal = session.getMapper(GoodDetailDAL.class);
        result = gdDal.deleteByGoodId(id);
        session.commit();
        if (result < 1) {
            session.close();
            throw new ServiceException("删除失败 good id = " + id);
        }

        session.close();
        ServiceUtils.createSuccess(this);
    }


}
