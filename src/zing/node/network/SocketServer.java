package zing.node.network;

import com.google.gson.Gson;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import zing.node.Server;
import zing.node.util.Language;
import zing.protocol.Protocol;
import zing.protocol.algorithm.RSA;

import java.net.InetSocketAddress;

public class SocketServer extends WebSocketServer {

    private SocketServer instance;

    private Server server = Server.getInstance();

    public SocketServer(int port) {
        super(new InetSocketAddress(port));
        instance = this;
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        server.getLogger().info(Language.get("connected") + webSocket.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {

    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        long time = System.currentTimeMillis();
//        System.out.println(s);
        Protocol packet = new Gson().fromJson(s, Protocol.class);
        if (time - packet.time_stamp < 600000) {
            if (packet.pid() > 0) {
                Server.getInstance().handlePacket(packet, webSocket);
            } else {
                //TODO pid无效
            }
        } else {
            System.out.println("时间错误");
        }
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {

    }

    @Override
    public void onStart() {

    }
}
