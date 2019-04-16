package top.knxy.fruits.Service.GoodType.Delete;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.DAL.GoodTypeDAL;
import top.knxy.library.Utils.MyBatisUtils;
import top.knxy.library.BaseService;
import top.knxy.library.Utils.ServiceUtils;
import top.knxy.library.Utils.TextUtils;

public class M1009 extends BaseService {
    public String id;

    @Override
    protected void run() throws Exception {
        if (!TextUtils.isNumeric(id)) {
            ServiceUtils.createSuccess(this);
            return;
        }

        SqlSession session = MyBatisUtils.getSession();
        GoodTypeDAL dal = session.getMapper(GoodTypeDAL.class);
        int result = dal.delete(id);
        session.commit();
        session.close();
        if (result < 1) {
            throw new Exception();
        }

        ServiceUtils.createSuccess(this);
    }
}
