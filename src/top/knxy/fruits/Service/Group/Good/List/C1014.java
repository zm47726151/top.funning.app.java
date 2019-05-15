package top.knxy.fruits.Service.Group.Good.List;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.DAL.GroupGoodDAL;
import top.knxy.library.BaseService;
import top.knxy.library.Utils.ServiceUtils;

import java.util.List;

public class C1014 extends BaseService {

    @Override
    protected void run() throws Exception {
        SqlSession session = getSqlSession();
        GroupGoodDAL dal = session.getMapper(GroupGoodDAL.class);
        List<Data.GroupGood> list = dal.getListByClient();
        Data data = new Data();
        data.dataList = list;
        this.data = data;

        ServiceUtils.createSuccess(this);
    }

    public static class Data {
        private List<GroupGood> dataList;

        public List<GroupGood> getDataList() {
            return dataList;
        }

        public void setDataList(List<GroupGood> dataList) {
            this.dataList = dataList;
        }

        public static class GroupGood {
            private String id;
            private String imageUrl;
            private String name;
            private String description;
            private String groupNum;
            private String price;

            public String getGroupNum() {
                return groupNum;
            }

            public void setGroupNum(String groupNum) {
                this.groupNum = groupNum;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }
        }
    }
}
