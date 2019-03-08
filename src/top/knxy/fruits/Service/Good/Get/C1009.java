package top.knxy.fruits.Service.Good.Get;

import com.google.gson.Gson;
import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.DAL.GoodDAL;
import top.knxy.fruits.DataBase.MyBatisUtils;
import top.knxy.fruits.DataBase.Table.BaseBean;
import top.knxy.fruits.Service.BaseService;
import top.knxy.fruits.Utils.ServiceUtils;
import top.knxy.fruits.Utils.TextUtils;

import java.util.List;

public class C1009 extends BaseService {

    public String id;

    @Override
    protected void run() throws Exception {
        if (TextUtils.isEmpty(id)) {
            ServiceUtils.createError(this);
            return;
        }

        SqlSession session = MyBatisUtils.getSession();
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

    public class Good extends BaseBean {
        private String id;
        private String name;
        private String description;
        private String imageUrl;
        private String price;
        private String content;

        public String getId() {
            return id;
        }

        public void setId(String id) {
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
