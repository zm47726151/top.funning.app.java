package top.funning.app.xyg.Service.Normal.Good.Get;

import com.google.gson.Gson;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.GoodDAL;
import top.funning.library.Bean.BaseTable;
import top.funning.library.BaseService;
import top.funning.library.Utils.ServiceUtils;
import top.funning.library.Utils.TextUtils;

import java.util.List;

public class C1009 extends BaseService {

    public String id;

    @Override
    protected void run() throws Exception {
        if (TextUtils.isEmpty(id)) {
            ServiceUtils.createError(this);
            return;
        }

        SqlSession session = getSqlSession();
        Good good = session.getMapper(GoodDAL.class).getDetailForUser(id);

        if (good == null) {
            ServiceUtils.createError(this);
            return;
        }

        String content = good.getContent();
        if (TextUtils.isEmpty(content)) {
            content = "{}";
        }

        Gson gson = new Gson();
        Data data = gson.fromJson(content, Data.class);
        data.id = String.valueOf(good.getId());
        data.name = good.getName();
        data.description = good.getDescription();
        data.imageUrl = good.getImageUrl();
        data.price = good.getPrice();

        this.data = data;
        ServiceUtils.createSuccess(this);

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

    public static class Good extends BaseTable {
        public int id;
        public String name;
        public String description;
        public String imageUrl;
        public String price;
        public String content;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

}
