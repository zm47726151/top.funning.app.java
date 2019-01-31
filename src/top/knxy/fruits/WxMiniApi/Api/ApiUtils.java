package top.knxy.fruits.WxMiniApi.Api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.http.HttpRequest;
import top.knxy.fruits.WxMiniApi.Service.BaseService;
import top.knxy.fruits.WxMiniApi.Config.C;
import top.knxy.fruits.WxMiniApi.Service.SessionInfo;

public class ApiUtils {

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
		response.code = C.Client.success;
		return response;
	}

	public static Response createError(String msg) {
		Response response = new Response();
		response.code = C.Client.error;
		response.msg = msg;
		return response;
	}

	public static void response(PrintWriter pw, BaseService model) {
		if (model.code == C.Service.success) {
			responseSuccess(pw, model.data);
		} else {
			responseError(pw, model.msg);
		}
	}

	public static void responseError(PrintWriter pw, String msg) {
		Response response = createError(msg);

		Gson gson = new Gson();
		String jsonStr = gson.toJson(response);

		printString(pw,jsonStr);
	}

	public static void responseSuccess(PrintWriter pw, Object data) {
		Response response = createSuccess();
		response.data = data;

		Gson gson = new Gson();
		String jsonStr = gson.toJson(response);

		printString(pw,jsonStr);
	}

	public static void responseSuccess(PrintWriter pw, Object data, String dateStringType) {
		Response response = createSuccess();
		response.data = data;

		Gson gson = new GsonBuilder().setDateFormat(dateStringType).create();
		String jsonStr = gson.toJson(response);

		printString(pw,jsonStr);
	}

	public static void responseSuccess(PrintWriter pw) {
		responseSuccess(pw, null);
	}

	public static void printString(PrintWriter pw,String str){
		System.out.println(str);
		pw.print(str);
		pw.close();
	}

	public static SessionInfo getSession(HttpServletRequest request){
		return (SessionInfo) request.getSession().getAttribute("SessionInfo");
	}
}