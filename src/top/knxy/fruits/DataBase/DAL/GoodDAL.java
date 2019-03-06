package top.knxy.fruits.DataBase.DAL;

import org.apache.ibatis.annotations.Select;
import top.knxy.fruits.DataBase.Model.Page;
import top.knxy.fruits.DataBase.Table.Good;
import top.knxy.fruits.Service.Good.Bean.Detail;
import top.knxy.fruits.Service.Good.List.M1010;

import java.util.List;

public interface GoodDAL {

    @Select("select g.id,g.name,g.description,g.imageUrl,g.price,gd.content " +
            "from Good g, GoodDetail gd " +
            "where g.id=#{id} and g.id = gd.goodId " +
            "limit 1 ")
    public Detail getDetail(String id);

    @Select("select id,name,description,imageUrl,price,stock,type from `Good` where id=#{id} and state = 1")
    public Good get(String id);

    @Select("select g.id,g.name,g.price,g.type,gt.name typeName,g.state " +
            "from `Good` g left join `GoodType` gt on gt.id = g.type " +
            "limit #{index},#{size}")
    public List<M1010.Data.Good> getList(Page page);
}
