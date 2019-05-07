package top.knxy.fruits.DataBase.DAL;

import org.apache.ibatis.annotations.*;
import top.knxy.fruits.DataBase.Table.Admin;
import top.knxy.fruits.DataBase.Table.User;

public interface AdminDAL {

    @Select("select id,username,password,fail,lastFailTime,salt from Admin where username=#{username} limit 1")
    public Admin getAdminByUserName(String username);

    @Select("select id,username,password,fail,lastFailTime,salt from Admin where id=#{id} limit 1")
    public Admin getAdminById(String id);

    @Update("update `Admin` set fail=#{fail}, lastFailTime=#{lastFailTime} where id = #{id}")
    public void updateFail(Admin admin);

    @Update("update `Admin` set password=#{password}, salt=#{salt} where id = #{id}")
    public void updatePassword(Admin admin);


}
