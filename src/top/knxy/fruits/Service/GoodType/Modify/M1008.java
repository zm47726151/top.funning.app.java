package top.knxy.fruits.Service.GoodType.Modify;

import org.apache.ibatis.session.SqlSession;
import top.knxy.library.Utils.MyBatisUtils;
import top.knxy.fruits.DataBase.Table.GoodType;
import top.knxy.library.BaseService;
import top.knxy.fruits.DataBase.DAL.GoodTypeDAL;
import top.knxy.library.Utils.ServiceUtils;
import top.knxy.library.Utils.TextUtils;

public class M1008 extends BaseService {

    public String id;
    public String name;
    public String state;

    @Override
    protected void run() throws Exception {
        if (!TextUtils.isNumeric(id)) {
            ServiceUtils.createError(this);
            return;
        }

        if (TextUtils.isEmpty(name)) {
            ServiceUtils.createError(this);
            return;
        }

        if (!TextUtils.isNumeric(state)) {
            ServiceUtils.createError(this);
            return;
        }

        SqlSession session = MyBatisUtils.getSession();
        GoodTypeDAL opreation = session.getMapper(GoodTypeDAL.class);
        GoodType type = new GoodType();
        type.setId(Integer.valueOf(id));
        type.setName(name);
        type.setState(Integer.valueOf(state));
        opreation.update(type);
        session.commit();
        session.close();

        ServiceUtils.createSuccess(this);
    }
}
