package node.zing.entity;

public class ZingAddress {

    private static final String HEAD = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBA";

    private final String PUBLIC_KEY;

    private final String ADDRESS;

    public ZingAddress(String address) {
        this.ADDRESS = address;
        PUBLIC_KEY = address.replace(HEAD, "ZN");
    }

    public String getPublicKey() {
        return PUBLIC_KEY;
    }

    @Override
    public String toString() {
        return ADDRESS;
    }
}
