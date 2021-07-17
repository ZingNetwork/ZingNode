package node.zing.entity;

import org.java_websocket.WebSocket;

public class ClientEntity extends Entity {

    public ClientEntity(String address, WebSocket socket) {
        super(address, socket);
    }

    @Override
    public boolean isNode() {
        return false;
    }
}
