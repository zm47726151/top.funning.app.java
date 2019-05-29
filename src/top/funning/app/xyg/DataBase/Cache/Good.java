package top.funning.app.xyg.DataBase.Cache;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.GoodDAL;
import top.funning.library.Utils.MyBatisUtils;

import java.util.ArrayList;
import java.util.List;

public class Good {

    private final static List<Item> data = new ArrayList<>();

    public static void clear() {
        data.clear();
    }


    public static  ArrayList<Item>  search(String keyword) throws Exception {
        if (data.isEmpty()) {
            SqlSession session = MyBatisUtils.getSession();
            GoodDAL dal = session.getMapper(GoodDAL.class);
            Good.data.addAll(dal.getSearchList());
            session.close();
        }

        ArrayList<Item> result = new ArrayList<>();
        for (Item i : data) {
            String name = i.name;
            if (name.contains(keyword)) {
                if (name.equals(keyword)) {
                    result.add(0, copy(i));
                } else {
                    result.add(copy(i));
                }
            }
        }
        return result;
    }

    private static Item copy(Item preitem) {
        Item newItem = new Item();
        newItem.id = preitem.id;
        newItem.name = preitem.name;
        newItem.imageUrl = preitem.imageUrl;
        newItem.price = preitem.price;
        return newItem;
    }

    public static class Item {
        private String id;
        private String name;
        private String imageUrl;
        private String price;

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

    }
}
