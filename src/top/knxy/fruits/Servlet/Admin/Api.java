package top.knxy.fruits.Servlet.Admin;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import top.knxy.fruits.Bean.Request;
import top.knxy.fruits.Config.V;
import top.knxy.fruits.Service.Good.Add.M1014;
import top.knxy.fruits.Service.Good.Delete.M1012;
import top.knxy.fruits.Service.Good.Modify.M1011;
import top.knxy.fruits.Service.GoodType.Add.M1006;
import top.knxy.fruits.Service.GoodType.Delete.M1009;
import top.knxy.fruits.Service.GoodType.Modify.M1008;
import top.knxy.fruits.Service.Order.Refund.Admin.M1018;
import top.knxy.fruits.Service.Order.Undo.GetNumber.M1017;
import top.knxy.fruits.Service.QiNiu.getUploadToken.M1015;
import top.knxy.fruits.Utils.ApiUtils;
import top.knxy.fruits.Utils.TextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        if (TextUtils.isEmpty(content)) {
            ApiUtils.responseError(pw, "input json is empty");
            return;
        }

        Request rq = gson.fromJson(content, Request.class);
        String cmd = rq.cmd;
        JsonObject data = rq.data == null ? new JsonObject() : rq.data;

        int adminId = (int) request.getSession().getAttribute(V.adminId);

        System.out.println(cmd + ":" + data);
        if ("M1006".equals(cmd)) {
            //good type add
            M1006 m1006 = gson.fromJson(data, M1006.class);
            m1006.start();
            ApiUtils.response(pw, m1006);
        } else if ("M1009".equals(cmd)) {
            //good type delete
            M1009 m1006 = gson.fromJson(data, M1009.class);
            m1006.start();
            ApiUtils.response(pw, m1006);
        } else if ("M1008".equals(cmd)) {
            //good type modify
            ApiUtils.doService(M1008.class, data, gson, pw);
        } else if ("M1011".equals(cmd)) {
            //good Modify
            ApiUtils.doService(M1011.class, data, gson, pw);
        } else if ("M1012".equals(cmd)) {
            //good delete
            ApiUtils.doService(M1012.class, data, gson, pw);
        } else if ("M1014".equals(cmd)) {
            //good add
            ApiUtils.doService(M1014.class, data, gson, pw);
        } else if ("M1015".equals(cmd)) {
            //get UpToken
            ApiUtils.doService(M1015.class, data, gson, pw);
        } else if ("M1018".equals(cmd)) {
            //order refund
            ApiUtils.doService(M1018.class, data, gson, pw);
        } else if ("M1017".equals(cmd)) {
            //get undo list count
            ApiUtils.doService(M1017.class, data, gson, pw);
        }  else if ("M1020".equals(cmd)) {
            //exit
            request.getSession().removeAttribute(V.adminId);
            ApiUtils.responseSuccess(pw);
        } else {
            ApiUtils.responseError(pw, "unknown cmd");
        }
    }
}

