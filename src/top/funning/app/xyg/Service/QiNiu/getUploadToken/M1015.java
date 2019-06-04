package top.funning.app.xyg.Service.QiNiu.getUploadToken;

import com.qiniu.util.Auth;
import top.funning.app.xyg.Config.C;
import top.knxy.library.BaseService;
import top.knxy.library.Utils.ServiceUtils;

public class M1015 extends BaseService {

    public String suffix;

    @Override
    protected void run() throws Exception {
        if (!"jpg".equals(suffix) && !"png".equals(suffix)) {
            ServiceUtils.createError(this);
            return;
        }

        String accessKey = C.QiNiu.AccessKey;
        String secretKey = C.QiNiu.SecretKey;
        String bucket = "shop_fruits";
        String key = ServiceUtils.getUUid() + "." + suffix;
        Auth auth = Auth.create(accessKey, secretKey);
        String token = auth.uploadToken(bucket, key);

        Data data = new Data();
        data.token = token;
        data.name = key;
        this.data = data;

        ServiceUtils.createSuccess(this);
    }

    public static class Data {
        public String token;
        public String name;
    }
}
