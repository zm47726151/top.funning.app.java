package top.knxy.fruits.WxMiniApi.Service;

public abstract class BaseService {
	public boolean status;
	public String code;
	public String msg;
	public Object data;

	public BaseService() {

	}

	public abstract void action();

}
