package top.knxy.fruits.DataBase.DAL;

import org.apache.ibatis.annotations.*;
import top.knxy.fruits.DataBase.Model.Page;
import top.knxy.fruits.DataBase.Table.Good;
import top.knxy.fruits.Service.Good.Get.C1009;
import top.knxy.fruits.Service.Good.Get.M1013;
import top.knxy.fruits.Service.Good.List.M1010;

import java.util.List;

public interface GoodDAL {

    @Select("select g.id,g.name,g.description,g.imageUrl,g.price,gd.content " +
            "from Good g, GoodDetail gd " +
            "where g.id=#{id} and g.id = gd.goodId " +
            "limit 1 ")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "description", property = "description"),
            @Result(column = "imageUrl", property = "imageUrl"),
            @Result(column = "price", property = "price"),
            @Result(column = "content", property = "content")
    })
    public C1009.Good getDetailForUser(String id);


    @Select("select g.id,g.name,g.description,g.imageUrl,g.price,gd.content,g.state,gt.id type, gt.name typeStr " +
            "from Good g left join GoodDetail gd on g.id = gd.goodId left join `GoodType` gt on g.type= gt.id " +
            "where g.id=#{id} " +
            "limit 1")
    public M1013.Good getDetailForAdmin(String id);

    @Select("select id,name,description,imageUrl,price,stock,type from `Good` where id=#{id} and state = 1")
    public Good get(String id);

    @Select("select g.id,g.name,g.price,g.type,gt.name typeName,g.state " +
            "from `Good` g left join `GoodType` gt on gt.id = g.type " +
            "order by g.id desc limit #{index},#{size}")
    public List<M1010.Data.Good> getList(Page page);

    @Delete("delete from `Good` where id = #{id}")
    public int delete(String id);

    @Delete("update `Good` set name=#{name},description=#{description},imageUrl=#{imageUrl},price=#{price},type=#{type},state=#{state} " +
            "where id=#{id}")
    public int update(Good good);

    @Insert("insert into `Good`(id,name,description,imageUrl,price,type,state) values " +
            "(#{id},#{name},#{description},#{imageUrl},#{price},#{type},#{state})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", resultType = Integer.class, before = false)
    public int insert(Good good);
}
