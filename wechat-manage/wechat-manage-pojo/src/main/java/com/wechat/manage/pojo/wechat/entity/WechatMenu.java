package com.wechat.manage.pojo.wechat.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WechatMenu {
    private Long sid;

    private String appid;

    private String parentSid;

    private String name;

    private String type;

    private String clickkey;

    private String menuContent;

    private Integer clickType;

    private String viewurl;

    private Integer orderBy;

    private Integer ifdel;

    private Date createTime;

    public List<WechatMenu> getChildren() {
        return children;
    }

    public void setChildren(List<WechatMenu> children) {
        this.children = children;
    }

    private Date updateTime;
    List<WechatMenu> children = new ArrayList<WechatMenu>();

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getParentSid() {
        return parentSid;
    }

    public void setParentSid(String parentSid) {
        this.parentSid = parentSid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClickkey() {
        return clickkey;
    }

    public void setClickkey(String clickkey) {
        this.clickkey = clickkey;
    }

    public String getViewurl() {
        return viewurl;
    }

    public void setViewurl(String viewurl) {
        this.viewurl = viewurl;
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getIfdel() {
        return ifdel;
    }

    public void setIfdel(Integer ifdel) {
        this.ifdel = ifdel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getMenuContent() {
        return menuContent;
    }

    public void setMenuContent(String menuContent) {
        this.menuContent = menuContent;
    }

    public Integer getClickType() {
        return clickType;
    }

    public void setClickType(Integer clickType) {
        this.clickType = clickType;
    }

    @Override
    public String toString() {
        return "WechatMenu{" +
                "sid=" + sid +
                ", appid='" + appid + '\'' +
                ", parentSid='" + parentSid + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", clickkey='" + clickkey + '\'' +
                ", menuContent='" + menuContent + '\'' +
                ", clickType=" + clickType +
                ", viewurl='" + viewurl + '\'' +
                ", orderBy=" + orderBy +
                ", ifdel=" + ifdel +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", children=" + children +
                '}';
    }
}