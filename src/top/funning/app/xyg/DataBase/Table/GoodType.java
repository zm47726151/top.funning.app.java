package top.funning.app.xyg.DataBase.Table;

import top.knxy.library.Bean.BaseTable;

public class GoodType  extends BaseTable {
    private int id ;
    private String name;
    private int state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
