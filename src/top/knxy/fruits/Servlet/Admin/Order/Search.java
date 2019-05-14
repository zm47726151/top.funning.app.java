package top.knxy.fruits.Servlet.Admin.Order;


import top.knxy.library.Config.Code;
import top.knxy.library.Config.V;
import top.knxy.fruits.Service.Normal.Order.ChangeState.M1005;
import top.knxy.fruits.Service.Normal.Order.Search.M1002;
import top.knxy.library.Utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/order/search"})
public class Search extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        M1002 service = ServletUtils.requestParamToModel(req, M1002.class);
        service.start();
        if (service.code == Code.Service.SUCCESS) {
            req.setAttribute(V.data, service.data);
            ServletUtils.setViewAndForward(req, resp);
        } else {
            ServletUtils.setViewAndForward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        M1005 service = ServletUtils.requestParamToModel(req, M1005.class);
        service.start();
        if (service.code == Code.Service.SUCCESS) {
            doGet(req, resp);
        } else {
            resp.sendError(500, service.msg);
        }

    }
}
