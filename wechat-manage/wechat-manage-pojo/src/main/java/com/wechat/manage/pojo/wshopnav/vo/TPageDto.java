package com.wechat.manage.pojo.wshopnav.vo;

public class TPageDto {
	public String notice;
	public String wpageTitle;
	public String title;
	public String proCodes;

	public String toString() {
		return "{'notice':'" + this.notice + "','wpageTitle':'" + this.wpageTitle + "','title':'" + this.title
				+ "','proCodes':'" + this.proCodes + "'}";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProCodes() {
		return proCodes;
	}

	public void setProCodes(String proCodes) {
		this.proCodes = proCodes;
	}

	public String getWpageTitle() {
		return wpageTitle;
	}

	public void setWpageTitle(String wpageTitle) {
		this.wpageTitle = wpageTitle;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

}
