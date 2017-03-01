package com.wechat.manage.pojo.wechat.vo;

import java.util.Date;

public class CouponDto {
	private String infoSid;
	private String memSid;
	private String title;
	private String storeName;
	private String district;
	private String useState;
	private String couponValue;
	private String couponPriceLimit;
	private String imageName;
	private String currentNum;
	private Date endTime;

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getCurrentNum() {
		return currentNum;
	}

	public void setCurrentNum(String currentNum) {
		this.currentNum = currentNum;
	}

	public String getInfoSid() {
		return infoSid;
	}

	public void setInfoSid(String infoSid) {
		this.infoSid = infoSid;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getMemSid() {
		return memSid;
	}

	public void setMemSid(String memSid) {
		this.memSid = memSid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getUseState() {
		return useState;
	}

	public void setUseState(String useState) {
		this.useState = useState;
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

	@Override
	public String toString() {
		return "CouponDto [infoSid=" + infoSid + ", memSid=" + memSid + ", title=" + title
				+ ", storeName=" + storeName + ", district=" + district + ", useState=" + useState
				+ ", couponValue=" + couponValue + ", couponPriceLimit=" + couponPriceLimit
				+ ", imageName=" + imageName + ", currentNum=" + currentNum + "]";
	}

}
