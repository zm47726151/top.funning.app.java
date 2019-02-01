package top.knxy.fruits.WxMiniApi.Service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

public class ServicelUtils {

	public static String getUUid() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}

	public static void createError(BaseService model, String msg) {
		model.status = false;
		model.code = "-1";
		model.msg = msg;
	}

	public static void createError(BaseService model, String code, String msg) {
		model.status = false;
		model.code = code;
		model.msg = msg;
	}

	public static void createSuccess(BaseService model) {
		model.status = true;
		model.code = "1";
	}

	public static String requestGet(String urlString, Map<String, String> map) throws Exception {// 创建URL对象，xxx是服务器API
		Set<Entry<String, String>> entrySet = map.entrySet();
		StringBuffer sb = new StringBuffer();
		sb.append(urlString);
		sb.append("?");
		for (Entry<String, String> entry : entrySet) {
			String key = URLEncoder.encode(entry.getKey(), "UTF-8");
			String value = URLEncoder.encode(entry.getValue(), "UTF-8");
			sb.append(key).append("=").append(value).append("&");
		}

		return requestGet(sb.toString());
	}

	public static String requestGet(String urlString) throws Exception {// 创建URL对象，xxx是服务器API
		URL url = new URL(urlString);
		// 调用URL对象的openConnection( )来获取HttpURLConnection对象实例
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// 请求方法为GET
		conn.setRequestMethod("GET");
		// 设置连接超时为5秒
		conn.setConnectTimeout(5000);
		// 服务器返回东西了，先对响应码判断
		int code = conn.getResponseCode();
		if (code == 200) {
			// 用getInputStream()方法获得服务器返回的输入流
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
