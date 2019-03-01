package top.knxy.fruits.Utils;

import top.knxy.fruits.Config.C;
import top.knxy.fruits.Service.BaseService;

import java.util.UUID;

public class ServiceUtils {

	public static String getUUid() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}

	/**
	 * state = {"待付款" = 1,"准备中" = 2,"已完成" = 3,"退款中" = 4,"已取消" = 5}
	 * @param state
	 * @return
	 */
	public static String getStateStr(int state){
		switch (state){
			case 1:
				return "待付款";
			case 2:
				return "准备中";
			case 3:
				return "已完成";
			case 4:
				return "退款中";
			case 5:
				return "已取消";
			case 6:
				return "已退款";
		}
		return null;
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
