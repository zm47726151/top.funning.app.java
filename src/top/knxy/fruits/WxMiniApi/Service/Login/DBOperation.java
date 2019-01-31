package top.knxy.fruits.WxMiniApi.Service.Login;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import top.knxy.fruits.WxMiniApi.DataBase.Bean.User;

public interface DBOperation {

    @Select({"select id,openId from User where openId = #{openId} limit 1"})
    public User get(String openId);

    @Insert({"insert into User(openId) values(#{openId})"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", resultType = Integer.class, before = false)
    public int insert(User user);
}

