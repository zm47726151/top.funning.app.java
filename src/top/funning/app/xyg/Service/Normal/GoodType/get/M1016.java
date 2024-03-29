package top.funning.app.xyg.Service.Normal.GoodType.get;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.GoodTypeDAL;
import top.funning.app.xyg.Service.Normal.Good.Get.M1013;
import top.knxy.library.BaseService;
import top.knxy.library.Utils.ServiceUtils;

import javax.sql.rowset.serial.SerialException;
import java.util.List;

public class M1016 extends BaseService {
    @Override
    protected void run() throws Exception {
        SqlSession session = getSqlSession();
        GoodTypeDAL gtDal = session.getMapper(GoodTypeDAL.class);
        List<M1013.GoodType> list = gtDal.getUsefulList();
        if (list == null || list.isEmpty()) {
            throw new SerialException("list empty");
        }

        M1013.GoodType item = list.get(0);
        Data data = new Data();
        data.type = item.getId();
        data.typeStr = item.getName();
        data.typeList = list;
        this.data = data;

        session.commit();
        ServiceUtils.createSuccess(this);
    }

    public static class Data {
        private String type;
        private String typeStr;
        private List<M1013.GoodType> typeList;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTypeStr() {
            return typeStr;
        }

        public void setTypeStr(String typeStr) {
            this.typeStr = typeStr;
        }

        public List<M1013.GoodType> getTypeList() {
            return typeList;
        }

        public void setTypeList(List<M1013.GoodType> typeList) {
            this.typeList = typeList;
        }
    }
}
