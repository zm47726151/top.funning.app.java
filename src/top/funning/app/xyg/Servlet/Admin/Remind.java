package top.funning.app.xyg.Servlet.Admin;

import com.google.gson.Gson;
import top.funning.app.xyg.Service.Index.GetNumber.M1017;
import top.funning.library.Config.Code;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/admin/remind")
public class Remind {
    private static final Set<Remind> connections = new CopyOnWriteArraySet<>();
    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        connections.add(this);
        String data = getData();
        if (data != null) {
            sendMessage(this, data);
        }
    }

    @OnClose
    public void onClose() {
        connections.remove(this);
    }

    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    public static void broadcast() {
        String data = getData();
        if (data != null) {
            for (Remind client : connections) {
                sendMessage(client, data);
            }
        }
    }

    public static String getData() {
        M1017 service = new M1017();
        service.start();
        Gson gson = new Gson();
        if (service.code == Code.Service.SUCCESS) {
            return gson.toJson(service.data);
        }
        return null;
    }

    public static void sendMessage(Remind client, String data) {
        try {
            synchronized (client) {
                client.session.getBasicRemote().sendText(data);
            }
        } catch (IOException e) {
            connections.remove(client);
            try {
                client.session.close();
            } catch (IOException e1) {
            }
        }
    }
}