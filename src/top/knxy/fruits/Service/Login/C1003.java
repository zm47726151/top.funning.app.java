package top.knxy.fruits.Service.Login;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.Config.W;
import top.knxy.fruits.DataBase.Table.User;
import top.knxy.fruits.DataBase.MyBatisUtils;
import top.knxy.fruits.Service.BaseService;
import top.knxy.fruits.Utils.ServiceUtils;
import top.knxy.fruits.Utils.StrUtils;
import top.knxy.fruits.Utils.WebUtils;

import java.util.HashMap;
import java.util.Map;

public class C1003 extends BaseService {

    public String jsCode;

    @Override
    public void run() throws Exception {

        if (StrUtils.isEmpty(jsCode)) {
            ServiceUtils.createError(this, "jsCode is empty");
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("appid", W.appid);
        map.put("secret", W.secret);
        map.put("js_code", jsCode);
        map.put("grant_type", "authorization_code");
        String str = WebUtils.requestGet("https://api.weixin.qq.com/sns/jscode2session", map);

        Gson gson = new Gson();
        WeiXinResponseModel wxrp = gson.fromJson(str, WeiXinResponseModel.class);
        if (!StrUtils.isEmpty(wxrp.errcode) && !"0".equals(wxrp.errcode)) {
            ServiceUtils.createError(this, wxrp.errmsg);
            return;
        }

        SqlSession session = MyBatisUtils.getSession();
        DBOperation mapper = session.getMapper(DBOperation.class);
        //1. getGood user info form db
        User user = mapper.get(wxrp.openid);
        if (user == null) {
            //2. 将记录插入到数据库中
            user = new User();
            user.setOpenId(wxrp.openid);
            int result = mapper.insert(user);
            session.commit();
            if (result < 1) {
                ServiceUtils.createError(this, "登录失败");
            }
        }


        this.data = new Data(wxrp, user.getId());
        ServiceUtils.createSuccess(this);
        session.close();
    }

    public static class Data {
        public String openid;
        public String sessionKey;
        public String userId;

        public Data(WeiXinResponseModel wxrp, int id) {
            this.openid = wxrp.openid;
            this.sessionKey = wxrp.sessionKey;
            this.userId = String.valueOf(id);
        }
    }

    public static class WeiXinResponseModel {
        public String openid;
        @SerializedName("session_key")
        public String sessionKey;
        public String unionid;
        public String errcode;
        public String errmsg;
    }
}
