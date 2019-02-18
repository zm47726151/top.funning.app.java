package top.knxy.fruits.WxMiniApi.Service.Address;

public class Address {
    private String id;
    private String area;
    private String address;
    private String phone;
    private String nickname;
    private String state;

    public Address(top.knxy.fruits.WxMiniApi.DataBase.Bean.Address address) {
        this.id = address.getId();
        this.area = address.getArea();
        this.address = address.getAddress();
        this.phone = address.getPhone();
        this.nickname = address.getNickname();
        this.state = address.getState();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
