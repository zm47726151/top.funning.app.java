package top.knxy.fruits.Service.Manager;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import top.knxy.fruits.DataBase.Table.Admin;

public interface DBOperation {

    @Select("select id,username,password,fail,lastFailTime,salt from Admin where username=#{username} limit 1")
    public Admin get(String username);

    @Update("update Admin set fail=#{fail}, lastFailTime=#{lastFailTime} where id = #{id}")
    public void update(Admin admin);
}
