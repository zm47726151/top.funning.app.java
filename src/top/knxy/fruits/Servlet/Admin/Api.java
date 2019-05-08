package top.knxy.fruits.Servlet.Admin;

import top.knxy.fruits.Service.Good.Add.M1014;
import top.knxy.fruits.Service.Good.Delete.M1012;
import top.knxy.fruits.Service.Good.Modify.M1011;
import top.knxy.fruits.Service.GoodType.Add.M1006;
import top.knxy.fruits.Service.GoodType.Delete.M1009;
import top.knxy.fruits.Service.GoodType.Modify.M1008;
import top.knxy.fruits.Service.Index.Poster.Put.M1021;
import top.knxy.fruits.Service.Index.Poster.Remove.M1022;
import top.knxy.fruits.Service.Order.Normal.Refund.Admin.M1018;
import top.knxy.fruits.Service.Order.Normal.Undo.GetNumber.M1017;
import top.knxy.fruits.Service.QiNiu.getUploadToken.M1015;
import top.knxy.library.ApiHandle;
import top.knxy.library.BaseApi;
import top.knxy.library.Config.V;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/api")
public class Api extends BaseApi {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new Handle(request, response).start();
    }

    private static class Handle extends ApiHandle {

        public Handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
            super(request, response);
        }

        public static Class[] serviceList = {
                M1006.class, M1008.class, M1009.class, M1011.class, M1012.class,
                M1014.class, M1015.class, M1017.class, M1018.class, M1021.class,
                M1022.class};

        @Override
        protected void run() throws ServletException, IOException {
            if ("M1020".equals(body.cmd)) {
                //exit
                request.getSession().removeAttribute(V.adminId);
                responseSuccess();
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
