package top.knxy.fruits.Servlet.Admin.Order;


import top.knxy.fruits.Config.C;
import top.knxy.fruits.Config.V;
import top.knxy.fruits.Service.Order.ChangeState.M1005;
import top.knxy.fruits.Service.Order.Search.M1002;
import top.knxy.fruits.Utils.ServletUtils;

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
        if (service.code == C.Service.success) {
            req.setAttribute(V.data, service.data);
            ServletUtils.setViewAndForward(req, resp);
        } else {
            resp.sendError(500, service.msg);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        M1005 service = ServletUtils.requestParamToModel(req, M1005.class);
        service.start();
        if (service.code == C.Service.success) {
            doGet(req, resp);
        } else {
            resp.sendError(500, service.msg);
        }

    }
}
