/*   
 * @(#)SalesCenterDao.java       2014-11-24 
 *   
 * 王府井集团拥有完全的版权   
 * 使用者必须经过许可   
 */  
package com.wechat.manage.pojo.cart;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;


/**     
 *  活动赠品或加价购商品   
 *     
 * @author  xuc33 
 * @since   
 */
public class PromotionGift implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//0-赠品/1-普通单品/2-促销统码(无促销不允许销售)/3-特卖统码(不促销)/9-印花码
	@JSONField(name="flag")
	private String flag;
	
	//商品所属经营公司
	@JSONField(name="mana_unit")
	private String manaUnit;
	
	//商品所属门店
	@JSONField(name="market")
	private String market;
	
	//商品编码(SKU子码销售时填对应的SPU母码)
	@JSONField(name="itemcode")
	private String itemCode;
	
	//商品名称
	@JSONField(name="itemname")
	private String itemName;
	
	//专柜
	@JSONField(name="counter")
	private String counter;
	
	//件数
	@JSONField(name="qty")
	private double qty;
	
	//售价
	@JSONField(name="price")
	private double price;
	
	//售价金额
	@JSONField(name="list_amount")
	private double listAmount;
	
	//成交金额
	@JSONField(name="sale_amount")
	private double saleAmount;
	
	//促销商品参与ID
	@JSONField(name="pop_seqno")
	private long popSeqNo;
	
	//活动业务单号
	@JSONField(name="pop_event_billid")
	private String popEventBillId;
	
	//促销活动ID
	@JSONField(name="pop_event_id")
	private long popEventId;
	
	//促销规则ID
	@JSONField(name="pop_policy_id")
	private long popPolicyId;
	
	//购物车中该商品是否选中 1选中，0未选中
	private String selected;
	
	//物品图片的地址(小图)
	private String imgUrl;
	
	 /*========================以下为换购商品信息===================*/
	
	/**
	 * 赠品分组关系(0-组之间and+组内or/1-组之间or+组内and)
	 */
	private String relate;	
	/**
	 * 赠品分组号
	 */
	private int group;
	/**
	 * 换购商品维度
	 */
	@JSONField(name="codemode")
	private String codeMode;	

	//codejson	 换购商品维度编码
	@JSONField(name="codejson")
	private JSON codeJson;
	
	/**
	 * 参与模式
	 */
	@JSONField(name="joinmode")
	private String joinMode;
	/**
	 * 价格模式
	 */
	@JSONField(name="prcmode")
	private String prcMode;
	/**
	 * 加价金额
	 */
	private Double poplsj;
	/**
	 * 本项赠品的基础数量
	 */
	@JSONField(name="base_qty")
	private int  baseQty;
	/**
	 * 本项赠品的限制数量
	 */
	@JSONField(name="limit_qty")
	private int limitQty;
	/**
	 * OR分组赠品总的限送数量
	 */
	
	@JSONField(name="max_qty")
	private int maxQty;
	
	//库存
	private double stock;
	
	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
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
	public String getCounter() {
		return counter;
	}
	public void setCounter(String counter) {
		this.counter = counter;
	}
	
	public String getPopEventBillId() {
		return popEventBillId;
	}
	public void setPopEventBillId(String popEventBillId) {
		this.popEventBillId = popEventBillId;
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
	public double getListAmount() {
		return listAmount;
	}
	public void setListAmount(double listAmount) {
		this.listAmount = listAmount;
	}
	public double getSaleAmount() {
		return saleAmount;
	}
	public void setSaleAmount(double saleAmount) {
		this.saleAmount = saleAmount;
	}
	public long getPopSeqNo() {
		return popSeqNo;
	}
	public void setPopSeqNo(long popSeqNo) {
		this.popSeqNo = popSeqNo;
	}
	public long getPopEventId() {
		return popEventId;
	}
	public void setPopEventId(long popEventId) {
		this.popEventId = popEventId;
	}
	public long getPopPolicyId() {
		return popPolicyId;
	}
	public void setPopPolicyId(long popPolicyId) {
		this.popPolicyId = popPolicyId;
	}
	public String getRelate() {
		return relate;
	}
	public void setRelate(String relate) {
		this.relate = relate;
	}
	public int getGroup() {
		return group;
	}
	public void setGroup(int group) {
		this.group = group;
	}
	public String getCodeMode() {
		return codeMode;
	}
	public void setCodeMode(String codeMode) {
		this.codeMode = codeMode;
	}
	
	public JSON getCodeJson() {
		return codeJson;
	}
	public void setCodeJson(JSON codeJson) {
		this.codeJson = codeJson;
	}
	public String getJoinMode() {
		return joinMode;
	}
	public void setJoinMode(String joinMode) {
		this.joinMode = joinMode;
	}
	public String getPrcMode() {
		return prcMode;
	}
	public void setPrcMode(String prcMode) {
		this.prcMode = prcMode;
	}
	public Double getPoplsj() {
		return poplsj;
	}
	public void setPoplsj(Double poplsj) {
		this.poplsj = poplsj;
	}
	public int getBaseQty() {
		return baseQty;
	}
	public void setBaseQty(int baseQty) {
		this.baseQty = baseQty;
	}
	public int getLimitQty() {
		return limitQty;
	}
	public void setLimitQty(int limitQty) {
		this.limitQty = limitQty;
	}
	public int getMaxQty() {
		return maxQty;
	}
	public void setMaxQty(int maxQty) {
		this.maxQty = maxQty;
	}
	public double getStock() {
		return stock;
	}
	public void setStock(double stock) {
		this.stock = stock;
	}

	
	
}
