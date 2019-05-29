package top.funning.app.xyg.Servlet.Pay;

import top.funning.app.xyg.Service.Pay.C1011;
import top.knxy.library.Config.Code;
import top.knxy.library.Utils.LogUtils;
import top.knxy.library.Utils.ServletUtils;
import top.knxy.library.Utils.XmlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/pay/confirm")
public class Confirm extends HttpServlet {
    public static final String TAG = "Pay.Confirm";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String data = ServletUtils.getPostBodyString(request);
        LogUtils.i(TAG, data);

        PrintWriter writer = response.getWriter();

        C1011 c1011 = new C1011();
        c1011.data = data;
        c1011.start();
        Map<Object, Object> map = new HashMap<>(2);
        if (c1011.code == Code.Service.SUCCESS) {
            map.put("return_code", "SUCCESS");
            map.put("return_msg", "OK");
        } else {
            map.put("return_code", "FAIL");
            map.put("return_msg", c1011.msg);
        }

        String result = XmlUtils.mapToXmlStr(map, true);
        writer.print(result);
        writer.close();
    }
}
