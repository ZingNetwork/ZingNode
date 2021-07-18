package zing.node.entity;

import org.java_websocket.WebSocket;

public class ClientEntity extends Entity {

    private String nickname;

    private String sub_info;

    public ClientEntity(String address, WebSocket socket, String nickname, String sub_info) {
        super(address, socket);
        this.nickname = nickname;
        this.sub_info = sub_info;
    }

    @Override
    public boolean isNode() {
        return false;
    }
}
