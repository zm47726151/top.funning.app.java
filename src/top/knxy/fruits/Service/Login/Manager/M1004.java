package top.knxy.fruits.Service.Login.Manager;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.MyBatisUtils;
import top.knxy.fruits.DataBase.Table.Admin;
import top.knxy.fruits.Service.BaseService;
import top.knxy.fruits.DataBase.DAL.LoginDAL;
import top.knxy.fruits.Utils.PwdUtils;
import top.knxy.fruits.Utils.ServiceUtils;
import top.knxy.fruits.Utils.TextUtils;

import java.util.Calendar;
import java.util.Date;

public class M1004 extends BaseService {

    public String username;
    public String password;

    public Admin result;

 
    @Override
    public void run() throws Exception {
        if (TextUtils.isEmpty(username)) {
            ServiceUtils.createError(this, "username is empty");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            ServiceUtils.createError(this, "password is empty");
            return;
        }


        SqlSession session = MyBatisUtils.getSession();
        LoginDAL mapper = session.getMapper(LoginDAL.class);
        Admin admin = mapper.getAdminByUserName(username);

        if (admin == null) {
            ServiceUtils.createError(this, "username is not exist");
            return;
        }

        long now = new Date().getTime();

        if (admin.getLastFailTime() != null) {
            if (now > admin.getLastFailTime().getTime() * 24 * 60 * 60 * 1000) {
                admin.setLastFailTime(getInitData());
                admin.setFail(0);
                mapper.updateFail(admin);
                session.commit();
            } else if (admin.getFail() > 3) {
                ServiceUtils.createError(this, "input wrong password is too much, you can try 24h after.");
                return;
            }
        }

        password = PwdUtils.sha1(password + admin.getSalt());
        if (password.equals(admin.getPassword())) {
            if (admin.getFail() > 0 || admin.getLastFailTime() != null) {
                admin.setLastFailTime(getInitData());
                admin.setFail(0);
                mapper.updateFail(admin);
                session.commit();
            }
            result = admin;
            ServiceUtils.createSuccess(this);
        } else {
            int fail = admin.getFail();
            fail++;
            admin.setFail(fail);
            if (admin.getLastFailTime() == null) {
                admin.setLastFailTime(new Date());
            }
            mapper.updateFail(admin);
            session.commit();
            ServiceUtils.createError(this, "password is wrong, it is the " + admin.getFail() + " time you input wrong password");
        }

        session.close();
    }

    public static Date getInitData() {

        Calendar cal = Calendar.getInstance();
        // 如果想设置为某个日期，可以一次设置年月日时分秒，由于月份下标从0开始赋值月份要-1
        // cal.set(year, month, date, hourOfDay, minute, second);
        cal.set(2000, 0, 1, 0, 0, 0);
        return cal.getTime();

    }
}
