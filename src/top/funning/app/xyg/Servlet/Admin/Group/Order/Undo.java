package top.funning.app.xyg.Servlet.Admin.Group.Order;


import top.funning.app.xyg.Service.Group.Order.Undo.M1035;
import top.funning.app.xyg.Servlet.Model.Page;
import top.knxy.library.Config.Code;
import top.knxy.library.Config.V;
import top.knxy.library.Utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/group/order/undo")
public class Undo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        M1035 services = ServletUtils.requestParamToModel(req, M1035.class);

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
