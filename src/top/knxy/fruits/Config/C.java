package top.knxy.fruits.Config;


import top.knxy.fruits.Utils.DateUtils;

import java.util.Date;

/**
 * code > 1000 为自定义范围代码
 */
public class C {
    public final static boolean isDebug = true;

    public final static String getImageHost() {
        return "http://image.fruits.knxy.top/";
    }

    public final static String getVersion() {
        if (isDebug) {
            return DateUtils.dateToString(new Date(), DateUtils.dateStringType1);
        } else {
            return "8";
        }
    }

    public static class Client {
        public static String success = "1";
        public static String error = "-1";
        public static String needLogin = "-2";
    }

    public static class Service {
        public static int success = 1;
        public static int error = -1;
    }
}
