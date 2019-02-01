package top.knxy.fruits.WxMiniApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import top.knxy.fruits.WxMiniApi.Service.BaseService;

public class RpUtils {

	public static String getString(HttpServletRequest request) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			br = request.getReader();
			String str;
			while ((str = br.readLine()) != null) {
				sb.append(str);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

	public static Response createSuccess() {
		Response response = new Response();
		response.code = "1";
		return response;
	}

	public static Response createError(String msg) {
		Response response = new Response();
		response.code = "1000";
		response.msg = msg;
		return response;
	}

	public static void print(PrintWriter pw, BaseService model) {
		if (model.status) {
			printSuccess(pw, model.data);
		} else {
			printError(pw, model.msg);
		}
	}

	public static void printError(PrintWriter pw, String msg) {
		Response response = createError(msg);

		Gson gson = new Gson();
		String jsonStr = gson.toJson(response);

		pw.print(jsonStr);
		pw.close();
	}

	public static void printSuccess(PrintWriter pw, Object data) {
		Response response = createSuccess();
		response.data = data;

		Gson gson = new Gson();
		String jsonStr = gson.toJson(response);

		pw.print(jsonStr);
		pw.close();
	}

	public static void printSuccess(PrintWriter pw, Object data, String dateStringType) {
		Response response = createSuccess();
		response.data = data;

		Gson gson = new GsonBuilder().setDateFormat(dateStringType).create();
		String jsonStr = gson.toJson(response);

		pw.print(jsonStr);
		pw.close();
	}

	public static void printSuccess(PrintWriter pw) {
		printSuccess(pw, null);
	}
}