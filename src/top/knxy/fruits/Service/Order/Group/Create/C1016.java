package top.knxy.fruits.Service.Order.Group.Create;

import com.google.gson.annotations.SerializedName;
import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.DAL.GroupGoodDAL;
import top.knxy.fruits.DataBase.DAL.GroupOrderDAL;
import top.knxy.fruits.DataBase.DAL.UserDAL;
import top.knxy.fruits.DataBase.Table.GroupGood;
import top.knxy.fruits.DataBase.Table.GroupOrder;
import top.knxy.fruits.DataBase.Table.User;
import top.knxy.library.BaseService;
import top.knxy.library.ServiceException;
import top.knxy.library.Utils.ServiceUtils;
import top.knxy.library.Utils.TextUtils;

import java.util.Date;

public class C1016 extends BaseService {
    public String userId;


    /**
     * orderInfo : {"id":"27"}
     * userInfo : {"nickName":"FaddenYin","gender":1,"language":"zh_CN","city":"Guangzhou","province":"Guangdong","country":"China","avatarUrl":"https://wx.qlogo.cn/mmopen/vi_32/1CgnVtqojktS65cWq3XsJOBU2CeJicv0n83T6uBKfaROZhicDiabQj4ics5y9XmanXQjGyicsVyJdDjoxGWVEqbxhTw/132"}
     */

    @SerializedName("orderInfo")
    public OrderInfo orderInfo;
    @SerializedName("userInfo")
    public UserInfo userInfo;

    @Override
    protected void run() throws Exception {
        if (TextUtils.isEmpty(orderInfo.id)) {
            throw new ServiceException("id is empty");
        }

        SqlSession session = getSqlSession();

        UserDAL uDal = session.getMapper(UserDAL.class);
        User user = new User();
        user.setId(Integer.valueOf(userId));
        user.setAvatarUrl(userInfo.avatarUrl);
        user.setCity(userInfo.city);
        user.setCountry(userInfo.country);
        user.setGender(userInfo.gender);
        user.setNickName(userInfo.nickName);
        user.setProvince(userInfo.province);
        uDal.update(user);
        session.commit();

        GroupGoodDAL ggDal = session.getMapper(GroupGoodDAL.class);
        GroupOrderDAL goDal = session.getMapper(GroupOrderDAL.class);
        GroupGood model = ggDal.get(orderInfo.id);
        if (model == null) {
            throw new ServiceException("not such GroupGood, id = " + orderInfo.id + " , userId = " + userId);
        }

        if (!TextUtils.isEmpty(orderInfo.teamId)) {
            int count = goDal.count(orderInfo.id, orderInfo.teamId);
            if (1 > count) {
                throw new ServiceException("not exist teamId, id = " + orderInfo.id +
                        " , userId = " + userId +
                        " , teamId = " + orderInfo.teamId);
            } else if (Integer.valueOf(model.getGroupNum()) < count) {
                ServiceUtils.response(this, -1001, "已经超过课参团人数");
                return;
            }

            count = goDal.countUserInTeam(userId, orderInfo.teamId);
            if (count > 1) {
                ServiceUtils.response(this, -1002, "你已经参与了该团");
                return;
            }

        } else {
            orderInfo.teamId = ServiceUtils.getUUid();
        }

        GroupOrder go = new GroupOrder();
        go.setId(ServiceUtils.getUUid());
        go.setPrice(model.getPrice());
        go.setGetTimeStart(model.getGetTimeStart());
        go.setGetTimeStop(model.getGetTimeStop());
        go.setGroupNum(model.getGroupNum());
        go.setUserId(userId);
        go.setCreateDT(new Date());
        go.setGroupGoodId(model.getId());
        go.setName(model.getName());
        go.setDescription(model.getDescription());
        go.setImageUrl(model.getImageUrl());
        go.setState(1);
        go.setTeamId(orderInfo.teamId);

        if (goDal.create(go) < 1) {
            throw new ServiceException("create order failure");
        }
        session.commit();

        Data data = new Data();
        data.id = go.getId();
        this.data = data;

        ServiceUtils.createSuccess(this);
    }

    public static class Data {
        public String id;
    }

    public static class Good {
        public String id;
        public String name;
        public String description;
        public String imageUrl;
        public String price;
    }


    public static class OrderInfo {
        /**
         * id : 27
         */

        @SerializedName("id")
        public String id;
        @SerializedName("teamId")
        public String teamId;
    }

    public static class UserInfo {
        /**
         * nickName : FaddenYin
         * gender : 1
         * language : zh_CN
         * city : Guangzhou
         * province : Guangdong
         * country : China
         * avatarUrl : https://wx.qlogo.cn/mmopen/vi_32/1CgnVtqojktS65cWq3XsJOBU2CeJicv0n83T6uBKfaROZhicDiabQj4ics5y9XmanXQjGyicsVyJdDjoxGWVEqbxhTw/132
         */

        @SerializedName("nickName")
        public String nickName;
        @SerializedName("gender")
        public int gender;
        @SerializedName("language")
        public String language;
        @SerializedName("city")
        public String city;
        @SerializedName("province")
        public String province;
        @SerializedName("country")
        public String country;
        @SerializedName("avatarUrl")
        public String avatarUrl;
    }
}
