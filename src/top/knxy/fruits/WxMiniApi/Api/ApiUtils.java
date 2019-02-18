package top.knxy.fruits.WxMiniApi.Api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import top.knxy.fruits.WxMiniApi.Service.BaseService;

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
		response.code = Code.success;
		return response;
	}

	public static Response createError(String msg) {
		Response response = new Response();
		response.code = Code.error;
		response.msg = msg;
		return response;
	}

	public static void Response(PrintWriter pw, BaseService model) {
		if (model.code == 1) {
			ResponseSuccess(pw, model.data);
		} else {
			ResponseError(pw, model.msg);
		}
	}

	public static void ResponseError(PrintWriter pw, String msg) {
		Response response = createError(msg);

		Gson gson = new Gson();
		String jsonStr = gson.toJson(response);

		printString(pw,jsonStr);
	}

	public static void ResponseSuccess(PrintWriter pw, Object data) {
		Response response = createSuccess();
		response.data = data;

		Gson gson = new Gson();
		String jsonStr = gson.toJson(response);

		printString(pw,jsonStr);
	}

	public static void ResponseSuccess(PrintWriter pw, Object data, String dateStringType) {
		Response response = createSuccess();
		response.data = data;

		Gson gson = new GsonBuilder().setDateFormat(dateStringType).create();
		String jsonStr = gson.toJson(response);

		printString(pw,jsonStr);
	}

	public static void ResponseSuccess(PrintWriter pw) {
		ResponseSuccess(pw, null);
	}

	public static void printString(PrintWriter pw,String str){
		System.out.println(str);
		pw.print(str);
		pw.close();
	}
}