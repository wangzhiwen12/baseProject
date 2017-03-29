package com.wechat.manage.pojo.category.entity;

import java.util.Date;

public class TCategory {
    private Long sid;

    private String categorySid;

    private String parentSid;

    private String name;

    private Integer isParent;

    private Integer isSelfBuilt;

    private String isLeaf;

    private Integer isDisplay;

    private String status;

    private Long rootSid;

    private Integer level;

    private Integer sortOrder;

    private Long channelSid;

    private Date createTime;

    private String searchPath;

    private Integer categoryType;

    private String shopSid;

    private String description;

    private Integer erpType;

    private String isMarket;

    private Date successTime;

    private Long groupSid;

    private String categoryCode;

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getCategorySid() {
        return categorySid;
    }

    public void setCategorySid(String categorySid) {
        this.categorySid = categorySid;
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

    public Integer getIsParent() {
        return isParent;
    }

    public void setIsParent(Integer isParent) {
        this.isParent = isParent;
    }

    public Integer getIsSelfBuilt() {
        return isSelfBuilt;
    }

    public void setIsSelfBuilt(Integer isSelfBuilt) {
        this.isSelfBuilt = isSelfBuilt;
    }

    public String getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
    }

    public Integer getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Integer isDisplay) {
        this.isDisplay = isDisplay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getRootSid() {
        return rootSid;
    }

    public void setRootSid(Long rootSid) {
        this.rootSid = rootSid;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Long getChannelSid() {
        return channelSid;
    }

    public void setChannelSid(Long channelSid) {
        this.channelSid = channelSid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSearchPath() {
        return searchPath;
    }

    public void setSearchPath(String searchPath) {
        this.searchPath = searchPath;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public String getShopSid() {
        return shopSid;
    }

    public void setShopSid(String shopSid) {
        this.shopSid = shopSid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getErpType() {
        return erpType;
    }

    public void setErpType(Integer erpType) {
        this.erpType = erpType;
    }

    public String getIsMarket() {
        return isMarket;
    }

    public void setIsMarket(String isMarket) {
        this.isMarket = isMarket;
    }

    public Date getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(Date successTime) {
        this.successTime = successTime;
    }

    public Long getGroupSid() {
        return groupSid;
    }

    public void setGroupSid(Long groupSid) {
        this.groupSid = groupSid;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
}