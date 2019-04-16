package top.knxy.fruits.Service.GoodType.Add;

import org.apache.ibatis.session.SqlSession;
import top.knxy.library.Utils.MyBatisUtils;
import top.knxy.library.BaseService;
import top.knxy.fruits.DataBase.DAL.GoodTypeDAL;
import top.knxy.library.Utils.ServiceUtils;
import top.knxy.library.Utils.TextUtils;

public class M1006 extends BaseService {

    public String name;

    @Override
    protected void run() throws Exception {
        if (TextUtils.isNumeric(name)) {
            ServiceUtils.createError(this);
            return;
        }

        SqlSession session = MyBatisUtils.getSession();
        GoodTypeDAL operation = session.getMapper(GoodTypeDAL.class);
        int row = operation.insert(name);
        session.commit();
        if (row < 1) {
            ServiceUtils.createError(this, "添加失败");
            return;
        }
        ServiceUtils.createSuccess(this);
        session.close();
    }
}
