package top.knxy.fruits.Service.Good;

import org.apache.ibatis.annotations.Select;
import top.knxy.fruits.Service.Good.Bean.Detail;

public interface DBOperation  {

    @Select("select g.id,g.name,g.description,g.imageUrl,g.price,gd.content " +
            "from Good g, GoodDetail gd " +
            "where g.id=#{id} and g.id = gd.goodId " +
            "limit 1 ")
    public Detail getDetail(String id);

}
