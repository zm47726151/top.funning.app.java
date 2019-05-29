package top.funning.app.xyg.Servlet.Admin.GoodType;

import top.funning.library.Config.Code;
import top.funning.library.Config.V;
import top.funning.app.xyg.Service.Normal.GoodType.List.M1007;
import top.funning.library.Utils.ServletUtils;

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
        if (service.code == Code.Service.SUCCESS) {
            req.setAttribute(V.data, service.data);
            ServletUtils.setViewAndForward(req, resp);
        } else {
            resp.sendError(500, service.msg);
        }
    }
}
