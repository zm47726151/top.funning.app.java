package top.knxy.fruits.Servlet.Admin;

import top.knxy.fruits.Config.V;
import top.knxy.fruits.DataBase.Cache.AppMap;
import top.knxy.fruits.Utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/poster"})
public class Poster extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String postImageUrl = AppMap.get(V.postImageUrl);
        request.setAttribute(V.postImageUrl, postImageUrl);
        ServletUtils.setViewAndForward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
