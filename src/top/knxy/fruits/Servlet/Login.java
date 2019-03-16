package top.knxy.fruits.Servlet;

import top.knxy.fruits.Config.C;
import top.knxy.fruits.Config.V;
import top.knxy.fruits.DataBase.Table.Admin;
import top.knxy.fruits.Service.Login.Manager.M1004;
import top.knxy.fruits.Utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        M1004 services = ServletUtils.requestParamToModel(request, M1004.class);
        String username = services.username;
        String password = services.password;
        services.start();
        if (services.code == C.Service.success) {
            Admin admin = services.result;
            HttpSession session = request.getSession();
            session.setAttribute(V.adminId, admin.getId());
            session.setAttribute(V.adminName, admin.getUsername());
            response.sendRedirect("/admin/index");
        } else {
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute(V.errorMsg, services.msg);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    public static class RpData {
        public String msg;
    }
}
