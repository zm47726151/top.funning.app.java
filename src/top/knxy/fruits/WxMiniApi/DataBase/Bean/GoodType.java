package top.knxy.fruits.WxMiniApi.DataBase.Bean;

import top.knxy.fruits.WxMiniApi.DataBase.BaseBean;

public class GoodType  extends BaseBean {
    private String id ;
    private String name;
    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
