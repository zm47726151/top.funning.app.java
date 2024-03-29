package top.funning.app.xyg.DataBase.DAL;

import org.apache.ibatis.annotations.*;
import top.funning.app.xyg.DataBase.Table.User;
import top.funning.app.xyg.Service.User.M1023;

import java.util.List;

public interface UserDAL {


    @Select({"select u.id,u.openId, SUM(o.amount) amount " +
            "from `User` u left join `Order` o " +
            "on u.id = o.userId and  o.state=3  group by u.id order by u.id desc"})
    List<M1023.Data.User> getList();

    @Select({"select id,openId from `User` where openId = #{openId} limit 1"})
    User getUserByOpenId(String openId);

    @Select({"select * from `User` where id = #{id} limit 1"})
    User getUser(String id);

    @Insert({"insert into User(openId) values(#{openId})"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", resultType = Integer.class, before = false)
    int insert(User user);

    @Update("update `User` set nickName=#{nickName},gender=#{gender},avatarUrl=#{avatarUrl},province=#{province},city=#{city},country=#{country} where id=#{id}")
    void update(User user);
}
