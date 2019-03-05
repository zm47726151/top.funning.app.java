package top.knxy.fruits.Service.GoodType.List;

import top.knxy.fruits.DataBase.MyBatisUtils;
import top.knxy.fruits.DataBase.Table.GoodType;
import top.knxy.fruits.Service.BaseService;
import top.knxy.fruits.DataBase.DAL.GoodTypeDAL;
import top.knxy.fruits.Utils.ServiceUtils;

import java.util.List;

public class M1007 extends BaseService {


    @Override
    protected void run() throws Exception {
        Data data = new Data();
        data.typeList = MyBatisUtils.getSession().getMapper(GoodTypeDAL.class).getList();
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
