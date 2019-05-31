package top.funning.app.xyg.DataBase.Table;

import top.knxy.library.Bean.BaseTable;

import java.util.Date;

public class Admin extends BaseTable {


    private int id;
    private String username;
    private String password;
    private int fail;
    private Date lastFailTime;
    private String salt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getFail() {
        return fail;
    }

    public void setFail(int fail) {
        this.fail = fail;
    }

    public Date getLastFailTime() {
        return lastFailTime;
    }

    public void setLastFailTime(Date lastFailTime) {
        this.lastFailTime = lastFailTime;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
