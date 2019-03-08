package top.knxy.fruits.Servlet.Admin;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import top.knxy.fruits.Bean.Request;
import top.knxy.fruits.Config.V;
import top.knxy.fruits.Service.Index.C1001;
import top.knxy.fruits.Utils.ApiUtils;
import top.knxy.fruits.Utils.TextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/admin/index"})
public class Index extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/admin/order/list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String content = ApiUtils.getString(request);
        PrintWriter pw = response.getWriter();

        Gson gson = new Gson();

        if (TextUtils.isEmpty(content)) {
            ApiUtils.responseError(pw, "input json is empty");
            return;
        }

        Request rq = gson.fromJson(content, Request.class);
        String cmd = rq.cmd;
        JsonObject data = rq.data == null ? new JsonObject() : rq.data;

        int adminId = (int) request.getSession().getAttribute(V.adminId);


        if ("C1001".equals(cmd)) {
            //getGood wx open id
            C1001 c1001 = gson.fromJson(data, C1001.class);
            c1001.start();
            ApiUtils.response(pw, c1001);
        } else {
            ApiUtils.responseError(pw, "unknown cmd");
        }

    }
}
