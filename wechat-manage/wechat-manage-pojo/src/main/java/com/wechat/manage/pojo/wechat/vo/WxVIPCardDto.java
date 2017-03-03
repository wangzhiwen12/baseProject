package com.wechat.manage.pojo.wechat.vo;

import java.io.Serializable;

public class WxVIPCardDto implements Serializable {
	private static final long serialVersionUID = -8011757621095881799L;
	private String headName;
	private String headPictureUrl;
	private String wxHeadPictureUrl;
	private String brandCodeStatus;
	private String onlineCodeStatus;
	private String storeCode;

	public String getHeadName() {
		return headName;
	}

	public void setHeadName(String headName) {
		this.headName = headName;
	}

	public String getHeadPictureUrl() {
		return headPictureUrl;
	}

	public void setHeadPictureUrl(String headPictureUrl) {
		this.headPictureUrl = headPictureUrl;
	}

	public String getWxHeadPictureUrl() {
		return wxHeadPictureUrl;
	}

	public void setWxHeadPictureUrl(String wxHeadPictureUrl) {
		this.wxHeadPictureUrl = wxHeadPictureUrl;
	}

	public String getBrandCodeStatus() {
		return brandCodeStatus;
	}

	public void setBrandCodeStatus(String brandCodeStatus) {
		this.brandCodeStatus = brandCodeStatus;
	}

	public String getOnlineCodeStatus() {
		return onlineCodeStatus;
	}

	public void setOnlineCodeStatus(String onlineCodeStatus) {
		this.onlineCodeStatus = onlineCodeStatus;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	@Override
	public String toString() {
		return "WxVIPCardDto [headName=" + headName + ", headPictureUrl=" + headPictureUrl + ", wxHeadPictureUrl="
				+ wxHeadPictureUrl + ", brandCodeStatus=" + brandCodeStatus + ", onlineCodeStatus=" + onlineCodeStatus
				+ ", storeCode=" + storeCode + "]";
	}

}
