package top.knxy.fruits.Service.Order.Undo.GetNumber;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.DAL.OrderDAL;
import top.knxy.fruits.DataBase.MyBatisUtils;
import top.knxy.fruits.Service.BaseService;
import top.knxy.fruits.Utils.ServiceUtils;

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
