package top.knxy.fruits.DataBase.DAL;

import org.apache.ibatis.annotations.Select;
import top.knxy.fruits.DataBase.Table.Good;
import top.knxy.fruits.DataBase.Table.GoodType;
import top.knxy.fruits.Service.User.M1023;

import java.util.List;

public interface UserDAL {


    @Select({"select u.id,u.openId, SUM(o.amount) amount " +
            "from `User` u left join `Order` o " +
            "on u.id = o.userId group by u.id"})
    public List<M1023.Data.User> getList();





}
