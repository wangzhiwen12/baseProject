package com.wechat.manage.pojo.category.entity;

import java.util.Date;

public class TCategoryPropsDict {
    private Long sid;

    private Long shopId;

    private Long propsSid;

    private String propsName;

    private Integer isKeyProp;

    private Integer isEnumProp;

    private String propsDesc;

    private String propsCode;

    private Long status;

    private Long sortOrder;

    private Long channelSid;

    private Integer isErpProp;

    private Integer erpType;

    private String erpPropCode;

    private Date createTime;

    private Long optUserSid;

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

    public Long getPropsSid() {
        return propsSid;
    }

    public void setPropsSid(Long propsSid) {
        this.propsSid = propsSid;
    }

    public String getPropsName() {
        return propsName;
    }

    public void setPropsName(String propsName) {
        this.propsName = propsName;
    }

    public Integer getIsKeyProp() {
        return isKeyProp;
    }

    public void setIsKeyProp(Integer isKeyProp) {
        this.isKeyProp = isKeyProp;
    }

    public Integer getIsEnumProp() {
        return isEnumProp;
    }

    public void setIsEnumProp(Integer isEnumProp) {
        this.isEnumProp = isEnumProp;
    }

    public String getPropsDesc() {
        return propsDesc;
    }

    public void setPropsDesc(String propsDesc) {
        this.propsDesc = propsDesc;
    }

    public String getPropsCode() {
        return propsCode;
    }

    public void setPropsCode(String propsCode) {
        this.propsCode = propsCode;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Long sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Long getChannelSid() {
        return channelSid;
    }

    public void setChannelSid(Long channelSid) {
        this.channelSid = channelSid;
    }

    public Integer getIsErpProp() {
        return isErpProp;
    }

    public void setIsErpProp(Integer isErpProp) {
        this.isErpProp = isErpProp;
    }

    public Integer getErpType() {
        return erpType;
    }

    public void setErpType(Integer erpType) {
        this.erpType = erpType;
    }

    public String getErpPropCode() {
        return erpPropCode;
    }

    public void setErpPropCode(String erpPropCode) {
        this.erpPropCode = erpPropCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getOptUserSid() {
        return optUserSid;
    }

    public void setOptUserSid(Long optUserSid) {
        this.optUserSid = optUserSid;
    }
}