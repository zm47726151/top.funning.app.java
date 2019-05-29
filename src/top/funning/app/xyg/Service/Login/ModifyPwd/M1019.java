package top.funning.app.xyg.Service.Login.ModifyPwd;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.DataBase.DAL.AdminDAL;
import top.funning.app.xyg.DataBase.Table.Admin;
import top.funning.library.BaseService;
import top.funning.library.Utils.PwdUtils;
import top.funning.library.Utils.ServiceUtils;
import top.funning.library.Utils.TextUtils;

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

        SqlSession session = getSqlSession();;
        AdminDAL dal = session.getMapper(AdminDAL.class);
        Admin admin = dal.getAdminById(adminId);
        if (admin == null) {
            throw new SerialException();
        }

        String salt = admin.getSalt();
        String prePassword = PwdUtils.sha1(this.prePassword + salt);
        if (!prePassword.equals(admin.getPassword())) {
            ServiceUtils.createError(this, "密码错误");

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
