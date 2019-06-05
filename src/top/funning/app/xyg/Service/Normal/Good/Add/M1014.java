package top.funning.app.xyg.Service.Normal.Good.Add;

import com.google.gson.Gson;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotNull;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.GoodDAL;
import top.funning.app.xyg.DataBase.DAL.GoodDetailDAL;
import top.funning.app.xyg.DataBase.Table.Good;
import top.funning.app.xyg.DataBase.Table.GoodDetail;
import top.knxy.library.BaseService;
import top.funning.app.xyg.Service.Normal.Good.Modify.M1011;
import top.knxy.library.ServiceException;
import top.knxy.library.Utils.ServiceUtils;

import java.util.ArrayList;
import java.util.List;

public class M1014 extends BaseService {
    @NotNull
    @Length(min = 1, max = 32)
    public String name;

    @NotNull
    @Length(min = 1, max = 128)
    public String description;

    @NotNull
    public String price;

    @NotNull
    public String state;

    @NotNull
    @Length(min = 1, max = 128)
    public String imageUrl;

    @NotNull
    public String type;

    @NotNull
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

        if (detail == null || detail.header == null || detail.header.imageList.isEmpty()) {
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
