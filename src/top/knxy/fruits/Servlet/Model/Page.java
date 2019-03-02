package top.knxy.fruits.Servlet.Model;

import top.knxy.fruits.Utils.StrUtils;

public class Page {

    private int index;

    private int size = 30;

    public Page(String pString) throws Exception{
        int page = 1;
        if(!StrUtils.isEmpty(pString)){
            page = Integer.valueOf(pString);
        }

        if (page < 1) {
            throw new Exception("page < 1");
        }
        index = (page - 1) * size;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
