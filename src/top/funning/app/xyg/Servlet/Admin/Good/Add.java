package top.funning.app.xyg.Servlet.Admin.Good;

import top.funning.app.xyg.Service.Normal.GoodType.get.M1016;
import top.funning.library.Config.Code;
import top.funning.library.Config.V;
import top.funning.library.Utils.ServletUtils;

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
        if (m1016.code == Code.Service.SUCCESS) {
            req.setAttribute(V.data, m1016.data);
            ServletUtils.setViewAndForward(req, resp);
        } else {
            resp.sendError(500, m1016.msg);
        }

    }
}
