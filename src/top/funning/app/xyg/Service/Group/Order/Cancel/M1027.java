package top.funning.app.xyg.Service.Group.Order.Cancel;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.GroupOrderDAL;
import top.funning.app.xyg.DataBase.Table.GroupOrder;
import top.funning.library.BaseService;
import top.funning.library.ServiceException;
import top.funning.library.Utils.ServiceUtils;
import top.funning.library.Utils.TextUtils;

public class M1027 extends BaseService {

    public String id;

    @Override
    protected void run() throws Exception {
        if (TextUtils.isEmpty(id)) {
            throw new ServiceException("no id");
        }

        SqlSession session = getSqlSession();
        GroupOrderDAL goDal = session.getMapper(GroupOrderDAL.class);
        GroupOrder go = goDal.getById(id);
        if (go == null) {
            throw new ServiceException("no order");
        }

        if (go.getState() != 1) {
            throw new ServiceException("state != 1");
        }

        int row = goDal.updateState(id, "6");
        session.commit();

        if (row < 1) {
            throw new ServiceException("row < 1");
        }

        ServiceUtils.createSuccess(this);
    }
}
