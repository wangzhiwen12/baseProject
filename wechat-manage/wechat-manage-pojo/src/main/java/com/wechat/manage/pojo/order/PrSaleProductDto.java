package com.wechat.manage.pojo.order;

import java.math.BigDecimal;


/**
 * 
 * @Comment 销售单商品信息DTO
 * @Class Name SaleProductDto
 * @Author tangysh
 * @Create In 2015-7-14
 */
public class PrSaleProductDto {
	
	private String skuNo;
	
	private String spuNo;

	private String supplyProductNo;//专柜商品编码
	
	private String erpProductNo;//erp商品编号
	
	private String supplyInnerProdNo;//供应商内部商品编号
	
	private String unit;//商品单位
	
	private String brandNo;//中台品牌编号
	
	private String brandName;//中台品牌名称
	
	private String barcode;//条码
	
	private String colorNo;//颜色编号
	
	private String colorName;//颜色名称
	
	private String sizeNo;//规格编号
	
	private String sizeName;//规格名称
	
	private String managerCateNo;//管理分类
	
	private String statisticsCateNo;//统计分类
	
	private BigDecimal standPrice;//标准价
	
	private BigDecimal salePrice;//销售价
	
	private String priceType;//价格类型
	
	private String shippingAttribute;//物流属性
	
	private String discountCode;//扣率码
	
	private String productClass;//大中小类
	
	private String productType;//商品类别
	
	private BigDecimal tax;//销项税
	
	private String shoppeNo;//专柜号
	
	private String shoppeName;//专柜名称
	
	private String shopNo;//门店号
	
	private String storeName;//门店名称
	
	private String supplyNo;//供应商编号
	
	private String suppllyName;//供应商名称
	
	private String shoppeProName;//商品名称
	
	private String stockMode;   //库存方式  stockMode   1自库 2 虚库    
	
	private String operateMode;
	
	private String industry;//业态          industry       0百货 1超市 2电商
	
	private String inventory;//数量 
	
	private String discountLimit;//折扣底限
	
	private String shoppeGroup;//柜组
	
	private Integer codeType;//大码类型：0  价格码,1 长期统码,2 促销统码,3 特卖统码,4 扣率码,5 促销扣率码,6 单品码 

	private String picUrl;
	
	private String materialNum;//货号
	
	private String isPayReduce;//是否支付减
	
	
	public String getSkuNo() {
		return skuNo;
	}

	public void setSkuNo(String skuNo) {
		this.skuNo = skuNo;
	}

	public String getSpuNo() {
		return spuNo;
	}

	public void setSpuNo(String spuNo) {
		this.spuNo = spuNo;
	}

	public String getSupplyProductNo() {
		return supplyProductNo;
	}

	public void setSupplyProductNo(String supplyProductNo) {
		this.supplyProductNo = supplyProductNo;
	}

	public String getErpProductNo() {
		return erpProductNo;
	}

	public void setErpProductNo(String erpProductNo) {
		this.erpProductNo = erpProductNo;
	}

	public String getSupplyInnerProdNo() {
		return supplyInnerProdNo;
	}

