package top.knxy.fruits.DataBase.DAL;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import top.knxy.fruits.DataBase.Table.GroupOrder;
import top.knxy.fruits.Service.Order.Group.GetResult.C1019;
import top.knxy.fruits.Service.Order.Group.List.C1020;

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
    GroupOrder getByUserId(@Param("id") String id, @Param("userId") String userId);

    @Select("SELECT * FROM `GroupOrder` WHERE id=#{id}")
    GroupOrder getById(@Param("id") String id);

    @Select("SELECT count(*) FROM `GroupOrder` WHERE id=#{id} and userId=#{userId} and state=2")
    int count(@Param("id") String id, @Param("teamId") String teamId);

    @Select("SELECT count(*) FROM `GroupOrder` WHERE teamId=#{teamId} and userId=#{userId} and state=2")
    int countUserInTeam(@Param("userId") String userId, @Param("teamId") String teamId);

    @Select("select u.id,u.avatarUrl,u.nickName from `GroupOrder` go,`User` u where go.teamId=#{teamId} and  go.userId=u.id and go.state=2")
    List<C1019.Data.User> getUserAvatarList(String teamId);

    @Select("select go.*,gg.shareImageUrl from `GroupOrder` go, `GroupGood` gg where go.userId=#{userId} and go.groupGoodId=gg.id order by go.createDT desc")
    List<C1020.Data.GroupOrder> getList(String userId);

    @Update("update `GroupOrder` set state=#{state} where id=#{id}")
    int updateState(@Param("id") String id, @Param("state") String state);


    @Update("update `GroupOrder` set state=#{state} , payDT=#{payDT} where id=#{id}")
    int payFinish(GroupOrder order);
}
