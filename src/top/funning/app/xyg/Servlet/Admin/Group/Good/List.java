package top.funning.app.xyg.Servlet.Admin.Group.Good;

import top.funning.app.xyg.Service.Group.Good.List.M1030;
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

@WebServlet(urlPatterns = "/admin/group/good/list")
public class List extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        M1030 service = ServletUtils.requestParamToModel(req, M1030.class);
        service.start();
        if (service.code == Code.Service.SUCCESS) {
            req.setAttribute(V.data, service.data);
            req.setAttribute(V.page, new Page(req));
            ServletUtils.setViewAndForward(req, resp);
        } else {
            resp.sendError(500, service.msg);
        }
    }
}
