package top.knxy.fruits.DataBase.Table;

import top.knxy.library.Bean.BaseTable;

public class User  extends BaseTable {

    private int id;
    private String openId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
