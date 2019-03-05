package top.knxy.fruits.DataBase.DAL;

import org.apache.ibatis.annotations.*;
import top.knxy.fruits.DataBase.Table.Admin;
import top.knxy.fruits.DataBase.Table.User;

public interface LoginDAL {

    @Select("select id,username,password,fail,lastFailTime,salt from Admin where username=#{username} limit 1")
    public Admin getAdmin(String username);

    @Update("update Admin set fail=#{fail}, lastFailTime=#{lastFailTime} where id = #{id}")
    public void update(Admin admin);

    @Select({"select id,openId from User where openId = #{openId} limit 1"})
    public User getUser(String openId);

    @Insert({"insert into User(openId) values(#{openId})"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", resultType = Integer.class, before = false)
    public int insert(User user);
}
