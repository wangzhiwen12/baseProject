package com.wechat.manage.pojo.product.vo;

import java.util.ArrayList;
import java.util.List;

public class PcmProYeColorInfoDto {
	private String colorSid;// 色系
	private String colorName;// 色系编码
	private String thumbnailUrl;// 缩略图
	private List<PcmProYePicStanInfoDto> stanList = new ArrayList<PcmProYePicStanInfoDto>();// 规格列表

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getColorSid() {
		return colorSid;
	}

	public void setColorSid(String colorSid) {
		this.colorSid = colorSid;
	}

	public List<PcmProYePicStanInfoDto> getStanList() {
		return stanList;
	}

	public void setStanList(List<PcmProYePicStanInfoDto> stanList) {
		this.stanList = stanList;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	@Override
	public String toString() {
		return "PcmProYeColorInfoDto [colorSid=" + colorSid + ", colorName=" + colorName
				+ ", thumbnailUrl=" + thumbnailUrl + ", stanList=" + stanList + "]";
	}

}
