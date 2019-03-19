package top.knxy.fruits.Service.Index.Poster.Put;

import top.knxy.fruits.Config.C;
import top.knxy.fruits.Config.V;
import top.knxy.fruits.DataBase.Cache.AppMap;
import top.knxy.fruits.Service.BaseService;
import top.knxy.fruits.Service.ServiceException;
import top.knxy.fruits.Utils.ServiceUtils;
import top.knxy.fruits.Utils.TextUtils;

public class M1021 extends BaseService {

    public String fileName;

    @Override
    public void run() throws Exception {
        if (TextUtils.isEmpty(fileName)) {
            throw new ServiceException();
        }

        String value = C.getImageHost() + fileName;
        AppMap.put(V.postImageUrl, value);
        ServiceUtils.createSuccess(this);
    }
}
