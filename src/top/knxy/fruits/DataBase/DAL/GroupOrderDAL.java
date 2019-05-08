package top.knxy.fruits.DataBase.DAL;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.knxy.fruits.DataBase.Table.GroupOrder;
import top.knxy.fruits.Service.Order.Group.Get.C1017;
import top.knxy.fruits.Service.Order.Group.GetResult.C1019;

import java.util.List;

public interface GroupOrderDAL {

    @Insert("insert into GroupOrder(id," +
            "price," +
            "getTimeStart," +
            "getTimeStop," +
            "groupNum," +
            "state," +
            "userId," +
            "createDT," +
            "groupGoodId," +
            "name," +
            "description," +
            "imageUrl," +
            "teamId) values" +
            "(#{id}," +
            "#{price}," +
            "#{getTimeStart}," +
            "#{getTimeStop}," +
            "#{groupNum}," +
            "#{state}," +
            "#{userId}," +
            "#{createDT}," +
            "#{groupGoodId}," +
            "#{name}," +
            "#{description}," +
            "#{imageUrl}," +
            "#{teamId})")
    int create(GroupOrder groupOrder);

    @Select("SELECT id, price, getTimeStart,teamId, getTimeStop, groupNum, groupGoodId, name, description, imageUrl, state,  createDT, payDT FROM `GroupOrder` WHERE id=#{id} and userId=#{userId}")
    C1017.GroupOrder get(@Param("id") String id, @Param("userId") String userId);

    @Select("SELECT count(*) FROM `GroupOrder` WHERE id=#{id} and userId=#{userId} and state=2")
    int count(@Param("id") String id, @Param("teamId") String teamId);

    @Select("select u.id,u.avatarUrl,u.nickName from `GroupOrder` go,`User` u where go.teamId=#{teamId} and  go.userId=u.id and go.state=2")
    List<C1019.Data.User> getUserAvatarList(String teamId);
}
