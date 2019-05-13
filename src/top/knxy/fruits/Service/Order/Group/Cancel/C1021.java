package top.knxy.fruits.Service.Order.Group.Cancel;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.DAL.GroupOrderDAL;
import top.knxy.fruits.DataBase.Table.GroupOrder;
import top.knxy.library.BaseService;
import top.knxy.library.ServiceException;
import top.knxy.library.Utils.ServiceUtils;
import top.knxy.library.Utils.TextUtils;


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
