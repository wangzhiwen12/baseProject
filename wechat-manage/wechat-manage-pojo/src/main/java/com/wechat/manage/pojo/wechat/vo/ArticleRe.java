package com.wechat.manage.pojo.wechat.vo;

public class ArticleRe {
	private String media_id;
	private String errcode;
	private String errmsg;

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
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

	@Override
	public String toString() {
		return "ArticleRe [media_id=" + media_id + ", errcode=" + errcode + ", errmsg=" + errmsg
				+ "]";
	}

}
