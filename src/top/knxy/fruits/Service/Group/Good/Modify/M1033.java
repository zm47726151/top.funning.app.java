package top.knxy.fruits.Service.Group.Good.Modify;

import com.google.gson.Gson;
import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.DAL.GroupGoodDAL;
import top.knxy.fruits.DataBase.Table.GroupGood;
import top.knxy.library.BaseService;
import top.knxy.library.ServiceException;
import top.knxy.library.Utils.ServiceUtils;
import top.knxy.library.Utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class M1033 extends BaseService {


    public String state;
    public String id;

    public String shareImageUrl;
    public String coverImageUrl;
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
        if (TextUtils.isEmpty(id)) {
            throw new ServiceException("no shareImageUrl");
        }

        if (!"1".equals(state) && !"2".equals(state)) {
            throw new ServiceException("state illegal");
        }

        if (TextUtils.isEmpty(shareImageUrl)) {
            throw new ServiceException("no shareImageUrl");
        }

        if (TextUtils.isEmpty(coverImageUrl)) {
            throw new ServiceException("no coverImageUrl");
        }

        if (detail == null) {
            throw new ServiceException("detail is null");
        }

        if (detail.header == null && detail.header.imageList.isEmpty()) {
            throw new ServiceException("header imageList is empty");
        }


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

        GroupGood gg = new GroupGood();
        gg.setId(id);
        gg.setState(state);
        gg.setShareImageUrl(shareImageUrl);
        gg.setImageUrl(coverImageUrl);
        gg.setDetail(new Gson().toJson(content));

        SqlSession session = getSqlSession();
        GroupGoodDAL ggDal = session.getMapper(GroupGoodDAL.class);
        int row = ggDal.update(gg);
        session.commit();

        if (row < 1) {
            throw new ServiceException("row < 1");
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
