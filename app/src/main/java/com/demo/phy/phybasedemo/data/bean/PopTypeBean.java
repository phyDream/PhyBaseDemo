package com.demo.phy.phybasedemo.data.bean;

public class PopTypeBean {

    public PopTypeBean(String name, int id) {
        this.name = name;
        this.id = id;
    }

    String name;
    int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
