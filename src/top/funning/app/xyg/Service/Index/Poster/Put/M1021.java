package top.funning.app.xyg.Service.Index.Poster.Put;

import top.funning.app.xyg.Config.C;
import top.funning.app.xyg.DataBase.Redis;
import top.knxy.library.Config.V;
import top.knxy.library.BaseService;
import top.knxy.library.ServiceException;
import top.knxy.library.Utils.ServiceUtils;
import top.knxy.library.Utils.TextUtils;

public class M1021 extends BaseService {

    public String fileName;

    @Override
    public void run() throws Exception {
        if (TextUtils.isEmpty(fileName)) {
            throw new ServiceException();
        }

        String value = C.getImageHost() + fileName;
        Redis.set(V.postImageUrl, value);
        ServiceUtils.createSuccess(this);
    }
}
