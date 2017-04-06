package com.wechat.manage.pojo.category.entity;

import java.util.Date;

public class TProGroup {
    private Long id;

    private Integer copid;

    private String storeCode;

    private Long code;

    private String name;

    private Long parentId;

    private Integer type;

    private Integer level;

    private Integer displayIndex;

    private Integer state;

    private String picUrl;

    private Integer publishTimeType;

    private Integer publishTimeUnit;

    private Integer publishTimeValue;

    private Date publishTimeStart;

    private Date publishTimeEnd;

    private String selectedpfcategories;

    private String selectedpfattrs;

    private String createUser;

    private Date createDate;

    private String updateUser;

    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCopid() {
        return copid;
    }

    public void setCopid(Integer copid) {
        this.copid = copid;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getDisplayIndex() {
        return displayIndex;
    }

    public void setDisplayIndex(Integer displayIndex) {
        this.displayIndex = displayIndex;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Integer getPublishTimeType() {
        return publishTimeType;
    }

    public void setPublishTimeType(Integer publishTimeType) {
        this.publishTimeType = publishTimeType;
    }

    public Integer getPublishTimeUnit() {
        return publishTimeUnit;
    }

    public void setPublishTimeUnit(Integer publishTimeUnit) {
        this.publishTimeUnit = publishTimeUnit;
    }

    public Integer getPublishTimeValue() {
        return publishTimeValue;
    }

    public void setPublishTimeValue(Integer publishTimeValue) {
        this.publishTimeValue = publishTimeValue;
    }

    public Date getPublishTimeStart() {
        return publishTimeStart;
    }

    public void setPublishTimeStart(Date publishTimeStart) {
        this.publishTimeStart = publishTimeStart;
    }

    public Date getPublishTimeEnd() {
        return publishTimeEnd;
    }

    public void setPublishTimeEnd(Date publishTimeEnd) {
        this.publishTimeEnd = publishTimeEnd;
    }

    public String getSelectedpfcategories() {
        return selectedpfcategories;
    }

    public void setSelectedpfcategories(String selectedpfcategories) {
        this.selectedpfcategories = selectedpfcategories;
    }

    public String getSelectedpfattrs() {
        return selectedpfattrs;
    }

    public void setSelectedpfattrs(String selectedpfattrs) {
        this.selectedpfattrs = selectedpfattrs;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}