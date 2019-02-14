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
    private int tag;

    /**
     * item描述
     */
    private int description;

    public MainListBean(String name, String createTime, int tag, String description) {
        this.name = name;
        this.createTime = createTime;
        this.tag = tag;
    }

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

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }
}
