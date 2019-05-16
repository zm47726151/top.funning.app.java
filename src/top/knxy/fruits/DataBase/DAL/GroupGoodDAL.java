package top.knxy.fruits.DataBase.DAL;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import top.knxy.fruits.DataBase.Model.Page;
import top.knxy.fruits.DataBase.Table.GroupGood;
import top.knxy.fruits.Service.Group.Good.Get.M1032;
import top.knxy.fruits.Service.Group.Good.List.C1014;
import top.knxy.fruits.Service.Group.Good.List.M1030;

import java.util.Date;
import java.util.List;

public interface GroupGoodDAL {
    @Select("select id,imageUrl,name,description,price,groupNum from `GroupGood` where state=1 and stopTime>now()")
    List<C1014.Data.GroupGood> getListByClient();

    @Select("select id,state,getTimeStop,getTimeStart,stopTime,groupNum,price,name from `GroupGood` order by stopTime desc limit #{index},#{size}")
    List<M1030.Data.GroupGood> getListByAdmin(Page page);

    @Select("select id,name,imageUrl,shareImageUrl,detail,name,description,price,groupNum,stopTime,getTimeStart,getTimeStop from `GroupGood` where id=#{id} limit 1")
    GroupGood get(@Param("id") String id);

    @Select("select * from `GroupGood` where id=#{id} limit 1")
    M1032.GroupGood getByAdmin(@Param("id") String id);

    @Delete("delete from `GroupGood` where id=#{id} ")
    int delete(String id);

    @Update("update `GroupGood` set shareImageUrl=#{shareImageUrl},imageUrl=#{imageUrl},detail=#{detail},state=#{state} where id=#{id}")
    int update(GroupGood gg);
}
