package top.funning.app.xyg.Service.Login.Wechat;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.UserDAL;
import top.funning.app.xyg.DataBase.Table.User;
import top.funning.app.xyg.Config.S;
import top.knxy.library.BaseService;
import top.knxy.library.ServiceException;
import top.knxy.library.Utils.ServiceUtils;
import top.knxy.library.Utils.TextUtils;
import top.knxy.library.Utils.WebUtils;

import java.util.HashMap;
import java.util.Map;

public class C1003 extends BaseService {

    public String jsCode;

    @Override
    public void run() throws Exception {

        if (TextUtils.isEmpty(jsCode)) {
            ServiceUtils.createError(this, "jsCode is empty");
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("appid", S.WeChat.appid);
        map.put("secret", S.WeChat.secret);
        map.put("js_code", jsCode);
        map.put("grant_type", "authorization_code");
        String str = WebUtils.get("https://api.weixin.qq.com/sns/jscode2session", map);

        Gson gson = new Gson();
        WeiXinResponseModel wxrp = gson.fromJson(str, WeiXinResponseModel.class);
        if (!TextUtils.isEmpty(wxrp.errcode) && !"0".equals(wxrp.errcode)) {
            ServiceUtils.createError(this, wxrp.errmsg);
            return;
        }

        SqlSession session = getSqlSession();
        UserDAL mapper = session.getMapper(UserDAL.class);
        //1. getGood user info form db
        User user = mapper.getUserByOpenId(wxrp.openid);
        if (user == null) {
            //2. 将记录插入到数据库中
            user = new User();
            user.setOpenId(wxrp.openid);
            int result = mapper.insert(user);
            session.commit();
            if (result < 1) {

                throw new ServiceException("登录失败");
            }
        }

        this.data = new Data(wxrp, user.getId());
        ServiceUtils.createSuccess(this);

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
