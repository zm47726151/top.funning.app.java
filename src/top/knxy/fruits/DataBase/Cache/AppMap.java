package top.knxy.fruits.DataBase.Cache;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.DAL.KvDAL;
import top.knxy.library.DBException;
import top.knxy.library.Utils.MyBatisUtils;
import top.knxy.fruits.DataBase.Table.Kv;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppMap {

    private final static Map<String, String> map = new HashMap<>();

    static {
        init();
    }

    private static void init() {
        try {
            SqlSession session = MyBatisUtils.getSession();
            KvDAL dal = session.getMapper(KvDAL.class);
            List<Kv> list = dal.pull();
            session.close();
            for (Kv kv : list) {
                map.put(kv.getKey(), kv.getValue());
            }
        } catch (Exception e) {
            new DBException(e).printStackTrace();
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
