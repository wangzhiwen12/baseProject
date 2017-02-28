package com.wechat.manage.pojo.wechat.entity;

public class MsgReply {
	private Integer sid;

	private String eventType;

	private String ruleName;

	private String msgKey;

	private Integer msgType;

	private String content;

	private String mediaId;

	private String title;

	private String description;

	private String musicUrl;

	private String hqmusicUrl;

	private String thumbMediald;

	private String articleCount;

	private String picUrl;

	private String url;

	private String storeCode;

	private String picLocalUrl;

	public String getPicLocalUrl() {
		return picLocalUrl;
	}

	public void setPicLocalUrl(String picLocalUrl) {
		this.picLocalUrl = picLocalUrl;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getMsgKey() {
		return msgKey;
	}

	public void setMsgKey(String msgKey) {
		this.msgKey = msgKey;
	}

	public Integer getMsgType() {
		return msgType;
	}

	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMusicUrl() {
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public String getHqmusicUrl() {
		return hqmusicUrl;
	}

	public void setHqmusicUrl(String hqmusicUrl) {
		this.hqmusicUrl = hqmusicUrl;
	}

	public String getThumbMediald() {
		return thumbMediald;
	}

	public void setThumbMediald(String thumbMediald) {
		this.thumbMediald = thumbMediald;
	}

	public String getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(String articleCount) {
		this.articleCount = articleCount;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	@Override
	public String toString() {
		return "MsgReply [sid=" + sid + ", eventType=" + eventType + ", ruleName=" + ruleName
				+ ", msgKey=" + msgKey + ", msgType=" + msgType + ", content=" + content
				+ ", mediaId=" + mediaId + ", title=" + title + ", description=" + description
				+ ", musicUrl=" + musicUrl + ", hqmusicUrl=" + hqmusicUrl + ", thumbMediald="
				+ thumbMediald + ", articleCount=" + articleCount + ", picUrl=" + picUrl + ", url="
				+ url + ", storeCode=" + storeCode + ", picLocalUrl=" + picLocalUrl + "]";
	}

}