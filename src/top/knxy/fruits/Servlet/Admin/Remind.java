package top.knxy.fruits.Servlet.Admin;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/admin/remind")
public class Remind {
    private static final Set<Remind> connections = new CopyOnWriteArraySet<>();
    private Session session;

    @OnOpen
    public void start(Session session) {
        this.session = session;
        connections.add(this);
    }

    @OnClose
    public void end() {
        connections.remove(this);
    }

    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    public static void broadcast() {
        for (Remind client : connections) {
            try {
                synchronized (client) {
                    client.session.getBasicRemote().sendText("{'code':'1','msg':'new order'}");
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
}