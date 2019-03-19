package top.knxy.fruits.Service.Index.Poster.Remove;

import top.knxy.fruits.Config.V;
import top.knxy.fruits.DataBase.Cache.AppMap;
import top.knxy.fruits.Service.BaseService;
import top.knxy.fruits.Utils.ServiceUtils;

public class M1022 extends BaseService {

    @Override
    public void run() throws Exception {

        AppMap.remove(V.postImageUrl);
        ServiceUtils.createSuccess(this);
    }

}
