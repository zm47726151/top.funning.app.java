package top.funning.app.xyg.Service.Normal.Order.Refund.Admin;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import top.funning.app.xyg.Config.C;
import top.funning.app.xyg.DataBase.DAL.OrderDAL;
import top.funning.app.xyg.DataBase.Table.Order;
import top.knxy.library.BaseService;
import top.knxy.library.ServiceException;
import top.knxy.library.Utils.*;
import top.knxy.library.Vehicle.WeChat.Refund.Result;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.security.KeyStore;
import java.util.TreeMap;

public class M1018 extends BaseService {

    public static final String TAG = "Normal.Order.Refund.Admin";

    public String id;


    @Override
    protected void run() throws Exception {
        if (TextUtils.isEmpty(id)) {
            ServiceUtils.createError(this);
            return;
        }


        SqlSession session = getSqlSession();
        OrderDAL operation = session.getMapper(OrderDAL.class);
        Order order = operation.getOrder(id);

        if (order.getState() != 4) {

            throw new ServiceException("修改不合法");
        }

        TreeMap<Object, Object> map = new TreeMap<>();
        map.put("appid", C.WeChat.appid);
        map.put("mch_id", C.WCPay.mchId);
        map.put("nonce_str", ServiceUtils.getUUid());
        map.put("sign_type", "MD5");
        map.put("out_trade_no", order.getId());
        map.put("out_refund_no", ServiceUtils.getUUid() + ServiceUtils.getUUid());
        BigDecimal money = new BigDecimal(order.getAmount()).multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_UP);
        map.put("total_fee", money);
        map.put("refund_fee", money);
        map.put("sign", ServiceUtils.getWXPaySignValue(map, C.WCPay.apiKey));

        String data = XmlUtils.mapToXmlStr(map, false);
        Result result = doRefund(data);

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

        order.setState(6);
        operation.changeState(order);
        session.commit();

        ServiceUtils.createSuccess(this);
    }

    public static Result doRefund(String data) throws Exception {
        String url = "https://api.mch.weixin.qq.com/secapi/pay/refund";
        /**
         * 注意PKCS12证书 是从微信商户平台-》账户设置-》 API安全 中下载的
         */

        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        String path = FileUtils.getFilePathInSrc("apiclient_cert.p12");
        FileInputStream instream = new FileInputStream(new File(path));//P12文件目录
        try {
            /**
             * 此处要改
             **/
            keyStore.load(instream, C.WCPay.mchId.toCharArray());//这里写密码..默认是你的MCHID
        } catch (Exception e) {
            throw e;
        } finally {
            instream.close();
        }

        // Trust own CA and all self-signed certs
        /**
         * 此处要改
         **/
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, C.WCPay.mchId.toCharArray())//这里也是写密码的
                .build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[]{"TLSv1"},
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
        try {
            HttpPost httpPost = new HttpPost(url); // 设置响应头信息
            httpPost.addHeader("Connection", "keep-alive");
            httpPost.addHeader("Accept", "*/*");
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpPost.addHeader("Host", "api.mch.weixin.qq.com");
            httpPost.addHeader("X-Requested-With", "XMLHttpRequest");
            httpPost.addHeader("Cache-Control", "max-age=0");
            httpPost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
            httpPost.setEntity(new StringEntity(data, "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();

                String rp = EntityUtils.toString(response.getEntity(), "UTF-8");
                LogUtils.i(TAG, rp);
                EntityUtils.consume(entity);
                return XmlUtils.xmlStrToBean(rp, Result.class);
            } catch (Exception e) {
                throw e;
            } finally {
                response.close();
            }
        } catch (Exception e) {
            throw e;
        } finally {
            httpclient.close();
        }
    }

}
