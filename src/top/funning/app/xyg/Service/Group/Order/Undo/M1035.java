package top.funning.app.xyg.Service.Group.Order.Undo;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.GroupOrderDAL;
import top.funning.app.xyg.DataBase.Model.Page;
import top.funning.app.xyg.Service.Group.Order.List.M1025;
import top.knxy.library.BaseService;
import top.knxy.library.Utils.ServiceUtils;

public class M1035 extends BaseService {

    public String page;

    @Override
    protected void run() throws Exception {
        Page page = new Page(this.page);
        SqlSession session = getSqlSession();
        GroupOrderDAL dal = session.getMapper(GroupOrderDAL.class);

        M1025.Data data = new M1025.Data();
        data.dataList = dal.getUndoList(page);
        this.data = data;
        ServiceUtils.createSuccess(this);
    }
}
