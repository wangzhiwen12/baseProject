/*   
 * @(#)SalesCenterDao.java       2014-11-7 
 *   
 * 王府井集团拥有完全的版权   
 * 使用者必须经过许可   
 */  
package com.wechat.manage.pojo.cart;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**     
 *  此类功能描述    
 *     
 * @author  xuc33 
 * @since   JDK1.7
 */
public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6867529115070644788L;
	
	@JSONField(name="rowno")
	private String rowNo;
	
	//商品描述
	private String desc;
	
	//商品图片
	private String imgUrl;  
	
	 //商品详情地址
	private String proUrl;
	
	//颜色
	private String color;	
	
	 //单位	
	private String unit;
	
	 // 款号
	private String size;
	
	//0-赠品/1-普通单品/2-促销统码(无促销不允许销售)/3-特卖统码(不促销)/9-印花码
	@JSONField(name="flag")
	private String flag;
	
	//专柜商品编码
	@JSONField(name="itemcode")
	private String sku;
	
	//商品的sku
	private String partNumber;
	
	//商品spu
	private String spu;
	
	//添加专柜编码
	private String counter;
	
	//商品数量
	@JSONField(name="qty")
	private double qty;
	
	@JSONField(name="price")
	private double price;
	
	//会员折扣
	private double vipDiscount;
	
	//直降金额
	private double directDiscount;
	
	//库存
	private double stock;
	
	//商品总价
	@JSONField(name="list_amount")
	private double totalAmount;
	
	@JSONField(name="total_discount")
	private double totalDiscount;
	
	//成交金额 list_amt - total_discount
	@JSONField(name="sale_amount")
	private double saleAmount;
	/**
	 * 是否支持cod
	 * 	Y 支持
	 *  N 不支持
	 */
	private String isCodPay;
	public String getIsCodPay() {
		return isCodPay;
	}

	public void setIsCodPay(String isCodPay) {
		this.isCodPay = isCodPay;
	}

	//商品选中状态
	private String isSelected;
	
	//页面展示商品总价减活动折扣金额
	private double promotionAmount;
	
	//结算页图片
	private String minProUrl;
	
	//商品上下架状态
	private boolean a2c;
	
	//是否有推荐支付活动
	private String recommendPay;
	
	private List<Promotion> promotions;

	public String getRowNo() {
		return rowNo;
	}

	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	
	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public double getQty() {
		return qty;
	}

	public void setQty(double qty) {
		this.qty = qty;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}
	
	

	public double getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public String getProUrl() {
		return proUrl;
	}

	public void setProUrl(String proUrl) {
		this.proUrl = proUrl;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public double getStock() {
		return stock;
	}

	public void setStock(double stock) {
		this.stock = stock;
	}

	public double getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(double saleAmount) {
		this.saleAmount = saleAmount;
	}

	public double getPromotionAmount() {
		return promotionAmount;
	}

	public void setPromotionAmount(double promotionAmount) {
		this.promotionAmount = promotionAmount;
	}

	public String getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(String isSelected) {
		this.isSelected = isSelected;
	}

	public String getMinProUrl() {
		return minProUrl;
	}

	public void setMinProUrl(String minProUrl) {
		this.minProUrl = minProUrl;
	}

	public boolean isA2c() {
		return a2c;
	}

	public void setA2c(boolean a2c) {
		this.a2c = a2c;
	}

	public String getCounter() {
		return counter;
	}

	public void setCounter(String counter) {
		this.counter = counter;
	}

	public double getVipDiscount() {
		return vipDiscount;
	}

	public void setVipDiscount(double vipDiscount) {
		this.vipDiscount = vipDiscount;
	}

	public double getDirectDiscount() {
		return directDiscount;
	}

	public void setDirectDiscount(double directDiscount) {
		this.directDiscount = directDiscount;
	}

	public String getRecommendPay() {
		return recommendPay;
	}

	public void setRecommendPay(String recommendPay) {
		this.recommendPay = recommendPay;
	}

	public String getSpu() {
		return spu;
	}

	public void setSpu(String spu) {
		this.spu = spu;
	}
	
	
}
