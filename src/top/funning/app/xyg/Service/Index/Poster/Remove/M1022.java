package top.funning.app.xyg.Service.Index.Poster.Remove;

import top.funning.app.xyg.DataBase.Redis;
import top.funning.library.Config.V;
import top.funning.library.BaseService;
import top.funning.library.Utils.ServiceUtils;

public class M1022 extends BaseService {

    @Override
    public void run() throws Exception {

        Redis.del(V.postImageUrl);
        ServiceUtils.createSuccess(this);
    }

}
