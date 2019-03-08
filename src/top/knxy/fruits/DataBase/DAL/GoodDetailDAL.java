package top.knxy.fruits.DataBase.DAL;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import top.knxy.fruits.DataBase.Table.GoodDetail;

public interface GoodDetailDAL {

    @Select("select id,content,goodId from `GoodDetail` where id=#{id} limit 1 ")
    GoodDetail getByGoodId(String id);

    @Update("update  `goodDetail` set content=#{content} where goodId=#{goodId} ")
    int update(GoodDetail goodDetail);

    @Insert("insert into `goodDetail` (content,goodId) values (#{content},#{goodId})")
    int insert(GoodDetail goodDetail);
}
