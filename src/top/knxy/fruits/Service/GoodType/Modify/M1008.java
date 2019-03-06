package top.knxy.fruits.Service.GoodType.Modify;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.MyBatisUtils;
import top.knxy.fruits.DataBase.Table.GoodType;
import top.knxy.fruits.Service.BaseService;
import top.knxy.fruits.DataBase.DAL.GoodTypeDAL;
import top.knxy.fruits.Utils.ServiceUtils;
import top.knxy.fruits.Utils.StrUtils;

public class M1008 extends BaseService {

    public String id;
    public String name;
    public String state;

    @Override
    protected void run() throws Exception {
        if (!StrUtils.isNumeric(id)) {
            ServiceUtils.createError(this);
            return;
        }

        if (StrUtils.isEmpty(name)) {
            ServiceUtils.createError(this);
            return;
        }

        if (!StrUtils.isNumeric(state)) {
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