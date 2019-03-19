package top.knxy.fruits.DataBase.Cache;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.DAL.KvDAL;
import top.knxy.fruits.DataBase.DBException;
import top.knxy.fruits.DataBase.MyBatisUtils;
import top.knxy.fruits.DataBase.Table.Kv;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AppMap {

    private static Map<String, String> map;

    static {
        init();
    }

    private static void init() {
        try {
            SqlSession session = MyBatisUtils.getSession();
            KvDAL dal = session.getMapper(KvDAL.class);
            map = dal.pull();
            session.close();
        } catch (Exception e) {
            new DBException(e).printStackTrace();
        } finally {
            if (map == null) map = new HashMap<>();
        }
    }

    public static String get(String key) throws IOException {
        return map.get(key);
    }

    public static void put(String key, String value) throws IOException {
        SqlSession session = MyBatisUtils.getSession();
        KvDAL dal = session.getMapper(KvDAL.class);
        Kv kv = dal.get(key);
        if (kv == null) {
            kv = new Kv();
            kv.setKey(key);
            kv.setValue(value);
            dal.put(kv);
            session.commit();
        } else if (!value.equals(kv.getValue())) {
            kv.setValue(value);
            dal.update(kv);
            session.commit();
        }
        session.close();
        map.put(key, value);
    }

    public static void remove(String key) throws IOException {
        String value = map.remove(key);
        if (value == null) return;

        SqlSession session = MyBatisUtils.getSession();
        KvDAL dal = session.getMapper(KvDAL.class);
        dal.delete(key);
        session.commit();
        session.close();
    }
}
