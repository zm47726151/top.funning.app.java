package top.knxy.fruits.Servlet.admin.Order;


import top.knxy.fruits.Config.C;
import top.knxy.fruits.Service.Manager.Order.ListServices;
import top.knxy.fruits.Utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/order/list")
public class List extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ListServices services = ServletUtils.requestParamToModel(req, ListServices.class);
        services.start();
        if (services.code == C.Service.success) {
            req.setAttribute("data", services.data);
            req.setAttribute("viewJsp", "/admin/order/list.jsp");
            req.getRequestDispatcher("/admin/common.jsp").forward(req, resp);
        } else {
            resp.sendError(500, services.msg);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
