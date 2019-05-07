package top.knxy.fruits.DataBase.Table;

import top.knxy.library.Bean.BaseTable;

import java.util.Date;

public class GroupGood extends BaseTable {
    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private String price;
    private String detail;
    private String groupNum;
    private Date stopTime;
    private Date getTimeStart;
    private Date getTimeStop;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(String groupNum) {
        this.groupNum = groupNum;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public Date getGetTimeStart() {
        return getTimeStart;
    }

    public void setGetTimeStart(Date getTimeStart) {
        this.getTimeStart = getTimeStart;
    }

    public Date getGetTimeStop() {
        return getTimeStop;
    }

    public void setGetTimeStop(Date getTimeStop) {
        this.getTimeStop = getTimeStop;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