	public void setSupplyInnerProdNo(String supplyInnerProdNo) {
		this.supplyInnerProdNo = supplyInnerProdNo;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getBrandNo() {
		return brandNo;
	}

	public void setBrandNo(String brandNo) {
		this.brandNo = brandNo;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getColorNo() {
		return colorNo;
	}

	public void setColorNo(String colorNo) {
		this.colorNo = colorNo;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getSizeNo() {
		return sizeNo;
	}

	public void setSizeNo(String sizeNo) {
		this.sizeNo = sizeNo;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public String getManagerCateNo() {
		return managerCateNo;
	}

	public void setManagerCateNo(String managerCateNo) {
		this.managerCateNo = managerCateNo;
	}

	public String getStatisticsCateNo() {
		return statisticsCateNo;
	}

	public void setStatisticsCateNo(String statisticsCateNo) {
		this.statisticsCateNo = statisticsCateNo;
	}

	public BigDecimal getStandPrice() {
		return standPrice;
	}

	public void setStandPrice(BigDecimal standPrice) {
		this.standPrice = standPrice;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String getShippingAttribute() {
		return shippingAttribute;
	}

	public void setShippingAttribute(String shippingAttribute) {
		this.shippingAttribute = shippingAttribute;
	}

	public String getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}

	public String getProductClass() {
		return productClass;
	}

	public void setProductClass(String productClass) {
		this.productClass = productClass;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public String getShoppeNo() {
		return shoppeNo;
	}

	public void setShoppeNo(String shoppeNo) {
		this.shoppeNo = shoppeNo;
	}

	public String getShoppeName() {
		return shoppeName;
	}

	public void setShoppeName(String shoppeName) {
		this.shoppeName = shoppeName;
	}

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getSupplyNo() {
		return supplyNo;
	}

	public void setSupplyNo(String supplyNo) {
		this.supplyNo = supplyNo;
	}

	public String getSuppllyName() {
		return suppllyName;
	}

	public void setSuppllyName(String suppllyName) {
		this.suppllyName = suppllyName;
	}

	public String getShoppeProName() {
		return shoppeProName;
	}

	public void setShoppeProName(String shoppeProName) {
		this.shoppeProName = shoppeProName;
	}

	public String getStockMode() {
		return stockMode;
	}

	public void setStockMode(String stockMode) {
		this.stockMode = stockMode;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getInventory() {
		return inventory;
	}

	public void setInventory(String inventory) {
		this.inventory = inventory;
	}

	public String getOperateMode() {
		return operateMode;
	}

	public void setOperateMode(String operateMode) {
		this.operateMode = operateMode;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getDiscountLimit() {
		return discountLimit;
	}

	public void setDiscountLimit(String discountLimit) {
		this.discountLimit = discountLimit;
	}

	public String getShoppeGroup() {
		return shoppeGroup;
	}

	public void setShoppeGroup(String shoppeGroup) {
		this.shoppeGroup = shoppeGroup;
	}

	public Integer getCodeType() {
		return codeType;
	}

	public void setCodeType(Integer codeType) {
		this.codeType = codeType;
	}

	public String getMaterialNum() {
		return materialNum;
	}

	public void setMaterialNum(String materialNum) {
		this.materialNum = materialNum;
	}

	public String getIsPayReduce() {
		return isPayReduce;
	}

	public void setIsPayReduce(String isPayReduce) {
		this.isPayReduce = isPayReduce;
	}

	@Override
	public String toString() {
		return "PrSaleProductDto [skuNo=" + skuNo + ", spuNo=" + spuNo + ", supplyProductNo="
				+ supplyProductNo + ", erpProductNo=" + erpProductNo + ", supplyInnerProdNo="
				+ supplyInnerProdNo + ", unit=" + unit + ", brandNo=" + brandNo + ", brandName="
				+ brandName + ", barcode=" + barcode + ", colorNo=" + colorNo + ", colorName="
				+ colorName + ", sizeNo=" + sizeNo + ", sizeName=" + sizeName + ", managerCateNo="
				+ managerCateNo + ", statisticsCateNo=" + statisticsCateNo + ", standPrice="
				+ standPrice + ", salePrice=" + salePrice + ", priceType=" + priceType
				+ ", shippingAttribute=" + shippingAttribute + ", discountCode=" + discountCode
				+ ", productClass=" + productClass + ", productType=" + productType + ", tax="
				+ tax + ", shoppeNo=" + shoppeNo + ", shoppeName=" + shoppeName + ", shopNo="
				+ shopNo + ", storeName=" + storeName + ", supplyNo=" + supplyNo + ", suppllyName="
				+ suppllyName + ", shoppeProName=" + shoppeProName + ", stockMode=" + stockMode
				+ ", operateMode=" + operateMode + ", industry=" + industry + ", inventory="
				+ inventory + ", discountLimit=" + discountLimit + ", shoppeGroup=" + shoppeGroup
				+ ", codeType=" + codeType + ", picUrl=" + picUrl + ", materialNum=" + materialNum
				+ ", isPayReduce=" + isPayReduce + "]";
	}
	
}
