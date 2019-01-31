package top.knxy.fruits.WxMiniApi.Service;

public class SessionInfo {
    public String openid;
    public String sessionKey;
    public String userId;

    public SessionInfo(String openid, String sessionKey, String userId) {
        this.openid = openid;
        this.sessionKey = sessionKey;
        this.userId = userId;
    }
}
