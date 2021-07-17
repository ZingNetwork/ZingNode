package node.zing;

import node.zing.network.SocketServer;
import node.zing.util.Language;
import org.apache.log4j.Logger;

public class Server {

    private static Server instance = null;

    private final Logger logger = Logger.getLogger(Server.class);

    private final int port;

    private SocketServer socketServer;

    Server() {
        instance = this;
        port = Integer.parseInt(Config.get("port"));
        Thread thread = new Thread(() -> {
            socketServer = new SocketServer(port);
            socketServer.start();
            logger.info(Language.get("running") + port);
        });
        thread.setName("SocketServer");
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
}
