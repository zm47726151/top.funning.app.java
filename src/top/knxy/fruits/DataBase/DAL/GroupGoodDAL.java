package top.knxy.fruits.DataBase.DAL;

import org.apache.ibatis.annotations.Select;

public interface GroupGood {
    @Select("select id,imageUrl,name,description,price from `GroupGood` where state=1 and stopTime>now()")
    void getList();
}
