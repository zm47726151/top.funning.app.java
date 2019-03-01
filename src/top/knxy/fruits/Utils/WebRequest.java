package top.knxy.fruits.Utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

public class WebRequest {


    public static String requestGet(String urlString, Map<String, String> data) throws Exception {// 创建URL对象，xxx是服务器API
        urlString += "?";
        Set<String> keySet = data.keySet();
        for (String key : keySet) {
            String value = data.get(key);
            if (value == null) value = "";
            value = URLEncoder.encode(value, "UTF-8");
            urlString += key;
            urlString += "=";
            urlString += value;
            urlString += "&";
        }
        return requestGet(urlString);
    }

    public static String requestGet(String urlString) throws Exception {// 创建URL对象，xxx是服务器API
        System.out.println(urlString);
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        return getString(conn);
    }


    public static String requestPost(String urlString, String data) throws Exception {
        URL url;
        url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setConnectTimeout(5000);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        OutputStream op = conn.getOutputStream();
        op.write(data.getBytes());
        return getString(conn);
    }

    private static String getString(HttpURLConnection conn) throws Exception {
        int code = conn.getResponseCode();
        if (code == 200) {
            InputStream in = conn.getInputStream();
            StringBuffer sb = new StringBuffer();
            byte[] bytes = new byte[1024];
            int len;
            while ((len = in.read(bytes)) != -1) {
                sb.append(new String(bytes, 0, len, "utf-8"));
            }
            in.close();
            return sb.toString();
        } else {
            throw new Exception("statu = " + code);
        }
    }

}
