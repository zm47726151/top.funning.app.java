package top.funning.app.xyg.DataBase.DAL;

import org.apache.ibatis.annotations.*;
import top.funning.app.xyg.DataBase.Model.Page;
import top.funning.app.xyg.DataBase.Table.GroupGood;
import top.funning.app.xyg.Service.Group.Good.Get.M1032;
import top.funning.app.xyg.Service.Group.Good.List.C1014;
import top.funning.app.xyg.Service.Group.Good.List.M1030;

import java.util.List;

public interface GroupGoodDAL {
    @Select("select id,imageUrl,name,description,price,groupNum from `GroupGood` where state=1 and stopTime>now()")
    List<C1014.Data.GroupGood> getListByClient();

    @Select("select id,state,getTimeStop,getTimeStart,stopTime,groupNum,price,name from `GroupGood` order by stopTime desc limit #{index},#{size}")
    List<M1030.Data.GroupGood> getListByAdmin(Page page);

    @Select("select id,name,state,imageUrl,shareImageUrl,detail,name,description,price,groupNum,stopTime,getTimeStart,getTimeStop,goodImageUrl from `GroupGood` where id=#{id} limit 1")
    GroupGood get(@Param("id") String id);

    @Select("select * from `GroupGood` where id=#{id} limit 1")
    M1032.GroupGood getByAdmin(@Param("id") String id);

    @Delete("delete from `GroupGood` where id=#{id} ")
    int delete(String id);

    @Update("update `GroupGood` set shareImageUrl=#{shareImageUrl},imageUrl=#{imageUrl},detail=#{detail},state=#{state} where id=#{id}")
    int update(GroupGood gg);

    @Insert("insert into `GroupGood`(id,name,description,imageUrl,price,detail,groupNum,stopTime,getTimeStart,getTimeStop,state,shareImageUrl,goodImageUrl)" +
            "values(#{id},#{name},#{description},#{imageUrl},#{price},#{detail},#{groupNum},#{stopTime},#{getTimeStart},#{getTimeStop},#{state},#{shareImageUrl},#{goodImageUrl})")
    int insert(GroupGood order);
}
