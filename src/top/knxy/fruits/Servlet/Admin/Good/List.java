package top.knxy.fruits.Servlet.Admin.Good;

import top.knxy.library.Config.Code;
import top.knxy.library.Config.V;
import top.knxy.fruits.Service.Normal.Good.List.M1010;
import top.knxy.fruits.Servlet.Model.Page;
import top.knxy.library.Utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/good/list")
public class List extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        M1010 service = ServletUtils.requestParamToModel(req, M1010.class);
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
