package top.knxy.fruits.Service.Order.Normal.Undo.GetNumber;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.DAL.OrderDAL;
import top.knxy.library.Utils.MyBatisUtils;
import top.knxy.library.BaseService;
import top.knxy.library.Utils.ServiceUtils;

public class M1017 extends BaseService {
    @Override
    protected void run() throws Exception {
        SqlSession session = MyBatisUtils.getSession();
        OrderDAL dal = session.getMapper(OrderDAL.class);
        Data data = new Data();
        data.value = String.valueOf(dal.getUnDoNumber());
        session.close();
        this.data = data;

        session.close();
        ServiceUtils.createSuccess(this);
    }

    public static class Data {
        public String value;
    }
}
