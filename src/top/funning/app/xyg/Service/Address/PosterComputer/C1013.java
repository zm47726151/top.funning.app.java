package top.funning.app.xyg.Service.Address.PosterComputer;

import com.google.gson.Gson;
import top.funning.app.xyg.Config.S;
import top.funning.app.xyg.Service.Normal.Order.Pay.C1010;
import top.knxy.library.BaseService;
import top.knxy.library.ServiceException;
import top.knxy.library.Utils.ServiceUtils;
import top.knxy.library.Utils.WebUtils;

import java.util.HashMap;
import java.util.Map;

public class C1013 extends BaseService {
    public String address;

    @Override
    protected void run() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("address", address);
        map.put("output", "json");
        map.put("ak", S.Baidu.key);
        C1010.LocationInfo locationInfo = new Gson().fromJson(WebUtils.get(S.Baidu.mapApiUrl, map), C1010.LocationInfo.class);

        if (locationInfo.status != 0) {
            throw new ServiceException("get location fail. address : " + address + " . Response" + new Gson().toJson(locationInfo));
        }

        Data data = new Data();
        this.data = data;

        data.lat = String.valueOf(locationInfo.result.location.lat);
        data.lng = String.valueOf(locationInfo.result.location.lng);
        ServiceUtils.createSuccess(this);
    }

    public static class Data {
        public String lat;
        public String lng;
    }

}

