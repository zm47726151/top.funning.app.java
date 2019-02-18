package top.knxy.fruits.WxMiniApi.Service.Address.Modify;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.WxMiniApi.DataBase.Bean.Address;
import top.knxy.fruits.WxMiniApi.DataBase.MyBatisUtils;
import top.knxy.fruits.WxMiniApi.Service.Address.DataSource;
import top.knxy.fruits.WxMiniApi.Service.BaseService;
import top.knxy.fruits.WxMiniApi.Service.ServicelUtils;
import top.knxy.fruits.WxMiniApi.Utils.StrUtils;

public class C1006 extends BaseService {

    public String id;
    public String userId;
    public String area ;
    public String address;
    public String phone ;
    public String nickname;
    public String state;

    @Override
    public void run() throws Exception {
        if (StrUtils.isEmpty(id)) {
            ServicelUtils.createError(this, "id is empty");
            return;
        }

        if (StrUtils.isEmpty(userId)) {
            ServicelUtils.createError(this, "userId is empty");
            return;
        }

        if (StrUtils.isEmpty(area)) {
            ServicelUtils.createError(this, "area is empty");
            return;
        }

        if (StrUtils.isEmpty(address)) {
            ServicelUtils.createError(this, "address is empty");
            return;
        }

        if (StrUtils.isEmpty(phone)) {
            ServicelUtils.createError(this, "phone is empty");
            return;
        }

        if (StrUtils.isEmpty(nickname)) {
            ServicelUtils.createError(this, "nickname is empty");
            return;
        }

        if (StrUtils.isEmpty(state)) {
            ServicelUtils.createError(this, "state is empty");
            return;
        }

        Address address = new Address();
        address.setId(id);
        address.setUserId(userId);
        address.setArea(area);
        address.setAddress(this.address);
        address.setPhone(phone);
        address.setNickname(phone);
        address.setState(state);

        SqlSession session = MyBatisUtils.getSession();
        DataSource ds = session.getMapper(DataSource.class);
        ds.update(address);

        ServicelUtils.createSuccess(this);
    }
}
