package top.funning.app.xyg.Servlet.Admin.Group.Order;


import top.funning.app.xyg.Service.Group.Order.List.M1025;
import top.funning.app.xyg.Servlet.Model.Page;
import top.funning.library.Config.Code;
import top.funning.library.Config.V;
import top.funning.library.Utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/group/order/list")
public class List extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        M1025 services = ServletUtils.requestParamToModel(req, M1025.class);

        services.start();
        if (services.code == Code.Service.SUCCESS) {
            req.setAttribute(V.page, new Page(req));
            req.setAttribute(V.data, services.data);
            ServletUtils.setViewAndForward(req, resp);
        } else {
            resp.sendError(500, services.msg);
        }
    }

}
