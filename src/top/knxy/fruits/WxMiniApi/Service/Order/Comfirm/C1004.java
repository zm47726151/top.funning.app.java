package top.knxy.fruits.WxMiniApi.Service.Order.Comfirm;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.WxMiniApi.DataBase.Table.Order;
import top.knxy.fruits.WxMiniApi.DataBase.MyBatisUtils;
import top.knxy.fruits.WxMiniApi.Service.BaseService;
import top.knxy.fruits.WxMiniApi.Service.Order.DBOperation;
import top.knxy.fruits.WxMiniApi.Service.ServicelUtils;
import top.knxy.fruits.WxMiniApi.Utils.StrUtils;

import java.math.BigDecimal;
import java.util.Date;

public class C1004 extends BaseService {

    public String userId;
    public String id;
    public String note;
    public Address address;

    public static class Address {
        public String provinceName;
        public String cityName;
        public String countyName;
        public String detailInfo;
        public String telNumber;
        public String userName;
        public String nationalCode;
        public String postalCode;
    }


    @Override
    public void run() throws Exception {
        if (address == null) {
            ServicelUtils.createError(this,"没有地址");
            return;
        }

        if (StrUtils.isEmpty(address.userName)) {
            ServicelUtils.createError(this,"没有姓名");
            return;
        }

        if (StrUtils.isEmpty(address.telNumber)) {
            ServicelUtils.createError(this,"没有电话号码");
            return;
        }

        if (StrUtils.isEmpty(address.detailInfo)) {
            ServicelUtils.createError(this,"没有详细地址");
            return;
        }
        if (StrUtils.isEmpty(address.provinceName)) {
            ServicelUtils.createError(this,"没有区域");
            return;
        }
        if (StrUtils.isEmpty(address.cityName)) {
            ServicelUtils.createError(this,"没有城市");
            return;
        }
        if (StrUtils.isEmpty(address.provinceName)) {
            ServicelUtils.createError(this,"没有省份");
            return;
        }

        SqlSession session = MyBatisUtils.getSession();
        DBOperation mapper = session.getMapper(DBOperation.class);

        String poster = "6";
        Order order = new Order();
        order.setId(id);
        order.setUserId(userId);
        order = mapper.getOrder(order);
        if(order == null){
            ServicelUtils.createError(this,"没有订单");
            return;
        }

        order.setNote(note);
        order.setPoster(poster);
        order.setAmount(new BigDecimal(poster).add(new BigDecimal(order.getPrice())).toString());

        order.setProvinceName(address.provinceName);
        order.setCityName(address.cityName);
        order.setCountyName(address.countyName);
        order.setDetailInfo(address.detailInfo);
        order.setTelNumber(address.telNumber);
        order.setUserName(address.userName);
        order.setNationalCode(address.nationalCode);
        order.setPostalCode(address.postalCode);
        order.setPayDT(new Date());
        order.setState(2);

        int result = mapper.update(order);
        session.commit();
        if (result < 1) {
            // TODO
            ServicelUtils.createError(this, "订单修改失败");
            return;
        }

        ServicelUtils.createSuccess(this);
        session.close();
    }

}
