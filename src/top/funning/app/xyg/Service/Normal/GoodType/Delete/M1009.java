package top.funning.app.xyg.Service.Normal.GoodType.Delete;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.GoodTypeDAL;
import top.funning.library.BaseService;
import top.funning.library.Utils.ServiceUtils;
import top.funning.library.Utils.TextUtils;

public class M1009 extends BaseService {
    public String id;

    @Override
    protected void run() throws Exception {
        if (!TextUtils.isNumeric(id)) {
            ServiceUtils.createSuccess(this);
            return;
        }

        SqlSession session = getSqlSession();
        GoodTypeDAL dal = session.getMapper(GoodTypeDAL.class);
        int result = dal.delete(id);
        session.commit();

        if (result < 1) {
            throw new Exception();
        }

        ServiceUtils.createSuccess(this);
    }
}
