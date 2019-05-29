package top.funning.app.xyg.Service.Index;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.GoodDAL;
import top.funning.app.xyg.DataBase.DAL.GoodTypeDAL;
import top.funning.app.xyg.DataBase.Redis;
import top.funning.app.xyg.DataBase.Table.Good;
import top.funning.library.Config.V;
import top.funning.library.BaseService;
import top.funning.app.xyg.Service.Normal.Good.Get.M1013;
import top.funning.library.Utils.ServiceUtils;

import java.util.*;

public class C1001 extends BaseService {


    @Override
    public void run() throws Exception {
        SqlSession session = getSqlSession();
        GoodTypeDAL gtDal = session.getMapper(GoodTypeDAL.class);
        List<M1013.GoodType> goodTypeList = gtDal.getUsefulList();
        GoodDAL gDal = session.getMapper(GoodDAL.class);
        List<Good> goodList = gDal.getUsefulList();


        Data data = new Data();
        data.typeList = new ArrayList<>();
        Map<String, Data.Type> typeMap = new HashMap<>();

        for (M1013.GoodType goodType : goodTypeList) {
            Data.Type type = new Data.Type(goodType);
            typeMap.put(String.valueOf(goodType.getId()), type);
            data.typeList.add(type);
        }

        for (Good good : goodList) {
            Data.Type.Good dtg = new Data.Type.Good(good);
            Data.Type type = typeMap.get(String.valueOf(good.getType()));
            type.goodList.add(dtg);
        }

        data.postImageUrl = Redis.get(V.postImageUrl);

        this.data = data;

        ServiceUtils.createSuccess(this);

    }

    public static class Data {

        public String postImageUrl;

        public List<Type> typeList;

        public static class Type {
            public String id;
            public String name;
            public List<Good> goodList;

            public Type(M1013.GoodType goodType) {
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

                public Good(top.funning.app.xyg.DataBase.Table.Good good) {
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
