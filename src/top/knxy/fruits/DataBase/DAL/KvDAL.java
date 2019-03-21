package top.knxy.fruits.DataBase.DAL;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import top.knxy.fruits.DataBase.Table.Kv;

import java.util.List;
import java.util.Map;

public interface KvDAL {

    @Select("select `key`,`value` from Kv")
    public List<Kv> pull();


    @Select("select `key`,`value` from `Kv` where `key`=#{key} limit 1 ")
    public Kv get(String key);

    @Insert("insert into `Kv`(`key`,`value`) values (#{key},#{value}) ")
    public int put(Kv kv);

    @Delete("delete from `Kv` where `key`=#{key} ")
    public int delete(String key);

    @Update("update `kv` set `value`=#{value} where `key`=#{key}")
    public int update(Kv kv);

}
