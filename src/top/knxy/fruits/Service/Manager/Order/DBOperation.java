package top.knxy.fruits.Service.Manager.Order;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import top.knxy.fruits.DataBase.Model.Page;
import top.knxy.fruits.DataBase.Table.Order;

import java.util.List;

public interface DBOperation {

    @Select("Select id,price,poster,amount,telNumber,userName,state,userId,createDT,payDT " +
            "from `Order` order by createDT desc " +
            "limit #{index},#{size}")
    //@SelectProvider(type = SqlProvider.class, method = "getList")
    public List<Order> getList(Page page);

    @Select("Select id,price,poster,amount,telNumber,userName,state,userId,createDT,payDT " +
            "from `order`  where state=2  order by payDT desc " +
            "limit #{index},#{size}")
    public List<Order> getUndoList(Page page);


    /**
     * Reference:https://www.cnblogs.com/guoyafenghome/p/9123442.html
     */
    class SqlProvider {
        public String getList() {
            String sql =
                    "Select id,price,poster,amount,telNumber,userName,state,userId,createDT,payDT " +
                            "from `Order` order by createDT desc " +
                            "limit #{index},#{size}";

            return sql;
        }
    }
}
