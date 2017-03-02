package com.wechat.manage.pojo.wshopnav.entity;

import java.util.Date;

public class TNavRel {
    private Long sid;

    private Long shopId;

    private Long pageTypeSid;

    private Date createTime;

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getPageTypeSid() {
        return pageTypeSid;
    }

    public void setPageTypeSid(Long pageTypeSid) {
        this.pageTypeSid = pageTypeSid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}