package com.wechat.manage.pojo.wechat.vo;

import java.util.Date;

public class UserCouponInfoDto {
	private String couponCode;// 消费码
	private String couponQRCode;// 二维码
	private String title;// 名称
	private String subTitle;// 副标题
	private Date endTimestamp;// 截止时间
	private String type;// 时间类型
	private Date collectionTime;// 领取时间
	private Integer fixedTerm;// 有效期天数
	private String imageName;

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Integer getFixedTerm() {
		return fixedTerm;
	}

	public void setFixedTerm(Integer fixedTerm) {
		this.fixedTerm = fixedTerm;
	}

	public Date getCollectionTime() {
		return collectionTime;
	}

	public void setCollectionTime(Date collectionTime) {
		this.collectionTime = collectionTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Date getEndTimestamp() {
		return endTimestamp;
	}

	public void setEndTimestamp(Date endTimestamp) {
		this.endTimestamp = endTimestamp;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getCouponQRCode() {
		return couponQRCode;
	}

	public void setCouponQRCode(String couponQRCode) {
		this.couponQRCode = couponQRCode;
	}

	@Override
	public String toString() {
		return "UserCouponInfoDto [couponCode=" + couponCode + ", couponQRCode=" + couponQRCode
				+ ", title=" + title + ", subTitle=" + subTitle + ", endTimestamp=" + endTimestamp
				+ ", type=" + type + ", collectionTime=" + collectionTime + ", fixedTerm="
				+ fixedTerm + "]";
	}

}
