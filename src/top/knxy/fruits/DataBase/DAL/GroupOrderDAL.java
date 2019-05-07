package top.knxy.fruits.DataBase.DAL;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.knxy.fruits.DataBase.Table.GroupOrder;
import top.knxy.fruits.Service.Order.Group.Get.C1017;

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
            "imageUrl) values" +
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
            "#{imageUrl})")
    int create(GroupOrder groupOrder);

    @Select("SELECT id, price, getTimeStart, getTimeStop, groupNum, groupGoodId, name, description, imageUrl, state,  createDT, payDT FROM `GroupOrder` WHERE id=#{id} and userId=#{userId}")
    C1017.GroupOrder get(@Param("id") String id, @Param("userId") String userId);

    @Select("SELECT count(*) FROM `GroupOrder` WHERE id=#{id} and userId=#{userId} and state=2 limit 1")
    int exist(@Param("id") String id, @Param("teamId") String teamId);
}
