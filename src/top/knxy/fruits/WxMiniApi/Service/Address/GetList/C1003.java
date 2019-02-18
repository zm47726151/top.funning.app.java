package top.knxy.fruits.WxMiniApi.Service.Address.GetList;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.WxMiniApi.DataBase.MyBatisUtils;
import top.knxy.fruits.WxMiniApi.Service.Address.Address;
import top.knxy.fruits.WxMiniApi.Service.Address.DataSource;
import top.knxy.fruits.WxMiniApi.Service.BaseService;
import top.knxy.fruits.WxMiniApi.Service.ServicelUtils;
import top.knxy.fruits.WxMiniApi.Utils.StrUtils;

import java.util.ArrayList;
import java.util.List;

public class C1003 extends BaseService {

    public String openId;

    @Override
    public void run() throws Exception {
        if (StrUtils.isEmpty(openId)) {
            ServicelUtils.createError(this, "open id is empty");
            return;
        }

        SqlSession session = MyBatisUtils.getSession();
        DataSource ds = session.getMapper(DataSource.class);

        List<top.knxy.fruits.WxMiniApi.DataBase.Bean.Address> addresses = ds.getList(openId);

        Data data = new Data(addresses);
        this.data = data;

        ServicelUtils.createSuccess(this);

    }

    public static class Data {

        public List<Address> dataList;

        public Data(List<top.knxy.fruits.WxMiniApi.DataBase.Bean.Address> addresses) {
            this.dataList = new ArrayList<>(addresses.size());
            for (top.knxy.fruits.WxMiniApi.DataBase.Bean.Address address : addresses) {
                this.dataList.add(new Address(address));
            }
        }
    }
}
