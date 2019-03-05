package top.knxy.fruits.Servlet.Admin.GoodType;

import top.knxy.fruits.Config.C;
import top.knxy.fruits.Config.V;
import top.knxy.fruits.Service.GoodType.List.M1007;
import top.knxy.fruits.Utils.ServiceUtils;
import top.knxy.fruits.Utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/goodtype/list")
public class List extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        M1007 service = ServletUtils.requestParamToModel(req, M1007.class);
        service.start();
        if (service.code == C.Service.success) {
            req.setAttribute(V.data, service.data);
            ServletUtils.setViewAndForward(req, resp);
        } else {
            resp.sendError(500, service.msg);
        }
    }
}
