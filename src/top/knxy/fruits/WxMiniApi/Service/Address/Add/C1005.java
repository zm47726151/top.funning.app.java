package top.knxy.fruits.WxMiniApi.Service.Address.Add;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.WxMiniApi.DataBase.MyBatisUtils;
import top.knxy.fruits.WxMiniApi.Service.Address.DataSource;
import top.knxy.fruits.WxMiniApi.Service.BaseService;
import top.knxy.fruits.WxMiniApi.Service.ServicelUtils;
import top.knxy.fruits.WxMiniApi.Utils.StrUtils;

public class C1005 extends BaseService {

    public String id;

    @Override
    public void run() throws Exception {
        if (StrUtils.isEmpty(id)) {
            ServicelUtils.createError(this, "id is empty");
            return;
        }

        SqlSession session = MyBatisUtils.getSession();
        DataSource ds = session.getMapper(DataSource.class);
        ds.delete(id);

        ServicelUtils.createSuccess(this);
    }
}
