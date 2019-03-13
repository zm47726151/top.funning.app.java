package top.knxy.fruits.Utils;

import top.knxy.fruits.Config.C;
import top.knxy.fruits.Config.S;
import top.knxy.fruits.Service.BaseService;

import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

public class ServiceUtils {

    public static String getUUid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    /**
     * @param state
     * @return
     */
    public static String getStateStr(int state) {
        switch (state) {
            case 1:
                return "待付款";
            case 2:
                return "准备中";
            case 3:
                return "已完成";
            case 4:
                return "退款中";
            case 5:
                return "已取消";
            case 6:
                return "已退款";
            case 7:
                return "已付款";
        }
        return null;
    }

    public static void createError(BaseService model) {
        model.code = C.Service.error;
        model.msg = "处理异常";
    }

    public static void createError(BaseService model, String msg) {
        model.code = C.Service.error;
        model.msg = msg;
    }

    public static void createError(BaseService model, int code, String msg) {
        model.code = code;
        model.msg = msg;
    }

    public static void createSuccess(BaseService model) {
        model.code = C.Service.success;
    }


    public static String getWXPaySignValue(TreeMap<Object, Object> map) {
        Set<Object> keySet = map.keySet();
        StringBuilder sb = new StringBuilder();
        for (Object key : keySet) {
            if ("sign".equals(key)) continue;
            sb.append(key).append("=").append(map.get(key)).append("&");
        }
        sb.append("key").append("=").append(S.WCPay.apiKey);
        return PwdUtils.md5(sb.toString()).toUpperCase();
    }

}
