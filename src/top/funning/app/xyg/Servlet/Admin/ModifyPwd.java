package top.funning.app.xyg.Servlet.Admin;

import top.funning.app.xyg.Service.Login.ModifyPwd.M1019;
import top.knxy.library.Config.V;
import top.knxy.library.Utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/modifyPwd"})
public class ModifyPwd extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletUtils.setViewAndForward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        M1019 service = ServletUtils.requestParamToModel(request, M1019.class);
        service.adminId = request.getSession().getAttribute(V.adminId).toString();
        service.start();
        request.setAttribute(V.code, service.code);
        request.setAttribute(V.errorMsg, service.msg);
        ServletUtils.setViewAndForward(request, response);
    }
}
