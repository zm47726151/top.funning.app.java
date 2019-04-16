package top.knxy.fruits.Service.Index.Poster.Put;

import top.knxy.fruits.Config.C;
import top.knxy.library.Config.Code;
import top.knxy.library.Config.V;
import top.knxy.fruits.DataBase.Cache.AppMap;
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
        AppMap.put(V.postImageUrl, value);
        ServiceUtils.createSuccess(this);
    }
}
