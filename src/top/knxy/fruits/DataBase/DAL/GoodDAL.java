package top.knxy.fruits.DataBase.DAL;

import org.apache.ibatis.annotations.Select;
import top.knxy.fruits.DataBase.Model.Page;
import top.knxy.fruits.DataBase.Table.Good;
import top.knxy.fruits.Service.Good.Bean.Detail;

public interface GoodDAL {

    @Select("select g.id,g.name,g.description,g.imageUrl,g.price,gd.content " +
            "from Good g, GoodDetail gd " +
            "where g.id=#{id} and g.id = gd.goodId " +
            "limit 1 ")
    public Detail getDetail(String id);

    @Select("select id,name,description,imageUrl,price,stock,type from `Good` where id=#{id} and state = 1")
    public Good get(String id);

    @Select("select id,name,description,imageUrl,price,stock,type,state from `Good` limit #{index},#{size}")
    public Good getList(Page page);
}
