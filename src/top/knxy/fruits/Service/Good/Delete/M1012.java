package top.knxy.fruits.Service.Good.Delete;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.DAL.GoodDAL;
import top.knxy.fruits.DataBase.MyBatisUtils;
import top.knxy.fruits.Service.BaseService;
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


        SqlSession session = MyBatisUtils.getSession();
        GoodDAL dal = session.getMapper(GoodDAL.class);
        int result = dal.delete(id);
        session.commit();
        session.close();
        if (result < 1) {
            ServiceUtils.createError(this, "删除失败");
            return;
        }
        ServiceUtils.createSuccess(this);
    }


}
