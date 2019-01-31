package top.knxy.fruits.WxMiniApi.Service;

import top.knxy.fruits.WxMiniApi.Config.C;

import java.util.UUID;

public class ServicelUtils {

	public static String getUUid() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
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



}
