package com.wechat.manage.pojo.wshopnav.vo;

public class TNavDtoList {
	private String sid;
	private String title;
	private String linkUrl;
	private String shopSid;
	private String parentSid;

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getShopSid() {
		return shopSid;
	}

	public void setShopSid(String shopSid) {
		this.shopSid = shopSid;
	}

	public String getParentSid() {
		return parentSid;
	}

	public void setParentSid(String parentSid) {
		this.parentSid = parentSid;
	}

	@Override
	public String toString() {
		return "TNavDtoList [sid=" + sid + ", title=" + title + ", linkUrl=" + linkUrl
				+ ", shopSid=" + shopSid + ", parentSid=" + parentSid + "]";
	}
}
