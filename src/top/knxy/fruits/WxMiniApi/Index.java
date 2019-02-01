package top.knxy.fruits.WxMiniApi;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
        RpUtils.printError(pw, "method should be POST");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String content = RpUtils.getString(request);
        PrintWriter pw = response.getWriter();

        Gson gson = new Gson();

        if (StrUtils.isEmpty(content)) {
            RpUtils.printError(pw, "input json is empty");
            return;
        }

        Request rq = gson.fromJson(content, Request.class);
        String cmd = rq.cmd;
        JsonObject data = rq.data;

        if ("C1001".equals(cmd)) {
            // get wx open id
            /*C0001 c0001 = new Gson().fromJson(data, C0001.class);
            c0001.action();
            RpUtils.print(pw, c0001);*/
        } else {
            RpUtils.printError(pw, "unknown cmd");
        }
    }
}


class Request {
    public String cmd;
    public JsonObject data;
}

class Response {
    public String code;
    public String msg;
    public Object data;
}