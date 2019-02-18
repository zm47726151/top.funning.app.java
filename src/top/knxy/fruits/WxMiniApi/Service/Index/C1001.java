package top.knxy.fruits.WxMiniApi.Service.Index;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.WxMiniApi.DataBase.Bean.Good;
import top.knxy.fruits.WxMiniApi.DataBase.Bean.GoodType;
import top.knxy.fruits.WxMiniApi.DataBase.MyBatisUtils;
import top.knxy.fruits.WxMiniApi.Service.BaseService;
import top.knxy.fruits.WxMiniApi.Service.ServicelUtils;

import java.util.*;

public class C1001 extends BaseService {


    @Override
    public void run() throws Exception {
        SqlSession session = MyBatisUtils.getSession();
        DataSource mapper = session.getMapper(DataSource.class);
        List<GoodType> goodTypeList = mapper.getGoodTypeList();
        List<Good> goodList = mapper.getGoodList();
        session.close();


        Data data = new Data();
        data.typeList = new ArrayList<>();
        Map<String, Data.Type> typeMap = new HashMap<>();

        for (GoodType goodType : goodTypeList) {
            Data.Type type = new Data.Type(goodType);
            typeMap.put(goodType.getId(), type);
            data.typeList.add(type);
        }

        for (Good good : goodList) {
            Data.Type.Good dtg = new Data.Type.Good(good);
            Data.Type type = typeMap.get(good.getType());
            type.goodList.add(dtg);
        }

        this.data = data;

        ServicelUtils.createSuccess(this);
    }

    public static class Data {

        public List<Type> typeList;

        public static class Type {
            public String id;
            public String name;
            public List<Good> goodList;

            public Type(GoodType goodType) {
                this.id = goodType.getId();
                this.name = goodType.getName();
                this.goodList = new ArrayList<>();
            }

            public static class Good {
                public String id;
                public String name;
                public String description;
                public String imageUrl;
                public String price;

                public Good(top.knxy.fruits.WxMiniApi.DataBase.Bean.Good good) {
                    this.id = good.getId();
                    this.name = good.getName();
                    this.description = good.getDescription();
                    this.imageUrl = good.getImageUrl();
                    this.price = good.getPrice();
                }
            }
        }
    }
}
