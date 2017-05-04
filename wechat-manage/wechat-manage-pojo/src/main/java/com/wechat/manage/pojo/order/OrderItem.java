package com.wechat.manage.pojo.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
/**
 * 
* @ClassName: OrderItem
* @Description: 订单明细信息
* @author dell
* @date 2014-11-18 - 上午11:50:10
* @version : 1.0
 */
public class OrderItem implements Serializable{

	private static final long serialVersionUID = 8158986606336928505L;
	
	
	private String erpProductCode;
	
	private int rowNo;
	/**
	 * 供应商商品ID
	 * 长度：2
	 */
	private String supplyProductNo;
	/**
	 * SKU
	 * 长度：20
	 */
	private String skuNo;
	/**
	 * 款号
	 */
	private String styleCode;
	/**
	 * 颜色编码
	 */
	private String colorNo;
	/**
	 * 颜色
	 */
	private String colorName;
	/**
	 * 规格编码
	 */
	private String sizeNo;
	/**
	 * 规格
	 */
	private String sizeName;
	/**
	 * 数量
	 * 格式(20,2)
	 */
	private BigDecimal saleSum;
	/**
	 * 售价（分摊后的售价）
	 * 格式##.## 小数点2位
	 */
	private BigDecimal salesPrice;
	/**
	 * 商品描述
	 */
	private String productName;
	/**
	 * 是否为赠品
	 * true: 是
	   false: 不是
	 */
	private String isGift;
	/**
	 * 商品图片地址
	 */
	@JSONField(name="url")
	private String image;
	/**
	 * 先销后采标示
	 * true： 是
	   false： 不是
	 */
//	private boolean xxhcFlag;
	/**
	 * 经营方式
	 * 平台服务： PLATFORM_SERVICE
		联营：JOINT_VENTURE
		经销：DISTRIBUTE
		代销：COMMISSION
	 */
	private String businessMode;
	/**
	 * 虚库类型
	 * PHYSICAL_WAREHOUSE：自库
	   VIRTUAL_WAREHOUSE：虚库
	 */
	private String WarehouseType;
	/**
	 * 出货主体编码
	 * 门店、电商仓、供应商仓对应的编码
	 */
//	private String locationOwnerId;
	/**
	 * 出货主体名称
	 * **门店、**电商仓、**供应商仓
	 */
//	private String locationOwnerName;
	/**
	 * 库存地点编码
	 * 专柜、电商仓、供应商仓对应的编码
	 */
//	private String locationId;
	/**
	 * 库存地点名称
	 * **专柜、**电商仓、**供应商仓
	 */
//	private String locationName;
	/**
	 * 数量
	 */
//	private BigDecimal quantity;
	/**
	 * 物流属性
	 * 是固体还是液体
	 */
	private String shippingAttribute;
	/**
	 *  供应商编号
	 */
	private String supplyCode;
	/**
	 * 供应商名称
	 */
	private String suppllyName;
	/**
	 * 品牌
	 */
	private String brandNo;
	/**
	 * 品牌名称
	 */
	private String brandName;
	
	private String unit;
	/**
	 * 运费分摊
	 */
	private String shippingFeeSplitAmount;
	
	private BigDecimal totalDiscount;

	private String barcode;
	
	private BigDecimal paymentAmount;//折后价* 购买数量
	
	private String naviCategoryId;//展示分类id
	
	private String naviCatagoryName;//展示分类名称
	
	private String naviCategoryParentId;//展示分类父类id
	
	/**
	 * 促销分摊集合
	 */

	/**
	 * 返利分摊集合
	 */

	
	/**
	 * 支付介质分摊
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getErpProductCode() {
		return erpProductCode;
	}

	public void setErpProductCode(String erpProductCode) {
		this.erpProductCode = erpProductCode;
	}

	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}

	public String getSupplyProductNo() {
		return supplyProductNo;
	}

	public void setSupplyProductNo(String supplyProductNo) {
		this.supplyProductNo = supplyProductNo;
	}

	public String getSkuNo() {
		return skuNo;
	}

	public void setSkuNo(String skuNo) {
		this.skuNo = skuNo;
	}

	public String getStyleCode() {
		return styleCode;
	}

	public void setStyleCode(String styleCode) {
		this.styleCode = styleCode;
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

	public BigDecimal getSaleSum() {
		return saleSum;
	}

	public void setSaleSum(BigDecimal saleSum) {
		this.saleSum = saleSum;
	}

	public BigDecimal getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(BigDecimal salesPrice) {
		this.salesPrice = salesPrice;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getIsGift() {
		return isGift;
	}

	public void setIsGift(String isGift) {
		this.isGift = isGift;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getBusinessMode() {
		return businessMode;
	}

	public void setBusinessMode(String businessMode) {
		this.businessMode = businessMode;
	}

	public String getWarehouseType() {
		return WarehouseType;
	}

	public void setWarehouseType(String warehouseType) {
		WarehouseType = warehouseType;
	}

	public String getShippingAttribute() {
		return shippingAttribute;
	}

	public void setShippingAttribute(String shippingAttribute) {
		this.shippingAttribute = shippingAttribute;
	}

	public String getSupplyCode() {
		return supplyCode;
	}

	public void setSupplyCode(String supplyCode) {
		this.supplyCode = supplyCode;
	}

	public String getSuppllyName() {
		return suppllyName;
	}

	public void setSuppllyName(String suppllyName) {
		this.suppllyName = suppllyName;
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getShippingFeeSplitAmount() {
		return shippingFeeSplitAmount;
	}

	public void setShippingFeeSplitAmount(String shippingFeeSplitAmount) {
		this.shippingFeeSplitAmount = shippingFeeSplitAmount;
	}

	public BigDecimal getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(BigDecimal totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getNaviCategoryId() {
		return naviCategoryId;
	}

	public void setNaviCategoryId(String naviCategoryId) {
		this.naviCategoryId = naviCategoryId;
	}

	public String getNaviCatagoryName() {
		return naviCatagoryName;
	}

	public void setNaviCatagoryName(String naviCatagoryName) {
		this.naviCatagoryName = naviCatagoryName;
	}

	public String getNaviCategoryParentId() {
		return naviCategoryParentId;
	}

	public void setNaviCategoryParentId(String naviCategoryParentId) {
		this.naviCategoryParentId = naviCategoryParentId;
	}
}
