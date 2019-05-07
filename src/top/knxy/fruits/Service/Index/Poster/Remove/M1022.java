package top.knxy.fruits.Service.Index.Poster.Remove;

import top.knxy.fruits.DataBase.Redis;
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
