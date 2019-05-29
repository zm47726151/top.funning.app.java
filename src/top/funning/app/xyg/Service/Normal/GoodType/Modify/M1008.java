package top.funning.app.xyg.Service.Normal.GoodType.Modify;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.GoodTypeDAL;
import top.funning.app.xyg.DataBase.Table.GoodType;
import top.funning.library.BaseService;
import top.funning.library.Utils.ServiceUtils;
import top.funning.library.Utils.TextUtils;

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

        SqlSession session = getSqlSession();
        GoodTypeDAL opreation = session.getMapper(GoodTypeDAL.class);
        GoodType type = new GoodType();
        type.setId(Integer.valueOf(id));
        type.setName(name);
        type.setState(Integer.valueOf(state));
        opreation.update(type);
        session.commit();


        ServiceUtils.createSuccess(this);
    }
}
