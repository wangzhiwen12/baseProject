package com.wechat.manage.pojo.product.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PcmProYeInfoDto {
	private String skuCode;// sku编码
	private String skuName;// sku标准品名
	private String originalPrice;// 市场价
	private String colorSid;// 色系名称
	private String colorName;// 色系编码
	private String stanName;// 规格名称
	private String brandName;// 品牌名称
	private String brandCode;// 品牌编码
	private String brandDesc;// 品牌描述
	private String brandLogo;// 品牌logo
	private String thumbnailUrl;// 缩略图
	private String sizePicture;// 尺码对照表
	private String customerServices;// 售后服务
	private String isSoldOut;// 是否售罄 0有货，1无货
	private String shortDesc;// 短描述
	private String longDesc;// 长描述
	private String sellingStatus;// 上架状态 0未上架 1已上架 2已下架
	private List<PcmProYeStanInfoDto> stanPicList = new ArrayList<PcmProYeStanInfoDto>();// 图片规格列表
	private List<Map<String, Object>> skuList = new ArrayList<Map<String, Object>>();// 商品列表
	private List<PcmProYeColorInfoDto> colorList = new ArrayList<PcmProYeColorInfoDto>();// 色系列表
	private List<PcmProNewStanInfoDto> stanNewList = new ArrayList<PcmProNewStanInfoDto>();
	private List<Map<String, Object>> cateList = new ArrayList<Map<String, Object>>();// 展示分类
	private List<Map<String, Object>> propList = new ArrayList<Map<String, Object>>();// 属性Info列表

	public List<PcmProNewStanInfoDto> getStanNewList() {
		return stanNewList;
	}

	public void setStanNewList(List<PcmProNewStanInfoDto> stanNewList) {
		this.stanNewList = stanNewList;
	}

	public String getSellingStatus() {
		return sellingStatus;
	}

	public void setSellingStatus(String sellingStatus) {
		this.sellingStatus = sellingStatus;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getLongDesc() {
		return longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public String getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
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

	public String getStanName() {
		return stanName;
	}

	public void setStanName(String stanName) {
		this.stanName = stanName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getBrandDesc() {
		return brandDesc;
	}

	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}

	public String getBrandLogo() {
		return brandLogo;
	}

	public void setBrandLogo(String brandLogo) {
		this.brandLogo = brandLogo;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getSizePicture() {
		return sizePicture;
	}

	public void setSizePicture(String sizePicture) {
		this.sizePicture = sizePicture;
	}

	public String getCustomerServices() {
		return customerServices;
	}

	public void setCustomerServices(String customerServices) {
		this.customerServices = customerServices;
	}

	public String getIsSoldOut() {
		return isSoldOut;
	}

	public void setIsSoldOut(String isSoldOut) {
		this.isSoldOut = isSoldOut;
	}

	public List<PcmProYeStanInfoDto> getStanPicList() {
		return stanPicList;
	}

	public void setStanPicList(List<PcmProYeStanInfoDto> stanPicList) {
		this.stanPicList = stanPicList;
	}

	public List<Map<String, Object>> getSkuList() {
		return skuList;
	}

	public void setSkuList(List<Map<String, Object>> skuList) {
		this.skuList = skuList;
	}

	public List<PcmProYeColorInfoDto> getColorList() {
		return colorList;
	}

	public void setColorList(List<PcmProYeColorInfoDto> colorList) {
		this.colorList = colorList;
	}

	public List<Map<String, Object>> getCateList() {
		return cateList;
	}

	public void setCateList(List<Map<String, Object>> cateList) {
		this.cateList = cateList;
	}

	public List<Map<String, Object>> getPropList() {
		return propList;
	}

	public void setPropList(List<Map<String, Object>> propList) {
		this.propList = propList;
	}

	@Override
	public String toString() {
		return "PcmProYeInfoDto [skuCode=" + skuCode + ", skuName=" + skuName + ", originalPrice="
				+ originalPrice + ", colorSid=" + colorSid + ", colorName=" + colorName
				+ ", stanName=" + stanName + ", brandName=" + brandName + ", brandCode="
				+ brandCode + ", brandDesc=" + brandDesc + ", brandLogo=" + brandLogo
				+ ", thumbnailUrl=" + thumbnailUrl + ", sizePicture=" + sizePicture
				+ ", customerServices=" + customerServices + ", isSoldOut=" + isSoldOut
				+ ", shortDesc=" + shortDesc + ", longDesc=" + longDesc + ", sellingStatus="
				+ sellingStatus + ", stanPicList=" + stanPicList + ", skuList=" + skuList
				+ ", colorList=" + colorList + ", stanNewList=" + stanNewList + ", cateList="
				+ cateList + ", propList=" + propList + "]";
	}

}
