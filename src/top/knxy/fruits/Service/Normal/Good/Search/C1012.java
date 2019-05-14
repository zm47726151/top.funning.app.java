package top.knxy.fruits.Service.Normal.Good.Search;

import top.knxy.fruits.DataBase.Cache.Good;
import top.knxy.library.BaseService;
import top.knxy.library.Utils.ServiceUtils;
import top.knxy.library.Utils.TextUtils;

import java.util.ArrayList;

public class C1012 extends BaseService {
    public String word;

    @Override
    protected void run() throws Exception {
        Data data = new Data();
        if (TextUtils.isEmpty(word)) {
            data.dataList = new ArrayList<>();
            this.data = data;
            ServiceUtils.createSuccess(this);
            return;
        }

        data.dataList = Good.search(word);
        this.data = data;
        ServiceUtils.createSuccess(this);
    }

    public static class Data {

        private ArrayList<Good.Item> dataList;

        public ArrayList<Good.Item> getDataList() {
            return dataList;
        }

        public void setDataList(ArrayList<Good.Item> dataList) {
            this.dataList = dataList;
        }
    }
}
