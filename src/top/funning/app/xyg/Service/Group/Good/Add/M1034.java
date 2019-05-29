package top.funning.app.xyg.Service.Group.Good.Add;

import com.google.gson.Gson;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.GroupGoodDAL;
import top.funning.app.xyg.DataBase.Table.GroupGood;
import top.funning.app.xyg.Service.Group.Good.Modify.M1033;
import top.knxy.library.BaseService;
import top.knxy.library.ServiceException;
import top.knxy.library.Utils.DateUtils;
import top.knxy.library.Utils.ServiceUtils;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class M1034 extends BaseService {


    /**
     * name :
     * price :
     * groupNum :
     * state :
     * stopTime :
     * getTimeStart :
     * getTimeStop :
     * description :
     * coverImageUrl :
     * goodImageUrl :
     * shareImageUrl :
     * detail : {"content":{"imageList":[]},"header":{"imageList":[]}}
     */

    @NotNull
    public String name;
    @NotNull
    public String price;
    @NotNull
    public String groupNum;
    @NotNull
    public String state;
    @NotNull
    public String stopTime;
    @NotNull
    public String getTimeStart;
    @NotNull
    public String getTimeStop;
    @NotNull
    public String description;
    @NotNull
    public String coverImageUrl;
    @NotNull
    public String goodImageUrl;
    @NotNull
    public String shareImageUrl;
    @NotNull
    public Detail detail;

    public static class Detail {

        @NotNull
        public Content content;
        @NotNull
        public Header header;

        public static class Content {
            @NotNull
            public List<String> imageList;
        }

        public static class Header {
            @NotNull
            public List<String> imageList;
        }
    }

    @Override
    protected void run() throws Exception {

        Date stopTime = DateUtils.stringToDate(this.stopTime, DateUtils.dateStringType2);
        Date getTimeStart = DateUtils.stringToDate(this.getTimeStart, DateUtils.dateStringType2);
        Date getTimeStop = DateUtils.stringToDate(this.getTimeStop, DateUtils.dateStringType2);

        if (new Date().getTime() > stopTime.getTime()) {
            throw new ServiceException("结束时间不能早与当前时间");
        }

        if (stopTime.getTime() > getTimeStart.getTime()) {
            throw new ServiceException("结束时间不能晚与取货开始时间");
        }

        if (getTimeStart.getTime() > getTimeStop.getTime()) {
            throw new ServiceException("取货开始时间晚与取货结束时间");
        }

        GroupGood order = new GroupGood();
        order.setName(name);
        order.setPrice(price);
        order.setGroupNum(groupNum);
        order.setState(state);

        order.setStopTime(stopTime);
        order.setGetTimeStart(getTimeStart);
        order.setGetTimeStop(getTimeStop);
        order.setDescription(description);
        order.setImageUrl(coverImageUrl);
        order.setGoodImageUrl(goodImageUrl);
        order.setShareImageUrl(shareImageUrl);

        M1033.Content content = new M1033.Content();

        content.header = new M1033.Content.Header();
        content.header.imageList = new ArrayList<>();
        for (String s : detail.header.imageList) {
            content.header.imageList.add(s);
        }

        content.detail = new M1033.Content.Detail();
        content.detail.imageList = new ArrayList<>();
        for (String s : detail.content.imageList) {
            content.detail.imageList.add(s);
        }

        order.setDetail(new Gson().toJson(content));

        SqlSession session = getSqlSession();
        GroupGoodDAL ggDal = session.getMapper(GroupGoodDAL.class);
        ggDal.insert(order);
        session.commit();

        ServiceUtils.createSuccess(this);
    }

}
