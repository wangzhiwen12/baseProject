package com.wechat.manage.pojo.wechat.entity;

import java.util.Date;

public class WxPageHome {
    private Long sid;

    private String pageCode;

    private String title;

    private String cardno;

    private String template;

    private String img;

    private String wxheadhtml;

    private String wxhomehtml;

    private Date createTime;

    private String createUserid;

    private Date updateTime;

    private String updateUserid;

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getPageCode() {
        return pageCode;
    }

    public void setPageCode(String pageCode) {
        this.pageCode = pageCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getWxheadhtml() {
        return wxheadhtml;
    }

    public void setWxheadhtml(String wxheadhtml) {
        this.wxheadhtml = wxheadhtml;
    }

    public String getWxhomehtml() {
        return wxhomehtml;
    }

    public void setWxhomehtml(String wxhomehtml) {
        this.wxhomehtml = wxhomehtml;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserid() {
        return createUserid;
    }

    public void setCreateUserid(String createUserid) {
        this.createUserid = createUserid;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUserid() {
        return updateUserid;
    }

    public void setUpdateUserid(String updateUserid) {
        this.updateUserid = updateUserid;
    }
}