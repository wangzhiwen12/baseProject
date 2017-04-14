package com.wechat.manage.pojo.product.vo;

public class PcmProNewColorInfoDto {
	private String colorSid;// 色系
	private String colorName;// 色系编码
	private String thumbnailUrl;// 缩略图
	private String isSoldOut;// 是否售罄 0有货，1无货

	public String getIsSoldOut() {
		return isSoldOut;
	}

	public void setIsSoldOut(String isSoldOut) {
		this.isSoldOut = isSoldOut;
	}

	public String getColorSid() {
		return colorSid;
	}

	public void setColorSid(String colorSid) {
		this.colorSid = colorSid;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	@Override
	public String toString() {
		return "PcmProNewColorInfoDto [colorSid=" + colorSid + ", colorName=" + colorName
				+ ", thumbnailUrl=" + thumbnailUrl + ", isSoldOut=" + isSoldOut + "]";
	}

}
