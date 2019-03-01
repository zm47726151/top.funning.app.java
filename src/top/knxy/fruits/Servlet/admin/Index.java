package top.knxy.fruits.Servlet.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "admin_index", urlPatterns = {"/admin/index", "/admin/order"})
public class Index extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        int index = requestURI.lastIndexOf("/");
        String page = requestURI.substring(index);
        request.setAttribute("jspPage", page + ".jsp");
        request.setAttribute("page", page);
        request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
    }
}
