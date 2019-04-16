package top.knxy.fruits.Servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import top.knxy.library.Vehicle.Request;
import top.knxy.fruits.Service.Address.PosterComputer.C1013;
import top.knxy.fruits.Service.Good.Get.C1009;
import top.knxy.fruits.Service.Good.Search.C1012;
import top.knxy.fruits.Service.Index.C1001;
import top.knxy.fruits.Service.Login.Wechat.C1003;
import top.knxy.library.Config.C;
import top.knxy.fruits.Service.Order.Cancel.C1007;
import top.knxy.fruits.Service.Order.Comfirm.C1004;
import top.knxy.fruits.Service.Order.Create.C1002;
import top.knxy.fruits.Service.Order.Get.C1006;
import top.knxy.fruits.Service.Order.List.C1005;
import top.knxy.fruits.Service.Order.Pay.C1010;
import top.knxy.fruits.Service.Order.Refund.Client.C1008;
import top.knxy.fruits.Service.SessionInfo;
import top.knxy.library.Utils.ApiUtils;
import top.knxy.library.Utils.TextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/api")
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

        SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute("UserInfo");
        if ("C1002".equals(cmd) || "C1004".equals(cmd) ||
                "C1005".equals(cmd) || "C1006".equals(cmd) ||
                "C1006".equals(cmd) || "C1007".equals(cmd) ||
                "C1008".equals(cmd) || "C1010".equals(cmd) || "C1012".equals(cmd)) {

            if (sessionInfo == null) {
                ApiUtils.responseError(pw, C.Client.NEED_LOGIN, "还没有登录");
                return;
            }
        }

        if ("C1001".equals(cmd)) {
            //get wx open id
            C1001 c1001 = gson.fromJson(data, C1001.class);
            c1001.start();
            ApiUtils.response(pw, c1001);
        } else if ("C1002".equals(cmd)) {
            //order created
            C1002 c1002 = gson.fromJson(data, C1002.class);
            c1002.userId = sessionInfo.userId;
            c1002.start();
            ApiUtils.response(pw, c1002);
        } else if ("C1003".equals(cmd)) {
            //Login
            C1003 c1003 = gson.fromJson(data, C1003.class);
            c1003.start();
            if (c1003.code == C.Service.SUCCESS) {
                HttpSession session = request.getSession();
                C1003.Data d = (C1003.Data) c1003.data;
                session.setAttribute("UserInfo", new SessionInfo(d.openid, d.sessionKey, d.userId));
                ApiUtils.responseSuccess(pw);
            } else {
                ApiUtils.responseError(pw, c1003.msg);
            }
        } else if ("C1004".equals(cmd)) {
            //order confirm
            C1004 c1004 = gson.fromJson(data, C1004.class);
            c1004.userId = sessionInfo.userId;
            c1004.start();
            ApiUtils.response(pw, c1004);
        } else if ("C1005".equals(cmd)) {
            //order list
            C1005 c1005 = gson.fromJson(data, C1005.class);
            c1005.userId = sessionInfo.userId;
            c1005.start();
            ApiUtils.response(pw, c1005);
        } else if ("C1006".equals(cmd)) {
            //order get
            C1006 c1006 = gson.fromJson(data, C1006.class);
            c1006.userId = sessionInfo.userId;
            c1006.start();
            ApiUtils.response(pw, c1006);
        } else if ("C1007".equals(cmd)) {
            //order cancel
            C1007 c1007 = gson.fromJson(data, C1007.class);
            c1007.userId = sessionInfo.userId;
            c1007.start();
            ApiUtils.response(pw, c1007);
        } else if ("C1008".equals(cmd)) {
            //order refund
            C1008 c1008 = gson.fromJson(data, C1008.class);
            c1008.userId = sessionInfo.userId;
            c1008.start();
            ApiUtils.response(pw, c1008);
        } else if ("C1010".equals(cmd)) {
            //order pay
            C1010 service = gson.fromJson(data, C1010.class);
            service.userId = sessionInfo.userId;
            service.start();
            ApiUtils.response(pw, service);
        } else if ("C1009".equals(cmd)) {
            //Good get
            C1009 c1009 = gson.fromJson(data, C1009.class);
            c1009.start();
            ApiUtils.response(pw, c1009);
        } else if ("C1012".equals(cmd)) {
            //Good search
            ApiUtils.doService(C1012.class, data, gson, pw);
        }  else if ("C1013".equals(cmd)) {
            //Good search
            ApiUtils.doService(C1013.class, data, gson, pw);
        } else {
            ApiUtils.responseError(pw, "unknown cmd");
        }
    }
}

