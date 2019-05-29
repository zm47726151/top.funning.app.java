package top.funning.app.xyg.Service.Normal.GoodType.List;

import top.funning.app.xyg.DataBase.DAL.GoodTypeDAL;
import top.funning.app.xyg.DataBase.Table.GoodType;
import top.funning.library.BaseService;
import top.funning.library.Utils.ServiceUtils;

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
