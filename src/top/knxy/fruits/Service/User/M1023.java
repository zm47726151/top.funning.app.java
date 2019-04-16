package top.knxy.fruits.Service.User;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.DAL.UserDAL;
import top.knxy.library.Utils.MyBatisUtils;
import top.knxy.library.BaseService;
import top.knxy.library.Utils.ServiceUtils;

import java.util.List;

public class M1023 extends BaseService {
    @Override
    protected void run() throws Exception {
        SqlSession session = MyBatisUtils.getSession();
        UserDAL userDal = session.getMapper(UserDAL.class);
        Data data = new Data();
        this.data = data;
        data.users = userDal.getList();
        session.close();
        ServiceUtils.createSuccess(this);
    }


    public static class Data {
        private List<User> users;

        public List<User> getUsers() {
            return users;
        }

        public void setUsers(List<User> users) {
            this.users = users;
        }

        public static class User {
            private String id;
            private String openId;
            private String amount;
            private String score;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getScore() {
                return score;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.score = String.valueOf(new Double(Double.valueOf(amount) / 100).intValue());
                this.amount = amount;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getOpenId() {
                return openId;
            }

            public void setOpenId(String openId) {
                this.openId = openId;
            }
        }
    }
}
