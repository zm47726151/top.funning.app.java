package top.knxy.fruits.WxMiniApi.DataBase.Table;

import top.knxy.fruits.WxMiniApi.DataBase.BaseBean;

public class GoodDetail extends BaseBean {
    private int id;
    private String content;
    private int goodId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }
}
