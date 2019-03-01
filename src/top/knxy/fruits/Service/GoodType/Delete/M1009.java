package top.knxy.fruits.Service.GoodType.Delete;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.DAL.GoodTypeDAL;
import top.knxy.fruits.DataBase.MyBatisUtils;
import top.knxy.fruits.Service.BaseService;
import top.knxy.fruits.Utils.ServiceUtils;
import top.knxy.fruits.Utils.TextUtils;

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
