package top.knxy.fruits.Servlet.Admin;

import top.knxy.fruits.Config.C;
import top.knxy.fruits.Config.V;
import top.knxy.fruits.Service.Login.ModifyPwd.M1016;
import top.knxy.fruits.Utils.ServiceUtils;
import top.knxy.fruits.Utils.ServletUtils;

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
        M1016 m1016 = ServletUtils.requestParamToModel(request, M1016.class);
        m1016.adminId = request.getSession().getAttribute(V.adminId).toString();
        m1016.start();
        request.setAttribute(V.code, m1016.code);
        request.setAttribute(V.errorMsg, m1016.msg);
        ServletUtils.setViewAndForward(request, response);
    }
}
