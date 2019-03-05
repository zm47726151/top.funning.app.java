package top.knxy.fruits.Servlet.Admin.GoodType;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.MyBatisUtils;
import top.knxy.fruits.Service.BaseService;
import top.knxy.fruits.DataBase.DAL.OrderDAL;
import top.knxy.fruits.Utils.ServiceUtils;
import top.knxy.fruits.Utils.StrUtils;

public class Delete extends BaseService {
    public String id;

    @Override
    protected void run() throws Exception {
        if (!StrUtils.isNumeric(id)) {
            ServiceUtils.createSuccess(this);
            return;
        }

        SqlSession session = MyBatisUtils.getSession();
        OrderDAL dao = session.getMapper(OrderDAL.class);
        dao.delete(id);
    }
}
