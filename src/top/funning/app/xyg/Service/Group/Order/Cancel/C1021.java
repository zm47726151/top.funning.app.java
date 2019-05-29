package top.funning.app.xyg.Service.Group.Order.Cancel;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.GroupOrderDAL;
import top.funning.app.xyg.DataBase.Table.GroupOrder;
import top.funning.library.BaseService;
import top.funning.library.ServiceException;
import top.funning.library.Utils.ServiceUtils;
import top.funning.library.Utils.TextUtils;


public class C1021 extends BaseService {

    public String id;
    public String userId;

    @Override
    protected void run() throws Exception {
        if (TextUtils.isEmpty(id)) {
            throw new ServiceException();
        }

        SqlSession session = getSqlSession();
        GroupOrderDAL goDal = session.getMapper(GroupOrderDAL.class);
        GroupOrder go = goDal.getByUserId(id, userId);

        if (go == null) {
            throw new ServiceException();
        }

        if (1 != go.getState()) {
            throw new ServiceException();
        }

        int result = goDal.updateState(id, "6");
        session.commit();

        if (result < 1) {
            throw new ServiceException();
        }

        ServiceUtils.createSuccess(this);
    }
}
