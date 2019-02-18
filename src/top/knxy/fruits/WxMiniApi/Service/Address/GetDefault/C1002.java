package top.knxy.fruits.WxMiniApi.Service.Address.GetDefault;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.WxMiniApi.DataBase.MyBatisUtils;
import top.knxy.fruits.WxMiniApi.Service.Address.Address;
import top.knxy.fruits.WxMiniApi.Service.Address.DataSource;
import top.knxy.fruits.WxMiniApi.Service.BaseService;
import top.knxy.fruits.WxMiniApi.Service.ServicelUtils;
import top.knxy.fruits.WxMiniApi.Utils.StrUtils;

public class C1002 extends BaseService {

    public String openId;

    @Override
    public void run() throws Exception {
        if (StrUtils.isEmpty(openId)) {
            ServicelUtils.createError(this, "open id is empty");
            return;
        }

        SqlSession session = MyBatisUtils.getSession();
        DataSource ds = session.getMapper(DataSource.class);
        this.data = ds.getDefault(openId);

        ServicelUtils.createSuccess(this);
    }
}
