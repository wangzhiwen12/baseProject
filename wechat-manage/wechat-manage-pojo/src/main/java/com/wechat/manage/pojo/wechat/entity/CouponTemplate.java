package com.wechat.manage.pojo.wechat.entity;

import java.util.Date;

public class CouponTemplate {
	private Integer sid;

	private String couponType;

	private String couponValue;

	private String couponPriceLimit;

	private String createUserid;

	private Date createTime;

	private Date updateTime;

	private Integer ifdel;

	private String storeCode;

	private String couponName;

	private String createUserName;

	private String noLength;
	private String prefixStr;
	private String startNo;
	private String suffixLength;
	private String background;

	public String getNoLength() {
		return noLength;
	}

	public void setNoLength(String noLength) {
		this.noLength = noLength;
	}

	public String getPrefixStr() {
		return prefixStr;
	}

	public void setPrefixStr(String prefixStr) {
		this.prefixStr = prefixStr;
	}

	public String getStartNo() {
		return startNo;
	}

	public void setStartNo(String startNo) {
		this.startNo = startNo;
	}

	public String getSuffixLength() {
		return suffixLength;
	}

	public void setSuffixLength(String suffixLength) {
		this.suffixLength = suffixLength;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}

	public String getCouponValue() {
		return couponValue;
	}

	public void setCouponValue(String couponValue) {
		this.couponValue = couponValue;
	}

	public String getCouponPriceLimit() {
		return couponPriceLimit;
	}

	public void setCouponPriceLimit(String couponPriceLimit) {
		this.couponPriceLimit = couponPriceLimit;
	}

	public String getCreateUserid() {
		return createUserid;
	}

	public void setCreateUserid(String createUserid) {
		this.createUserid = createUserid;
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

	public Integer getIfdel() {
		return ifdel;
	}

	public void setIfdel(Integer ifdel) {
		this.ifdel = ifdel;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	@Override
	public String toString() {
		return "CouponTemplate [sid=" + sid + ", couponType=" + couponType + ", couponValue="
				+ couponValue + ", couponPriceLimit=" + couponPriceLimit + ", createUserid="
				+ createUserid + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", ifdel=" + ifdel + ", storeCode=" + storeCode + ", couponName=" + couponName
				+ ", createUserName=" + createUserName + ", noLength=" + noLength + ", prefixStr="
				+ prefixStr + ", startNo=" + startNo + ", suffixLength=" + suffixLength
				+ ", background=" + background + "]";
	}

}