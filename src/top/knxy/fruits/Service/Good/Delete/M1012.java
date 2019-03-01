package top.knxy.fruits.Service.Good.Delete;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.DAL.GoodDAL;
import top.knxy.fruits.DataBase.DAL.GoodDetailDAL;
import top.knxy.fruits.DataBase.MyBatisUtils;
import top.knxy.fruits.DataBase.Table.GoodDetail;
import top.knxy.fruits.Service.BaseService;
import top.knxy.fruits.Service.ServiceException;
import top.knxy.fruits.Utils.ServiceUtils;
import top.knxy.fruits.Utils.TextUtils;

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
