package top.knxy.fruits.Service.Manager.Order;

import org.apache.ibatis.annotations.Select;
import top.knxy.fruits.Servlet.Model.Page;
import top.knxy.fruits.DataBase.Table.Order;

import java.util.List;

public interface DBOperation {

    @Select("Select id,price,poster,amount,telNumber,userName,state,userId,createDT,payDT " +
            "from `order` order by createDT desc limit #{index},#{size}")
    public List<Order> getList(Page page);

}
