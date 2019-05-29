package top.funning.app.xyg.Servlet;

import top.funning.app.xyg.Service.Address.PosterComputer.C1013;
import top.funning.app.xyg.Service.Group.Good.Get.C1015;
import top.funning.app.xyg.Service.Group.Good.List.C1014;
import top.funning.app.xyg.Service.Group.Order.Cancel.C1021;
import top.funning.app.xyg.Service.Group.Order.Create.C1016;
import top.funning.app.xyg.Service.Group.Order.Get.C1017;
import top.funning.app.xyg.Service.Group.Order.GetResult.C1019;
import top.funning.app.xyg.Service.Group.Order.List.C1020;
import top.funning.app.xyg.Service.Group.Order.Pay.C1018;
import top.funning.app.xyg.Service.Group.Order.Refund.Client.C1022;
import top.funning.app.xyg.Service.Index.C1001;
import top.funning.app.xyg.Service.Login.Wechat.C1003;
import top.funning.app.xyg.Service.Normal.Good.Get.C1009;
import top.funning.app.xyg.Service.Normal.Good.Search.C1012;
import top.funning.app.xyg.Service.Normal.Order.Cancel.C1007;
import top.funning.app.xyg.Service.Normal.Order.Confirm.C1004;
import top.funning.app.xyg.Service.Normal.Order.Create.C1002;
import top.funning.app.xyg.Service.Normal.Order.Get.C1006;
import top.funning.app.xyg.Service.Normal.Order.List.C1005;
import top.funning.app.xyg.Service.Normal.Order.Pay.C1010;
import top.funning.app.xyg.Service.Normal.Order.Refund.Client.C1008;
import top.funning.app.xyg.Service.Pay.C1011;
import top.funning.app.xyg.Service.SessionInfo;
import top.funning.library.ApiHandle;
import top.funning.library.BaseApi;
import top.funning.library.Config.Code;
import top.funning.library.Config.V;
import top.funning.library.Utils.TextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/api")
public class Api extends BaseApi {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new Handle(request, response).start();
    }

    private static class Handle extends ApiHandle {

        public Handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
            super(request, response);
        }

        public static Class[] serviceList = {
                C1001.class, C1002.class, C1003.class, C1004.class, C1005.class,
                C1006.class, C1007.class, C1008.class, C1009.class, C1010.class,
                C1011.class, C1012.class, C1013.class, C1014.class, C1015.class,
                C1016.class, C1017.class, C1018.class, C1019.class, C1020.class,
                C1021.class, C1022.class};

        @Override
        protected void run() throws ServletException, IOException {

            SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(V.userInfo);
            if ("C1002".equals(body.cmd) || "C1004".equals(body.cmd) ||
                    "C1005".equals(body.cmd) || "C1006".equals(body.cmd) ||
                    "C1006".equals(body.cmd) || "C1007".equals(body.cmd) ||
                    "C1008".equals(body.cmd) || "C1010".equals(body.cmd) ||
                    "C1012".equals(body.cmd) || "C1016".equals(body.cmd) ||
                    "C1017".equals(body.cmd) || "C1018".equals(body.cmd) ||
                    "C1019".equals(body.cmd) || "C1020".equals(body.cmd) ||
                    "C1021".equals(body.cmd) || "C1022".equals(body.cmd)
            ) {
                if (sessionInfo == null) {
                    responseError(Code.Client.NEED_LOGIN, "还没有登录");
                    return;
                }
            }

            if (sessionInfo != null && !TextUtils.isEmpty(sessionInfo.userId)) {
                body.data.addProperty(V.userId, sessionInfo.userId);
            }

            if ("C1003".equals(body.cmd)) {
                //Login
                C1003 c1003 = gson.fromJson(body.data, C1003.class);
                c1003.start();
                if (c1003.code == Code.Service.SUCCESS) {
                    HttpSession session = request.getSession();
                    C1003.Data d = (C1003.Data) c1003.data;
                    session.setAttribute(V.userInfo, new SessionInfo(d.openid, d.sessionKey, d.userId));
                    responseSuccess();
                } else {
                    responseError(c1003.msg);
                }
                return;
            }

            for (Class cls : serviceList) {
                if (cls.getSimpleName().equals(body.cmd)) {
                    doService(cls, body.data);
                    return;
                }
            }

            responseError("unknown cmd");
        }
    }
}

