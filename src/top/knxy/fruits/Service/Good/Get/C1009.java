package top.knxy.fruits.Service.Good.Get;

import com.google.gson.Gson;
import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.MyBatisUtils;
import top.knxy.fruits.Service.BaseService;
import top.knxy.fruits.Service.Good.Bean.Detail;
import top.knxy.fruits.Service.Good.DBOperation;
import top.knxy.fruits.Service.ServicelUtils;
import top.knxy.fruits.Utils.StrUtils;

import java.util.List;

public class C1009 extends BaseService {

    public String id;

    @Override
    protected void run() throws Exception {
        if (StrUtils.isEmpty(id)) {
            ServicelUtils.createError(this);
            return;
        }

        SqlSession session = MyBatisUtils.getSession();
        Detail detail = session.getMapper(DBOperation.class).getDetail(id);

        if (detail == null) {
            ServicelUtils.createError(this);
            return;
        }

        String content = detail.getContent();
        if (StrUtils.isEmpty(content)) {
            content = "{}";
        }

        Gson gson = new Gson();
        Data data = gson.fromJson(content, Data.class);
        data.id = detail.getId();
        data.name = detail.getName();
        data.description = detail.getDescription();
        data.imageUrl = detail.getImageUrl();
        data.price = detail.getPrice();

        this.data = data;
        ServicelUtils.createSuccess(this);
        session.close();
    }

    public static class Data {
        public String id;
        public String name;
        public String description;
        public String imageUrl;
        public String price;
        public Header header;
        public Detail detail;

        public static class Header {
            public List<String> imageList;
        }

        public static class Detail {
            public List<String> imageList;
        }
    }
}
