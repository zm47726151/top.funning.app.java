package top.funning.app.xyg.Config;

import top.knxy.library.Config.D;
import top.knxy.library.Utils.DateUtils;
import top.knxy.library.Utils.FileUtils;
import java.util.Date;

public class C {

    public final static String domain = getValue("domain");
    public final static String ip = getValue("ip");
    public final static String imageHost = getValue("qiniu_host");

    public final static String getVersion() {
        if (D.isDebug()) {
            return DateUtils.dateToString(new Date(), DateUtils.dateStringType1);
        } else {
            return "2";
        }
    }

    public static class Baidu {
        public static final String mapApiUrl = getValue("baidu_mapApiUrl");
        public static final String key = getValue("baidu_key");
    }

    public static class QiNiu {
        public static final String AccessKey = getValue("qiniu_AccessKey");
        public static final String SecretKey = getValue("qiniu_SecretKey");
        public static final String bucket = getValue("qiniu_bucket");
    }

    public static class Redis {
        public static final String host = getValue("redis_host");
        public static final int port = Integer.valueOf(getValue("redis_port"));
        public static final String password = getValue("redis_password");
        public static final int db = Integer.valueOf(getValue("redis_db"));
    }

    public static class WCPay {
        public static final String mchId = getValue("wcpay_mchId");
        public static final String apiKey = getValue("wcpay_apiKey");
    }

    public static class WeChat {
        public static final String appid = getValue("wechat_appid");
        public static final String secret = getValue("wechat_secret");
    }

    private static String getValue(String key) {
        try {
            return String.valueOf(FileUtils.getPropertyMap("app.properties").get(key));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
