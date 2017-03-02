package com.wechat.manage.pojo.wshopnav.vo;

import java.util.ArrayList;
import java.util.List;

public class TNavDto {
	private String sid;
	private String title;
	private String linkUrl;
	private String shopSid;
	List<TNavDtoList> second = new ArrayList<TNavDtoList>();

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getShopSid() {
		return shopSid;
	}

	public void setShopSid(String shopSid) {
		this.shopSid = shopSid;
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

	public List<TNavDtoList> getSecond() {
		return second;
	}

	public void setSecond(List<TNavDtoList> second) {
		this.second = second;
	}

	@Override
	public String toString() {
		return "TNavDto [title=" + title + ", linkUrl=" + linkUrl + ", shopSid=" + shopSid
				+ ", second=" + second + "]";
	}

}
