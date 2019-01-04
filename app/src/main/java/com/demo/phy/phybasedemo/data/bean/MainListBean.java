package com.demo.phy.phybasedemo.data.bean;

/**
 * Created by phy on 2019/1/4.
 */

public class MainListBean {
    /**
     * item名
     */
    private String name;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * item代号 - 用于处理点击事件
     */
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
