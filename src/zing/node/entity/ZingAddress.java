package zing.node.entity;

import zing.protocol.algorithm.RSA;

import java.security.PublicKey;

public class ZingAddress {

    public static final String HEAD = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBA";

    private final PublicKey PUBLIC_KEY;

    private final String ADDRESS;

    public ZingAddress(String address) {
        this.ADDRESS = address;
        PUBLIC_KEY = RSA.getPublicKey(address.replaceFirst("ZN", HEAD));
    }

    public PublicKey getPublicKey() {
        return PUBLIC_KEY;
    }

    @Override
    public String toString() {
        return ADDRESS;
    }
}
