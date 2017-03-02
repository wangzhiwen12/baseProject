package com.wechat.manage.pojo.wechat.vo;

import java.util.Date;

public class MaterialReDto {
	private String materialType;// 素材类型 image/news

	private String mediaId;// 对应微信mid

	private String title;// 图文素材标题

	private Integer showCoverPic;// 是否显示封面1显示0不显示

	private String content;// 正文内容

	private String picUrl;// 图文页的URL

	private String localUrl;// 本地图片地址

	private Date updateTime;// 最后一次修改时间

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getShowCoverPic() {
		return showCoverPic;
	}

	public void setShowCoverPic(Integer showCoverPic) {
		this.showCoverPic = showCoverPic;
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

	public String getLocalUrl() {
		return localUrl;
	}

	public void setLocalUrl(String localUrl) {
		this.localUrl = localUrl;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "MaterialReDto [materialType=" + materialType + ", mediaId=" + mediaId + ", title="
				+ title + ", showCoverPic=" + showCoverPic + ", content=" + content + ", picUrl="
				+ picUrl + ", localUrl=" + localUrl + ", updateTime=" + updateTime + "]";
	}

}
