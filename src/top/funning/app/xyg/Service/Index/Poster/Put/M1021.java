package top.funning.app.xyg.Service.Index.Poster.Put;

import top.funning.app.xyg.Config.C;
import top.funning.app.xyg.DataBase.Redis;
import top.funning.library.Config.V;
import top.funning.library.BaseService;
import top.funning.library.ServiceException;
import top.funning.library.Utils.ServiceUtils;
import top.funning.library.Utils.TextUtils;

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
