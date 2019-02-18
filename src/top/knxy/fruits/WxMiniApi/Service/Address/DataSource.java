package top.knxy.fruits.WxMiniApi.Service.Address;

import org.apache.ibatis.annotations.Select;

import top.knxy.fruits.WxMiniApi.DataBase.Bean.Address;

import java.util.List;

public interface DataSource {

    @Select("select id,area,address,phone,nickname,state " +
            "from Address a,User u " +
            "where a.userId = u.userId and u.openId = #openId")
    public List<Address> getList(String openId);

    @Select("select id,area,address,phone,nickname,state " +
            "from Address a,User u " +
            "where a.userId = u.userId and u.openId = #openId")
    public Address getDefault(String openId);

    @Select("delete from Address where id = #id")
    public void delete(String id);

    public void insert(Address address);

    public void update(Address address);
}
