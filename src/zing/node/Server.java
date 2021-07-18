package zing.node;

import org.java_websocket.WebSocket;
import zing.node.entity.ClientEntity;
import zing.node.entity.Entity;
import zing.node.entity.ZingAddress;
import zing.node.network.SocketServer;
import zing.node.util.Language;
import org.apache.log4j.Logger;
import zing.protocol.Packet;
import zing.protocol.Protocol;
import zing.protocol.algorithm.RSA;

import java.util.HashMap;

public class Server {

    private static Server instance = null;

    private final Logger logger = Logger.getLogger(Server.class);

    private final int port;

    private SocketServer socketServer;

    private final HashMap<String, Entity> users = new HashMap<>();

    Server() {
        instance = this;
        port = Integer.parseInt(Config.get("port"));
        Thread thread = new Thread(() -> {
            socketServer = new SocketServer(port);
            socketServer.start();
            logger.info(Language.get("running") + port);
        });
        thread.setName("socket");
        thread.start();
    }

    public static Server getInstance() {
        return instance;
    }

    public Logger getLogger() {
        return logger;
    }

    public int getPort() {
        return port;
    }

    public void handlePacket(Protocol p, WebSocket socket) {
        if (!verify(p)) {
            logger.info(p.getSimpleAddress() + Language.get("verify.fail"));
            socket.close();
            return;
        }
        Packet packet = p.packet;
        switch (p.pid()) {
            case 1:
                ClientEntity clientEntity = new ClientEntity(p.zingAddress, socket, packet.nickname, packet.sub_info);
                users.put(p.zingAddress, clientEntity);
                logger.info(p.getSimpleAddress() + Language.get("login"));
                break;
        }
    }

    public boolean verify(Protocol protocol) {
        try {
            return RSA.verify(Protocol.format(protocol), protocol.sign
                    , RSA.getPublicKey(protocol.zingAddress.replaceFirst("ZN", ZingAddress.HEAD)));
        } catch (Exception e) {
            logger.info(protocol.getSimpleAddress() + Language.get("address.error"));
            return false;
        }
    }
}
