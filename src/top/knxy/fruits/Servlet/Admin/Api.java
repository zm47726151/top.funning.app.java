package top.knxy.fruits.Servlet.Admin;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import top.knxy.fruits.Bean.Request;
import top.knxy.fruits.Config.C;
import top.knxy.fruits.Config.V;
import top.knxy.fruits.Service.Good.Get.C1009;
import top.knxy.fruits.Service.GoodType.Add.M1006;
import top.knxy.fruits.Service.Index.C1001;
import top.knxy.fruits.Service.Login.Wechat.C1003;
import top.knxy.fruits.Service.Order.Cancel.C1007;
import top.knxy.fruits.Service.Order.Comfirm.C1004;
import top.knxy.fruits.Service.Order.Create.C1002;
import top.knxy.fruits.Service.Order.Get.C1006;
import top.knxy.fruits.Service.Order.List.C1005;
import top.knxy.fruits.Service.Order.Refund.C1008;
import top.knxy.fruits.Service.SessionInfo;
import top.knxy.fruits.Utils.ApiUtils;
import top.knxy.fruits.Utils.StrUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/admin/api")
public class Api extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Api() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        ApiUtils.responseError(pw, "method should be POST");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String content = ApiUtils.getString(request);
        PrintWriter pw = response.getWriter();

        Gson gson = new Gson();

        if (StrUtils.isEmpty(content)) {
            ApiUtils.responseError(pw, "input json is empty");
            return;
        }

        Request rq = gson.fromJson(content, Request.class);
        String cmd = rq.cmd;
        JsonObject data = rq.data == null ? new JsonObject() : rq.data;

        //int adminId = (int) request.getSession().getAttribute(V.adminId);


        if ("M1006".equals(cmd)) {
            //good type add
            M1006 m1006 = gson.fromJson(data, M1006.class);
            m1006.start();
            ApiUtils.response(pw, m1006);
        } else {
            ApiUtils.responseError(pw, "unknown cmd");
        }
    }
}

