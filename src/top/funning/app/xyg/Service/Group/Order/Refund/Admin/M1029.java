package top.funning.app.xyg.Service.Group.Order.Refund.Admin;

import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.Config.C;
import top.funning.app.xyg.DataBase.DAL.GroupOrderDAL;
import top.funning.app.xyg.DataBase.Table.GroupOrder;
import top.funning.app.xyg.Service.Normal.Order.Refund.Admin.M1018;
import top.funning.app.xyg.Servlet.Admin.Remind;
import top.knxy.library.BaseService;
import top.knxy.library.ServiceException;
import top.knxy.library.Utils.LogUtils;
import top.knxy.library.Utils.ServiceUtils;
import top.knxy.library.Utils.TextUtils;
import top.knxy.library.Utils.XmlUtils;
import top.knxy.library.Vehicle.WeChat.Refund.Result;

import java.math.BigDecimal;
import java.util.TreeMap;

public class M1029 extends BaseService {

    public static final String TAG = "Group.Order.Refund.Admin";

    public String id;


    @Override
    protected void run() throws Exception {
        if (TextUtils.isEmpty(id)) {
            ServiceUtils.createError(this);
            return;
        }

        SqlSession session = getSqlSession();
        GroupOrderDAL goDal = session.getMapper(GroupOrderDAL.class);
        GroupOrder order = goDal.getById(id);

        if (order.getState() != 5) {
            throw new ServiceException("state != 5");
        }

        TreeMap<Object, Object> map = new TreeMap<>();
        map.put("appid", C.WeChat.appid);
        map.put("mch_id", C.WCPay.mchId);
        map.put("nonce_str", ServiceUtils.getUUid());
        map.put("sign_type", "MD5");
        map.put("out_trade_no", order.getId());
        map.put("out_refund_no", ServiceUtils.getUUid() + ServiceUtils.getUUid());
        BigDecimal money = new BigDecimal(order.getPrice()).multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_UP);
        map.put("total_fee", money);
        map.put("refund_fee", money);
        map.put("sign", ServiceUtils.getWXPaySignValue(map, C.WCPay.apiKey));

        String data = XmlUtils.mapToXmlStr(map, false);
        Result result = M1018.doRefund(data);

        if (!"SUCCESS".equals(result.return_code)) {

            ServiceUtils.createError(this, result.return_msg);
            LogUtils.i(TAG, String.format("refund fail: return_code = %s, return_msg = %s, order id = %s",
                    result.return_code,
                    result.return_msg,
                    order.getId()));
            return;
        }

        if (!"SUCCESS".equals(result.result_code)) {
            ServiceUtils.createError(this, result.err_code + ":" + result.err_code_des);
            LogUtils.i(TAG, String.format("refund fail: err_code = %s, err_code_des = %s, order id = %s",
                    result.err_code,
                    result.err_code_des,
                    order.getId()));
            return;
        }

        int row = goDal.updateState(id, "7");
        session.commit();

        Remind.broadcast();
        ServiceUtils.createSuccess(this);
    }

}
