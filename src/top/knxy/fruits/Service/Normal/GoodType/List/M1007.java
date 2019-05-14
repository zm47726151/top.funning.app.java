package top.knxy.fruits.Service.Normal.GoodType.List;

import top.knxy.fruits.DataBase.Table.GoodType;
import top.knxy.library.BaseService;
import top.knxy.fruits.DataBase.DAL.GoodTypeDAL;
import top.knxy.library.Utils.ServiceUtils;

import java.util.List;

public class M1007 extends BaseService {


    @Override
    protected void run() throws Exception {
        Data data = new Data();
        data.typeList = getSqlSession().getMapper(GoodTypeDAL.class).getList();
        this.data = data;
        ServiceUtils.createSuccess(this);
    }

    public static class Data{

        private List<GoodType> typeList;

        public List<GoodType> getTypeList() {
            return typeList;
        }

        public void setTypeList(List<GoodType> typeList) {
            this.typeList = typeList;
        }
    }
}
