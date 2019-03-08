package top.knxy.fruits.Servlet.Admin.Good;

import top.knxy.fruits.Config.C;
import top.knxy.fruits.Config.V;
import top.knxy.fruits.Service.GoodType.get.M1016;
import top.knxy.fruits.Utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/good/add")
public class Add extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        M1016 m1016 = ServletUtils.requestParamToModel(req, M1016.class);
        m1016.start();
        if (m1016.code == C.Service.success) {
            req.setAttribute(V.data, m1016.data);
            ServletUtils.setViewAndForward(req, resp);
        } else {
            resp.sendError(500, m1016.msg);
        }

    }
}
