package top.funning.app.xyg.DataBase.Table;

import top.knxy.library.Bean.BaseTable;

import java.util.Date;

public class GroupOrder extends BaseTable {

    private String id;
    private String price;
    private Date getTimeStart;
    private Date getTimeStop;
    private String groupNum;
    private int state;
    private String userId;
    private Date createDT;
    private Date payDT;
    private String teamId;

    private String GroupGoodId;
    private String name;
    private String description;
    private String imageUrl;

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupGoodId() {
        return GroupGoodId;
    }

    public void setGroupGoodId(String groupGoodId) {
        GroupGoodId = groupGoodId;
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

    public Date getCreateDT() {
        return createDT;
    }

    public void setCreateDT(Date createDT) {
        this.createDT = createDT;
    }

    public Date getPayDT() {
        return payDT;
    }

    public void setPayDT(Date payDT) {
        this.payDT = payDT;
    }

    public String getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(String groupNum) {
        this.groupNum = groupNum;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
