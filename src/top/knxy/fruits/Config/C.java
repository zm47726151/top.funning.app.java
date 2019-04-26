package top.knxy.fruits.Config;

import top.knxy.library.Config.D;
import top.knxy.library.Utils.DateUtils;

import java.util.Date;

public class C {
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
