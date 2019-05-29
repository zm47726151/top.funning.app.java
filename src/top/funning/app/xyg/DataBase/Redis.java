package top.funning.app.xyg.DataBase;

import redis.clients.jedis.Jedis;
import top.funning.app.xyg.Config.S;
import top.knxy.library.Utils.LogUtils;

public class Redis {
    public static final String TAG = "Redis";

    private static Jedis jedis;

    static {
        try {
            jedis = new Jedis(S.Redis.host);
            jedis.auth(S.Redis.password);
            jedis.select(S.Redis.db);
        } catch (Exception e) {
            LogUtils.i(TAG, "Redis Open Failure", e);
            jedis = null;
        }
    }


    public static void set(String key, String value) {
        jedis.set(key, value);
    }

    public static void del(String key) {
        jedis.del(key);
    }

    public static String get(String key) {
        return jedis.get(key);
    }

}
