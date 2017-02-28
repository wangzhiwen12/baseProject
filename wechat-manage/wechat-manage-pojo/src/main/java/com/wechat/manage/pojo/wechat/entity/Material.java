package com.wechat.manage.pojo.wechat.entity;

import java.util.Date;

public class Material {// 素材
	private Integer sid;

	private String storeCode;// 门店编码

	private String materialType;// 素材类型 image/news

	private String mediaId;// 对应微信mid

	private String imageName;// 图片素材名称

	private String title;// 图文素材标题

	private String thumbMediaId;// 图文消息的封面图片素材id（必须是永久mediaID）

	private String thumbUrl;

	private Integer showCoverPic;// 是否显示封面1显示0不显示

	private String author;// 作者

	private String digest;// 摘要

	private String content;// 正文内容

	private String picUrl;// 图文页的URL

	private String contentSoureUrl;// 原文url

	private String localUrl;// 本地图片地址

	private Date createTime;// 素材创建时间

	private Date updateTime;// 最后一次修改时间

	private String localContent;

	public String getLocalContent() {
		return localContent;
	}

	public void setLocalContent(String localContent) {
		this.localContent = localContent;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThumbUrl() {
		return thumbUrl;
	}

	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	public Integer getShowCoverPic() {
		return showCoverPic;
	}

	public void setShowCoverPic(Integer showCoverPic) {
		this.showCoverPic = showCoverPic;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

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

	public String getContentSoureUrl() {
		return contentSoureUrl;
	}

	public void setContentSoureUrl(String contentSoureUrl) {
		this.contentSoureUrl = contentSoureUrl;
	}

	public String getLocalUrl() {
		return localUrl;
	}

	public void setLocalUrl(String localUrl) {
		this.localUrl = localUrl;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "Material [sid=" + sid + ", storeCode=" + storeCode + ", materialType="
				+ materialType + ", mediaId=" + mediaId + ", imageName=" + imageName + ", title="
				+ title + ", thumbMediaId=" + thumbMediaId + ", thumbUrl=" + thumbUrl
				+ ", showCoverPic=" + showCoverPic + ", author=" + author + ", digest=" + digest
				+ ", content=" + content + ", picUrl=" + picUrl + ", contentSoureUrl="
				+ contentSoureUrl + ", localUrl=" + localUrl + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", localContent=" + localContent + "]";
	}

}