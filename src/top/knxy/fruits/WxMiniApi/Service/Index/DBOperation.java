package top.knxy.fruits.WxMiniApi.Service.Index;

import org.apache.ibatis.annotations.Select;
import top.knxy.fruits.WxMiniApi.DataBase.Bean.Good;
import top.knxy.fruits.WxMiniApi.DataBase.Bean.GoodType;

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
