package top.knxy.fruits.Service.Index;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.Table.Good;
import top.knxy.fruits.DataBase.Table.GoodType;
import top.knxy.fruits.DataBase.MyBatisUtils;
import top.knxy.fruits.Service.BaseService;
import top.knxy.fruits.Utils.ServiceUtils;

import java.util.*;

public class C1001 extends BaseService {


    @Override
    public void run() throws Exception {
        SqlSession session = MyBatisUtils.getSession();
        DBOperation mapper = session.getMapper(DBOperation.class);
        List<GoodType> goodTypeList = mapper.getGoodTypeList();
        List<Good> goodList = mapper.getGoodList();
        session.close();


        Data data = new Data();
        data.typeList = new ArrayList<>();
        Map<String, Data.Type> typeMap = new HashMap<>();

        for (GoodType goodType : goodTypeList) {
            Data.Type type = new Data.Type(goodType);
            typeMap.put(String.valueOf(goodType.getId()), type);
            data.typeList.add(type);
        }

        for (Good good : goodList) {
            Data.Type.Good dtg = new Data.Type.Good(good);
            Data.Type type = typeMap.get(good.getType());
            type.goodList.add(dtg);
        }

        this.data = data;

        ServiceUtils.createSuccess(this);
        session.close();
    }

    public static class Data {

        public List<Type> typeList;

        public static class Type {
            public String id;
            public String name;
            public List<Good> goodList;

            public Type(GoodType goodType) {
                this.id = String.valueOf(goodType.getId());
                this.name = goodType.getName();
                this.goodList = new ArrayList<>();
            }

            public static class Good {
                public String id;
                public String name;
                public String description;
                public String imageUrl;
                public String price;

                public Good(top.knxy.fruits.DataBase.Table.Good good) {
                    this.id = String.valueOf(good.getId());
                    this.name = good.getName();
                    this.description = good.getDescription();
                    this.imageUrl = good.getImageUrl();
                    this.price = good.getPrice();
                }
            }
        }
    }
}
