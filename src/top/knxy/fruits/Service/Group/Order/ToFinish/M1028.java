package top.knxy.fruits.Service.Group.Order.ToFinish;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.DAL.GroupOrderDAL;
import top.knxy.fruits.DataBase.Table.GroupOrder;
import top.knxy.library.BaseService;
import top.knxy.library.ServiceException;
import top.knxy.library.Utils.ServiceUtils;
import top.knxy.library.Utils.TextUtils;

public class M1028 extends BaseService {

    public String id;

    @Override
    protected void run() throws Exception {
        if (TextUtils.isEmpty(id)) {
            throw new ServiceException("no id");
        }

        SqlSession session = getSqlSession();
        GroupOrderDAL goDal = session.getMapper(GroupOrderDAL.class);
        GroupOrder order = goDal.getById(id);

        if (order == null) {
            throw new ServiceException("no order");
        }

        if (order.getState() != 3) {
            throw new ServiceException("state != 3");
        }

        int row = goDal.updateState(id, "4");
        session.commit();

        if (row < 1) {
            throw new RuntimeException("row < 1");
        }

        ServiceUtils.createSuccess(this);
    }
}
