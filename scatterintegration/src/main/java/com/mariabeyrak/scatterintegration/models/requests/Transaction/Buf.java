package com.mariabeyrak.scatterintegration.models.requests.Transaction;

import java.util.List;

public class Buf {
    private String type;
    private List<Integer> data = null;

    public Buf(String type, List<Integer> data) {
        super();
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

}