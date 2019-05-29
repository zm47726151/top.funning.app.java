package top.funning.app.xyg.Config;

import top.funning.library.Config.D;
import top.funning.library.Utils.DateUtils;
import top.funning.library.Utils.FileUtils;

import java.io.IOException;
import java.util.Date;

public class C {

    private static String domain;
    private static String ip;

    static {
        try {
            domain = FileUtils.getPropertyMap("app.properties").get("domain").toString();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            ip = FileUtils.getPropertyMap("app.properties").get("ip").toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final static String getDomain() {
        return domain;
    }

    public final static String getIp() {
        return ip;
    }

    public final static String getImageHost() {
        return "http://image.fruits.knxy.top/";
    }

    public final static String getVersion() {
        if (D.isDebug()) {
            return DateUtils.dateToString(new Date(), DateUtils.dateStringType1);
        } else {
            return "2";
        }
    }
}
