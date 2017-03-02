package com.wechat.manage.pojo.wechat.vo;

import com.wechat.manage.pojo.wechat.entity.DataTableParams;

import java.util.Date;

public class CouponInfoDto extends DataTableParams {
	private Long sid;

	private String cardSid;

	private String useState;

	private String storeCode;

	private String cardType;

	private String logoUrl;

	private String codeType;

	private String brandName;

	private String title;

	private String subTitle;

	private String color;

	private String notice;

	private Integer quantity;

	private String type;

	private Date beginTimestamp;

	private Date endTimestamp;

	private Integer getLimit;

	private Integer fixedTerm;

	private Integer fixedBeginTerm;

	private String couponStatus;

	private String approvalUserid;

	private String approvalUsername;

	private Date approvalTime;

	private Date createTime;

	private String createUserid;

	private Date updateTime;

	private String updateUserid;

	private String appSystem;

	private String description;

	private int tplSid;

	private String operationType;

	private String couponValue;

	private String couponPriceLimit;

	private String imageName;

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getCouponPriceLimit() {
		return couponPriceLimit;
	}

	public void setCouponPriceLimit(String couponPriceLimit) {
		this.couponPriceLimit = couponPriceLimit;
	}

	public String getCouponValue() {
		return couponValue;
	}

	public void setCouponValue(String couponValue) {
		this.couponValue = couponValue;
	}

	public String getCardSid() {
		return cardSid;
	}

	public void setCardSid(String cardSid) {
		this.cardSid = cardSid;
	}

	public String getUseState() {
		return useState;
	}

	public void setUseState(String useState) {
		this.useState = useState;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getBeginTimestamp() {
		return beginTimestamp;
	}

	public void setBeginTimestamp(Date beginTimestamp) {
		this.beginTimestamp = beginTimestamp;
	}

	public Date getEndTimestamp() {
		return endTimestamp;
	}

	public void setEndTimestamp(Date endTimestamp) {
		this.endTimestamp = endTimestamp;
	}

	public Integer getGetLimit() {
		return getLimit;
	}

	public void setGetLimit(Integer getLimit) {
		this.getLimit = getLimit;
	}

	public Integer getFixedTerm() {
		return fixedTerm;
	}

	public void setFixedTerm(Integer fixedTerm) {
		this.fixedTerm = fixedTerm;
	}

	public Integer getFixedBeginTerm() {
		return fixedBeginTerm;
	}

	public void setFixedBeginTerm(Integer fixedBeginTerm) {
		this.fixedBeginTerm = fixedBeginTerm;
	}

	public String getCouponStatus() {
		return couponStatus;
	}

	public void setCouponStatus(String couponStatus) {
		this.couponStatus = couponStatus;
	}

	public String getApprovalUserid() {
		return approvalUserid;
	}

	public void setApprovalUserid(String approvalUserid) {
		this.approvalUserid = approvalUserid;
	}

	public String getApprovalUsername() {
		return approvalUsername;
	}

	public void setApprovalUsername(String approvalUsername) {
		this.approvalUsername = approvalUsername;
	}

	public Date getApprovalTime() {
		return approvalTime;
	}

	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
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

	public String getAppSystem() {
		return appSystem;
	}

	public void setAppSystem(String appSystem) {
		this.appSystem = appSystem;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTplSid() {
		return tplSid;
	}

	public void setTplSid(int tplSid) {
		this.tplSid = tplSid;
	}

	@Override
	public String toString() {
		return "CouponInfoDto [sid=" + sid + ", cardSid=" + cardSid + ", useState=" + useState
				+ ", storeCode=" + storeCode + ", cardType=" + cardType + ", logoUrl=" + logoUrl
				+ ", codeType=" + codeType + ", brandName=" + brandName + ", title=" + title
				+ ", subTitle=" + subTitle + ", color=" + color + ", notice=" + notice
				+ ", quantity=" + quantity + ", type=" + type + ", beginTimestamp=" + beginTimestamp
				+ ", endTimestamp=" + endTimestamp + ", getLimit=" + getLimit + ", fixedTerm="
				+ fixedTerm + ", fixedBeginTerm=" + fixedBeginTerm + ", couponStatus="
				+ couponStatus + ", approvalUserid=" + approvalUserid + ", approvalUsername="
				+ approvalUsername + ", approvalTime=" + approvalTime + ", createTime=" + createTime
				+ ", createUserid=" + createUserid + ", updateTime=" + updateTime
				+ ", updateUserid=" + updateUserid + ", appSystem=" + appSystem + ", description="
				+ description + ", tplSid=" + tplSid + ", operationType=" + operationType
				+ ", couponValue=" + couponValue + ", couponPriceLimit=" + couponPriceLimit + "]";
	}

}
