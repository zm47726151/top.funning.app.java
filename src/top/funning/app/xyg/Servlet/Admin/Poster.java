package top.funning.app.xyg.Servlet.Admin;

import top.funning.app.xyg.DataBase.Redis;
import top.knxy.library.Config.V;
import top.knxy.library.Utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/poster"})
public class Poster extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String postImageUrl = Redis.get(V.postImageUrl);
        request.setAttribute(V.postImageUrl, postImageUrl);
        ServletUtils.setViewAndForward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
