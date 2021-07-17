package zing.node.entity;

import org.java_websocket.WebSocket;

import java.net.InetSocketAddress;

public abstract class Entity {

    private ZingAddress address;

    private WebSocket socket;

    public Entity(String address, WebSocket socket) {
        this.address = new ZingAddress(address);
        this.socket = socket;
    }

    public abstract boolean isNode();

    public InetSocketAddress getInetAddress() {
        return socket.getRemoteSocketAddress();
    }

    public ZingAddress getZingAddress() {
        return address;
    }
}
