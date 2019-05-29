package top.funning.app.xyg.Service.Group.Order.ToFinish;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.GroupOrderDAL;
import top.funning.app.xyg.DataBase.Table.GroupOrder;
import top.funning.library.BaseService;
import top.funning.library.ServiceException;
import top.funning.library.Utils.ServiceUtils;
import top.funning.library.Utils.TextUtils;

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
