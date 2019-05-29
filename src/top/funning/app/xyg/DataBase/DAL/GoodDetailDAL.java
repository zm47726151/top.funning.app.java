package top.funning.app.xyg.DataBase.DAL;

import org.apache.ibatis.annotations.*;
import top.funning.app.xyg.DataBase.Table.GoodDetail;

public interface GoodDetailDAL {

    @Select("select id,content,goodId from `GoodDetail` where id=#{id} limit 1 ")
    GoodDetail getByGoodId(String id);

    @Update("update  `GoodDetail` set content=#{content} where goodId=#{goodId} ")
    int update(GoodDetail goodDetail);

    @Insert("insert into `GoodDetail` (content,goodId) values (#{content},#{goodId})")
    int insert(GoodDetail goodDetail);

    @Delete("delete from `GoodDetail` where `goodId`=#{goodId}")
    int deleteByGoodId(@Param("goodId") String goodId);
}
