package top.funning.app.xyg.DataBase.DAL;

import org.apache.ibatis.annotations.*;
import top.funning.app.xyg.DataBase.Model.Page;
import top.funning.app.xyg.DataBase.Table.Order;
import top.funning.library.Utils.TextUtils;

import java.util.List;

public interface OrderDAL {


    @Insert("insert into `Order`(id,goods,price,createDT,userId,state)" +
            " values(#{id},#{goods},#{price},#{createDT},#{userId},#{state})")
    public int insert(Order order);

    @Select("select id,goods,price,poster,amount,provinceName,cityName,countyName," +
            "detailInfo,telNumber,userName,nationalCode,postalCode,note,state,userId,createDT,payDT " +
            "from `Order` where id = #{id} and userId = #{userId} limit 1")
    public Order getOrderByUser(Order order);

    @Select("select id,goods,price,poster,amount,provinceName,cityName,countyName," +
            "detailInfo,telNumber,userName,nationalCode,postalCode,note,state,userId,createDT,payDT " +
            "from `Order` where id=#{id} limit 1")
    public Order getOrder(String id);

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

    @Select("select id,price,poster,amount,telNumber,userName,state,userId,createDT,payDT " +
            "from `Order` where state=2 or state=4 order by payDT desc " +
            "limit #{index},#{size}")
    public List<Order> getUndoList(Page page);

    @Select("select id,goods,price,poster,amount,provinceName,cityName,countyName," +
            "detailInfo,telNumber,userName,nationalCode,postalCode,note,state,userId,createDT,payDT " +
            "from `Order` where userId = #{userId} order by createDT desc")
    public List<Order> getListByUserId(String userId);

    /*@Select("select id,price,poster,amount,telNumber,userName,state,userId,createDT,payDT " +
            "from `Order` order by createDT desc " +
            "limit #{index},#{size}")*/
    @SelectProvider(type = SqlProvider.class, method = "getList")
    public List<Order> getList(@Param("index") int index,
                               @Param("size") int size,
                               @Param("userId") String userId);


    @Select("select id,state from `Order` where id=#{id} limit 1")
    public Order getState(String id);


    @Select("select count(*) from `Order` where state=2 or state=4")
    public int getUnDoNumber();

    @Update("update `Order` set state = #{state} " +
            "where id = #{id}")
    public int changeState(Order order);

    @Update("update `Order` set state = #{state} " +
            "where id = #{id} and userId = #{userId}")
    public int changeStateByUser(Order order);


    /**
     * Reference:https://www.cnblogs.com/guoyafenghome/p/9123442.html
     */
    class SqlProvider {
        public String getList(@Param("index") int index,
                              @Param("size") int size,
                              @Param("userId") String userId) {
            String sql =
                    "select id,price,poster,amount,telNumber,userName,state,userId,createDT,payDT " +
                            "from `Order` "
                            + (TextUtils.isEmpty(userId) ? "" : "where userId = #{userId} ") +
                            "order by createDT desc " +
                            "limit #{index},#{size}";
            return sql;
        }
    }
}
