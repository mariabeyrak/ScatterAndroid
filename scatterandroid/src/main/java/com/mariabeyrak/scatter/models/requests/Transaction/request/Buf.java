package com.mariabeyrak.scatter.models.requests.Transaction.request;

public class Buf {
    private String type;
    private byte[] data;

    public Buf(String type, byte[] data) {
        super();
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Buf{" +
                "type='" + type + '\'' +
                ", data=" + data +
                '}';
    }
}