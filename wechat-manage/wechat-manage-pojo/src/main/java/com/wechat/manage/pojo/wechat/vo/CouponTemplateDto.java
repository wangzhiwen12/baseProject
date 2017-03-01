package com.wechat.manage.pojo.wechat.vo;

import com.wechat.manage.pojo.wechat.entity.DataTableParams;

import java.util.Date;

public class CouponTemplateDto extends DataTableParams {
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

	private String filed3;

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

	public String getFiled3() {
		return filed3;
	}

	public void setFiled3(String filed3) {
		this.filed3 = filed3;
	}

	@Override
	public String toString() {
		return "CouponTemplateDto [sid=" + sid + ", couponType=" + couponType + ", couponValue="
				+ couponValue + ", couponPriceLimit=" + couponPriceLimit + ", createUserid="
				+ createUserid + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", ifdel=" + ifdel + ", storeCode=" + storeCode + ", couponName=" + couponName
				+ ", filed3=" + filed3 + "]";
	}

}
