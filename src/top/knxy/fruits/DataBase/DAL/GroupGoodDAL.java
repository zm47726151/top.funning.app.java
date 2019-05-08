package top.knxy.fruits.DataBase.DAL;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.knxy.fruits.DataBase.Table.GroupGood;
import top.knxy.fruits.Service.Group.List.C1014;

import java.util.List;

public interface GroupGoodDAL {
    @Select("select id,imageUrl,name,description,price,groupNum from `GroupGood` where state=1 and stopTime>now()")
    List<C1014.Data.GroupGood> getList();

    @Select("select id,name,imageUrl,shareImageUrl,detail,name,description,price,groupNum,stopTime,getTimeStart,getTimeStop from `GroupGood` where id=#{id} limit 1")
    GroupGood get(@Param("id") String id);

}
