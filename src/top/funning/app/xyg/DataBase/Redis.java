package top.funning.app.xyg.DataBase;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;
import top.funning.app.xyg.Config.C;
import top.knxy.library.Utils.LogUtils;

public class Redis {
    public static final String TAG = "Redis";

    private static Jedis jedis;

    private static Jedis getJedis() {
        if (jedis == null) {
            try {
                jedis = new Jedis(C.Redis.host);
                jedis.auth(C.Redis.password);
                jedis.select(C.Redis.db);
            } catch (Exception e) {
                LogUtils.i(TAG, "Redis Open Failure", e);
                jedis = null;
            }
        }
        return jedis;
    }

    public static void set(String key, String value, int secondsToExpire) {
        SetParams params = new SetParams();
        params.ex(secondsToExpire);
        getJedis().set(key, value, params);
    }

    public static void set(String key, String value) {
        getJedis().set(key, value);
    }

    public static void del(String key) {
        getJedis().del(key);
    }

    public static String get(String key) {
        return getJedis().get(key);
    }

}
