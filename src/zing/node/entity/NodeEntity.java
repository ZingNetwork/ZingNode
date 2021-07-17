package zing.node.entity;

import org.java_websocket.WebSocket;

public class NodeEntity extends Entity {

    public NodeEntity(String address, WebSocket socket) {
        super(address, socket);
    }

    @Override
    public boolean isNode() {
        return true;
    }
}
