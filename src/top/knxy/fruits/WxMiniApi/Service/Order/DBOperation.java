package top.knxy.fruits.WxMiniApi.Service.Order;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import top.knxy.fruits.WxMiniApi.DataBase.Bean.Address;
import top.knxy.fruits.WxMiniApi.DataBase.Bean.Good;
import top.knxy.fruits.WxMiniApi.DataBase.Bean.Order;

import java.util.List;

public interface DBOperation {

    @Select("select id,name,description,imageUrl,price,stock,type from good where id=#{id} and state = 1")
    public Good getGood(String id);

    @Insert("insert into `Order`(id,goods,price,createDT,userId,state)" +
            " values(#{id},#{goods},#{price},#{createDT},#{userId},#{state})")
    public int insert(Order order);

    @Select("select id,userId,price from `Order` where id = #{id} and userId = #{userId}")
    public Order getOrder(Order order);

    @Update("update `Order` set note = #{note}," +
            "poster = #{poster}," +
            "amount = #{amount}," +
            "provinceName = #{provinceName}," +
            "cityName = #{cityName}," +
            "countyName = #{countyName}," +
            "detailInfo = #{detailInfo}," +
            "telNumber = #{telNumber}," +
            "userName = #{userName}," +
            "nationalCode = #{nationalCode}," +
            "postalCode = #{postalCode}," +
            "payDT = #{payDT}," +
            "userName = #{userName}," +
            "state = #{state} " +
            "where id = #{id} and userId = #{userId}")
    public int update(Order order);
}
