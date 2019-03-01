package top.knxy.fruits.Service.Index;

import org.apache.ibatis.annotations.Select;
import top.knxy.fruits.DataBase.Table.Good;
import top.knxy.fruits.DataBase.Table.GoodType;

import java.util.List;

public interface DBOperation {

    @Select("select id,name from GoodType where state = 1")
    public List<GoodType> getGoodTypeList();


    @Select({"select g.id," +
            "g.name," +
            "g.description," +
            "g.imageUrl," +
            "g.price," +
            "g.stock," +
            "g.type " +
            "from Good g,GoodType gd " +
            "where g.type = gd.id and gd.state = 1 and g.state = 1"})
    public List<Good> getGoodList();


}
