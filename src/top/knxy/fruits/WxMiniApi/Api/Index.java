package top.knxy.fruits.WxMiniApi.Api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import top.knxy.fruits.WxMiniApi.Service.IndexService.C1001;
import top.knxy.fruits.WxMiniApi.Utils.StrUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api")
public class Index extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        ApiUtils.ResponseError(pw, "method should be POST");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String content = ApiUtils.getString(request);
        PrintWriter pw = response.getWriter();

        Gson gson = new Gson();

        if (StrUtils.isEmpty(content)) {
            ApiUtils.ResponseError(pw, "input json is empty");
            return;
        }

        Request rq = gson.fromJson(content, Request.class);
        String cmd = rq.cmd;
        JsonObject data = rq.data == null ? new JsonObject() : rq.data;

        if ("C1001".equals(cmd)) {
            //get wx open id
            C1001 c1001 = new Gson().fromJson(data, C1001.class);
            c1001.start();
            ApiUtils.Response(pw, c1001);
        } else {
            ApiUtils.ResponseError(pw, "unknown cmd");
        }
    }
}

