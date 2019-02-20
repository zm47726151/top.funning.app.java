package top.knxy.fruits.WxMiniApi.Service;

public abstract class BaseService {
    public int code;
    public String msg;
    public Object data;

    public BaseService() {

    }

    public void start() {
        try {
            run();
        } catch (Exception e) {
            System.out.println("service exception");
            e.printStackTrace();
            ServicelUtils.createError(this, "处理异常");
        }
    }


    protected abstract void run() throws Exception;
}
