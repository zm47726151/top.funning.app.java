package top.funning.app.xyg.Service.Index.Poster.Remove;

import top.funning.app.xyg.DataBase.Redis;
import top.knxy.library.Config.V;
import top.knxy.library.BaseService;
import top.knxy.library.Utils.ServiceUtils;

public class M1022 extends BaseService {

    @Override
    public void run() throws Exception {

        Redis.del(V.postImageUrl);
        ServiceUtils.createSuccess(this);
    }

}
