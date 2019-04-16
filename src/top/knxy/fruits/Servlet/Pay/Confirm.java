package top.knxy.fruits.Servlet.Pay;

import top.knxy.library.Config.Code;
import top.knxy.fruits.Service.Pay.C1011;
import top.knxy.library.Utils.ApiUtils;
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String data = ApiUtils.getString(request);
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
        System.out.println("C1011:" + result);
        writer.print(result);
        writer.close();
    }
}
