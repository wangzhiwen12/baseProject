/*   
 * @(#)SalesCenterDao.java       2014-11-6 
 *   
 * 王府井集团拥有完全的版权   
 * 使用者必须经过许可   
 */  
package com.wechat.manage.pojo.cart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**     
 *  商品详情   
 *     
 * @author  xuc33 
 * @since   
 */
public class SellDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8713717282749375100L;

	//行号
	private int rowNo ;


	/**
	 * 商品所属经营公司
	 */
	@JSONField(name="mana_unit")
	private String manaUnit ;
	
	//门店
	private String market ;
	
	private String storeName;
	
	//SKU
	@JSONField(name="itemcode")
	private String itemCode ;
	
	@JSONField(name="itemname")
	private String itemName;
	
	/**
	 * 	0-赠品/1-普通单品/2-促销统码(无促销不允许销售)/3-特卖统码(不促销)/9-印花码
	 */
	private String flag;
	/**
	 * 商品条码(中台编码)
	 */
	@JSONField(name="barcode")
	private String barCode;
	/**
	 * 单位码/属性码(SKU子码销售时填对应的SKU子码)
	 */
	@JSONField(name="unitcode")
	private String unitCode;
	/**
	 * 包装含量
	 */
	private Double factor;
	/**
	 * 用于计算促销范围(全渠道统一编码)
	 */
	private String gz;	
	/**
	 * 用于计算促销范围(全渠道统一编码)
	 */
	private String counter;	
	/**
	 * 用于计算促销范围(全渠道统一编码)
	 */
	private String brand;
	/**
	 * 用于计算促销范围(全渠道统一编码)
	 */
	private String category;
	/**
	 * 用于计算促销范围(全渠道统一编码)
	 */
	private String supplier;
	/**
	 * 用于计算促销范围(全渠道统一编码)
	 */
	private String contract;
	/**
	 * 用于计算促销范围(全渠道统一编码)
	 * 扣率码
	 */
	private String klm;
	/**
	 * 	用于计算促销范围
	 */
	private String 	poptag;
	/**
	 * 件数
	 */
	private Double qty;	
	/**
	 * 价格
	 */
	private Double price;	
	
	//售价金额  qty * price
	@JSONField(name="list_amount")
	private Double listAmount;
	
	//总折扣
	@JSONField(name="total_discount")
	private double totalDiscount;
	
	//成交金额  list_amt - total_discount
	@JSONField(name="sale_amount")
	private Double saleAmount;
	/**
	 * 
	 */
	@JSONField(name="original_rowno")
	private int originalRowno;

	
	@JSONField(name="pop_details")
	private List<PopDetail> popDetails;

	@JSONField(name="coupon_uses")
	private List<CouponUse> couponUses;
	
	@JSONField(name="coupon_gains")
	private List<CouponGain> couponGains;

	public int getRowNo() {
		return rowNo;
	}


	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}


	public String getManaUnit() {
		return manaUnit;
	}


	public void setManaUnit(String manaUnit) {
		this.manaUnit = manaUnit;
	}


	public String getMarket() {
		return market;
	}


	public void setMarket(String market) {
		this.market = market;
	}


	public String getItemCode() {
		return itemCode;
	}


	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public String getFlag() {
		return flag;
	}


	public void setFlag(String flag) {
		this.flag = flag;
	}


	public String getBarCode() {
		return barCode;
	}


	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}


	public String getUnitCode() {
		return unitCode;
	}


	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}


	public Double getFactor() {
		return factor;
	}


	public void setFactor(Double factor) {
		this.factor = factor;
	}


	public String getGz() {
		return gz;
	}


	public void setGz(String gz) {
		this.gz = gz;
	}


	public String getCounter() {
		return counter;
	}


	public void setCounter(String counter) {
		this.counter = counter;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getSupplier() {
		return supplier;
	}


	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}


	public String getContract() {
		return contract;
	}


	public void setContract(String contract) {
		this.contract = contract;
	}


	public String getKlm() {
		return klm;
	}


	public void setKlm(String klm) {
		this.klm = klm;
	}


	public String getPoptag() {
		return poptag;
	}


	public void setPoptag(String poptag) {
		this.poptag = poptag;
	}


	public Double getQty() {
		return qty;
	}


	public void setQty(Double qty) {
		this.qty = qty;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public String getStoreName() {
		return storeName;
	}


	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}


	public int getOriginalRowno() {
		return originalRowno;
	}


	public void setOriginalRowno(int originalRowno) {
		this.originalRowno = originalRowno;
	}


	public List<PopDetail> getPopDetails() {
		return popDetails;
	}


	public void setPopDetails(List<PopDetail> popDetails) {
		this.popDetails = popDetails;
	}


	public Double getListAmount() {
		return listAmount;
	}


	public void setListAmount(Double listAmount) {
		this.listAmount = listAmount;
	}


	public double  getTotalDiscount() {
		return totalDiscount;
	}


	public void setTotalDiscount(double  totalDiscount) {
		this.totalDiscount = totalDiscount;
	}


	public Double getSaleAmount() {
		return saleAmount;
	}


	public void setSaleAmount(Double saleAmount) {
		this.saleAmount = saleAmount;
	}


	public List<CouponUse> getCouponUses() {
		return couponUses;
	}


	public void setCouponUses(List<CouponUse> couponUses) {
		this.couponUses = couponUses;
	}


	public List<CouponGain> getCouponGains() {
		return couponGains;
	}


	public void setCouponGains(List<CouponGain> couponGains) {
		this.couponGains = couponGains;
	}
	
	
}
