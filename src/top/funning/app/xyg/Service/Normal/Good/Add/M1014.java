package top.funning.app.xyg.Service.Normal.Good.Add;

import com.google.gson.Gson;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.GoodDAL;
import top.funning.app.xyg.DataBase.DAL.GoodDetailDAL;
import top.funning.app.xyg.DataBase.Table.Good;
import top.funning.app.xyg.DataBase.Table.GoodDetail;
import top.funning.library.BaseService;
import top.funning.app.xyg.Service.Normal.Good.Modify.M1011;
import top.funning.library.ServiceException;
import top.funning.library.Utils.ServiceUtils;
import top.funning.library.Utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class M1014 extends BaseService {


    public String name;
    public String description;
    public String price;
    public String state;
    public String imageUrl;
    public String type;
    public M1011.Detail detail;


    public static class Detail {

        public M1011.Detail.Content content;
        public M1011.Detail.Header header;

        public static class Content {
            public List<String> imageList;
        }

        public static class Header {
            public List<String> imageList;
        }
    }

    @Override
    protected void run() throws Exception {
        if (TextUtils.isEmpty(name) ||
                TextUtils.isEmpty(price) ||
                TextUtils.isEmpty(imageUrl) ||
                !TextUtils.isNumeric(state) ||
                detail == null ||
                detail.header == null ||
                detail.header.imageList.isEmpty()
        ) {
            throw new ServiceException();
        }

        if (detail.content.imageList == null) {
            detail.content.imageList = new ArrayList<>();
        }

        Good good = new Good();
        good.setName(name);
        good.setDescription(description);
        good.setPrice(price);
        good.setState(Integer.valueOf(state));
        good.setType(Integer.valueOf(type));
        good.setImageUrl(imageUrl);

        SqlSession session = getSqlSession();
        GoodDAL gDal = session.getMapper(GoodDAL.class);
        if (gDal.insert(good) < 1) {
            ServiceUtils.createError(this);

            return;
        }

        //删除缓存
        top.funning.app.xyg.DataBase.Cache.Good.clear();

        M1011.Content content = new M1011.Content();

        content.header = new M1011.Content.Header();
        content.header.imageList = new ArrayList<>();
        for (String s : detail.header.imageList) {
            content.header.imageList.add(s);
        }

        content.detail = new M1011.Content.Detail();
        content.detail.imageList = new ArrayList<>();
        for (String s : detail.content.imageList) {
            content.detail.imageList.add(s);
        }

        GoodDetail goodDetail = new GoodDetail();
        goodDetail.setGoodId(good.getId());
        goodDetail.setContent(new Gson().toJson(content));
        GoodDetailDAL gdDal = session.getMapper(GoodDetailDAL.class);
        int result = gdDal.insert(goodDetail);
        session.commit();
        if (result < 1) {
            ServiceUtils.createError(this);

            return;
        }

        ServiceUtils.createSuccess(this);

    }
}
