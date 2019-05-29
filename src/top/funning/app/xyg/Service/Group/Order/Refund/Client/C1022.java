package top.funning.app.xyg.Service.Group.Order.Refund.Client;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.GroupOrderDAL;
import top.funning.app.xyg.DataBase.Table.GroupOrder;
import top.funning.app.xyg.Servlet.Admin.Remind;
import top.knxy.library.BaseService;
import top.knxy.library.ServiceException;
import top.knxy.library.Utils.ServiceUtils;
import top.knxy.library.Utils.TextUtils;

public class C1022 extends BaseService {

    public String userId;
    public String id;

    @Override
    public void run() throws Exception {
        if (TextUtils.isEmpty(id)) {
            ServiceUtils.createError(this);
            return;
        }

        SqlSession session = getSqlSession();
        GroupOrderDAL goDal = session.getMapper(GroupOrderDAL.class);
        GroupOrder go = goDal.getByUserId(id, userId);
        if (2 != go.getState() && 3 != go.getState()) {
            throw new ServiceException();
        }

        int result = goDal.updateState(id, "5");
        session.commit();

        if (result < 1) {
            throw new ServiceException();
        }

        Remind.broadcast();

        ServiceUtils.createSuccess(this);

    }
}
