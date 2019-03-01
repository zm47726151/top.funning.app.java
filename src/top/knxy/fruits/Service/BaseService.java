package top.knxy.fruits.Service;

import top.knxy.fruits.Utils.ServiceUtils;

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
            ServiceUtils.createError(this, "处理异常");
        }
    }


    protected abstract void run() throws Exception;
}
