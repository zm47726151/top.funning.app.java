package top.knxy.fruits.Service.Login.ModifyPwd;

import org.apache.ibatis.session.SqlSession;
import top.knxy.fruits.DataBase.DAL.LoginDAL;
import top.knxy.fruits.DataBase.MyBatisUtils;
import top.knxy.fruits.DataBase.Table.Admin;
import top.knxy.fruits.Service.BaseService;
import top.knxy.fruits.Utils.PwdUtils;
import top.knxy.fruits.Utils.ServiceUtils;
import top.knxy.fruits.Utils.TextUtils;

import javax.sql.rowset.serial.SerialException;

public class M1019 extends BaseService {

    public String adminId;
    public String prePassword;
    public String newPassword;

    @Override
    protected void run() throws Exception {

        if (TextUtils.isEmpty(prePassword) ||
                TextUtils.isEmpty(newPassword) ||
                newPassword.length() < 6 ||
                newPassword.length() > 18
        ) {
            throw new SerialException();
        }

        SqlSession session = MyBatisUtils.getSession();
        LoginDAL dal = session.getMapper(LoginDAL.class);
        Admin admin = dal.getAdminById(adminId);
        if (admin == null) {
            throw new SerialException();
        }

        String salt = admin.getSalt();
        String prePassword = PwdUtils.sha1(this.prePassword + salt);
        if (!prePassword.equals(admin.getPassword())) {
            ServiceUtils.createError(this, "密码错误");
            session.close();
            return;
        }

        String newSalt = PwdUtils.createSalt();
        admin.setSalt(newSalt);
        String newPassword = PwdUtils.sha1(this.newPassword + newSalt);
        admin.setPassword(newPassword);

        dal.updatePassword(admin);
        session.commit();

        ServiceUtils.createSuccess(this);
    }
}
