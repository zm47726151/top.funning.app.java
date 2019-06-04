package top.funning.app.xyg.Service.Index.Statistics;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import top.funning.app.xyg.Config.C;
import top.knxy.library.BaseService;
import top.knxy.library.Utils.ServiceUtils;
import top.knxy.library.Utils.WebUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class M1024 extends BaseService {
    @Override
    protected void run() throws Exception {
        Gson gson = new Gson();

        //https://developers.weixin.qq.com/miniprogram/dev/api-backend/getAccessToken.html
        AccessCode accessToken = null;
        {
            Map<String, String> map = new HashMap<>();
            map.put("grant_type", "client_credential");
            map.put("appid", C.WeChat.appid);
            map.put("secret", C.WeChat.secret);
            accessToken = WebUtils.getJson("https://api.weixin.qq.com/cgi-bin/token", map, AccessCode.class);

            if (accessToken.errcode != null && !"0".equals(accessToken.errcode)) {
                ServiceUtils.createError(this, accessToken.errmsg);
                return;
            }
        }

        //https://developers.weixin.qq.com/miniprogram/dev/api-backend/getAnalysisDailyVisitTrend.html
        DailyVisit dailyVisit;
        {
            Map<String, String> map = new HashMap<>();
            map.put("begin_date", "20190421");
            map.put("end_date", "20190421");
            String data = gson.toJson(map);
            dailyVisit = WebUtils.postJson("https://api.weixin.qq.com/datacube/getweanalysisappiddailyvisittrend?access_token=" + accessToken.value, data, DailyVisit.class);
        }

        //https://developers.weixin.qq.com/miniprogram/dev/api-backend/getAnalysisDailySummary.html
        DailySummary dailySummary;
        {
            Map<String, String> map = new HashMap<>();
            map.put("begin_date", "20190423");
            map.put("end_date", "20190423");
            String data = gson.toJson(map);
            dailySummary = WebUtils.postJson("https://api.weixin.qq.com/datacube/getweanalysisappiddailysummarytrend?access_token=" + accessToken.value, data, DailySummary.class);
        }

        ServiceUtils.createSuccess(this);
    }


    public static class AccessCode {
        @SerializedName("access_token")
        public String value;
        @SerializedName("expires_in")
        public String expiresIn;
        public String errcode;
        public String errmsg;
    }

    public static class DailySummary {

        @SerializedName("list")
        public List<Item> list;

        public static class Item {
            /**
             * ref_date : 20170313
             * visit_total : 391
             * share_pv : 572
             * share_uv : 383
             */

            @SerializedName("ref_date")
            public String refDate;
            @SerializedName("visit_total")
            public String visitTotal;
            @SerializedName("share_pv")
            public String sharePv;
            @SerializedName("share_uv")
            public String shareUv;
        }
    }

    public static class DailyVisit {

        @SerializedName("list")
        public List<Item> list;

        public static class Item {
            /**
             * ref_date : 20170313
             * session_cnt : 142549
             * visit_pv : 472351
             * visit_uv : 55500
             * visit_uv_new : 5464
             * stay_time_session : 0
             * visit_depth : 1.9838
             */

            @SerializedName("ref_date")
            public String refDate;
            @SerializedName("session_cnt")
            public String sessionCnt;
            @SerializedName("visit_pv")
            public String visitPv;
            @SerializedName("visit_uv")
            public String visitUv;
            @SerializedName("visit_uv_new")
            public String visitUvNew;
            @SerializedName("stay_time_session")
            public String stayTimeSession;
            @SerializedName("visit_depth")
            public String visitDepth;
        }
    }
}
