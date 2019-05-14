package top.knxy.fruits.Service.Group.Order.Refund.Client;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.DAL.GroupOrderDAL;
import top.knxy.fruits.DataBase.Table.GroupOrder;
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

        //TODO
        ServiceUtils.createSuccess(this);

    }
}
