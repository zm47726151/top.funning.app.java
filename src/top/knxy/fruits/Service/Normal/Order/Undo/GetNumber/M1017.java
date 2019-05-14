package top.knxy.fruits.Service.Normal.Order.Undo.GetNumber;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.DAL.OrderDAL;
import top.knxy.library.BaseService;
import top.knxy.library.Utils.ServiceUtils;

public class M1017 extends BaseService {
    @Override
    protected void run() throws Exception {
        SqlSession session = getSqlSession();
        OrderDAL dal = session.getMapper(OrderDAL.class);
        Data data = new Data();
        data.value = String.valueOf(dal.getUnDoNumber());

        this.data = data;


        ServiceUtils.createSuccess(this);
    }

    public static class Data {
        public String value;
    }
}
