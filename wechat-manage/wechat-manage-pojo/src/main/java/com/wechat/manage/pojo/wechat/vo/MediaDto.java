package com.wechat.manage.pojo.wechat.vo;

public class MediaDto {
	private String media_id;
	private String url;
	private String name;
	private String update_time;
	private ContentDto content = new ContentDto();
	private String errcode;
	private String errmsg;
	private String localUrl;

	public String getLocalUrl() {
		return localUrl;
	}

	public void setLocalUrl(String localUrl) {
		this.localUrl = localUrl;
	}

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public ContentDto getContent() {
		return content;
	}

	public void setContent(ContentDto content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "MediaDto [media_id=" + media_id + ", url=" + url + ", name=" + name
				+ ", update_time=" + update_time + ", content=" + content + ", errcode=" + errcode
				+ ", errmsg=" + errmsg + ", localUrl=" + localUrl + "]";
	}

}
