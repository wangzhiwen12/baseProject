package com.wechat.manage.pojo.wechat.vo;

public class MaterialReSubDto {
	private String content;// 正文内容

	private String picUrl;// 图文页的URL

	private String localUrl;// 本地图片地址

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getLocalUrl() {
		return localUrl;
	}

	public void setLocalUrl(String localUrl) {
		this.localUrl = localUrl;
	}

	@Override
	public String toString() {
		return "MaterialReSubDto [content=" + content + ", picUrl=" + picUrl + ", localUrl="
				+ localUrl + "]";
	}

}
