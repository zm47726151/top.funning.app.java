package top.knxy.fruits.DataBase.DAL;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import top.knxy.fruits.DataBase.Table.GoodType;
import top.knxy.fruits.Service.Normal.Good.Get.M1013;

import java.util.List;

public interface GoodTypeDAL {

    @Insert("insert into `GoodType`(name,state) values(#{name},1) ")
    public int insert(String name);

    @Select("select id,name,state from `GoodType`")
    public List<GoodType> getList();

    @Select("select id,name from `GoodType` where state = 1")
    public List<M1013.GoodType> getUsefulList();

    @Update("update `GoodType` set name=#{name},state=#{state} where id=#{id}")
    public int update(GoodType type);

    @Delete("delete from `GoodType` where id=#{id}")
    public int delete(String id);
}
