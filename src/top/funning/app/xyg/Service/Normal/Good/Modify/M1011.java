package top.funning.app.xyg.Service.Normal.Good.Modify;

import com.google.gson.Gson;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.GoodDAL;
import top.funning.app.xyg.DataBase.DAL.GoodDetailDAL;
import top.funning.app.xyg.DataBase.Table.Good;
import top.funning.app.xyg.DataBase.Table.GoodDetail;
import top.knxy.library.BaseService;
import top.knxy.library.Utils.ServiceUtils;
import top.knxy.library.Utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class M1011 extends BaseService {


    public String id;
    public String name;
    public String description;
    public String price;
    public String state;
    public String imageUrl;
    public String type;
    public Detail detail;


    public static class Detail {

        public Content content;
        public Header header;

        public static class Content {
            public List<String> imageList;
        }

        public static class Header {
            public List<String> imageList;
        }
    }

    @Override
    protected void run() throws Exception {
        if (!TextUtils.isNumeric(id) ||
                TextUtils.isEmpty(name) ||
                TextUtils.isEmpty(price) ||
                TextUtils.isEmpty(imageUrl) ||
                !TextUtils.isNumeric(state) ||
                detail == null ||
                detail.header == null ||
                detail.header.imageList.isEmpty()
        ) {
            ServiceUtils.createError(this);
            return;
        }

        if (detail.content.imageList == null) {
            detail.content.imageList = new ArrayList<>();
        }

        Good good = new Good();
        good.setId(Integer.valueOf(id));
        good.setName(name);
        good.setDescription(description);
        good.setPrice(price);
        good.setState(Integer.valueOf(state));
        if (TextUtils.isNumeric(type)) good.setType(Integer.valueOf(type));
        good.setImageUrl(imageUrl);

        SqlSession session = getSqlSession();
        GoodDAL gDal = session.getMapper(GoodDAL.class);
        if (gDal.update(good) < 1) {
            ServiceUtils.createError(this);

            return;
        }

        //删除缓存
        top.funning.app.xyg.DataBase.Cache.Good.clear();

        Content content = new Content();

        content.header = new Content.Header();
        content.header.imageList = new ArrayList<>();
        for (String s : detail.header.imageList) {
            content.header.imageList.add(s);
        }

        content.detail = new Content.Detail();
        content.detail.imageList = new ArrayList<>();
        for (String s : detail.content.imageList) {
            content.detail.imageList.add(s);
        }


        GoodDetail goodDetail = new GoodDetail();
        goodDetail.setGoodId(Integer.valueOf(id));
        goodDetail.setContent(new Gson().toJson(content));
        GoodDetailDAL gdDal = session.getMapper(GoodDetailDAL.class);
        int result = gdDal.update(goodDetail);
        session.commit();
        if (result < 1) {
            ServiceUtils.createError(this);

            return;
        }

        ServiceUtils.createSuccess(this);

    }

    public static class Content {
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
