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
 *  活动模型   
 *     
 * @author  xuc33 
 * @since   JDK1.7
 */
public class Promotion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5909101841136173427L;

	//促销商品参与ID
	@JSONField(name="pop_seqno")
	private long popSeqno;
	
	//促销活动ID
	@JSONField(name="pop_event_id")
	private long popEventId  ;
	
	//促销活动业务单号
	@JSONField(name="pop_event_billid")
	private String popEventBillId;
	
	//促销策略ID
	@JSONField(name="pop_policy_id")
	private long popPolicyId;
	
	//促销规则类型
	@JSONField(name="pop_policy_type")
	private String popPolicyType;
	
	//促销活动描述
	@JSONField(name="pop_describe")
	private String popDescribe  ;
	
	//短描述，门店级活动在单价与中间的位置展示信息
	private String popShotDescribe;
	
	//商品在促销活动中分摊的折扣金额
	@JSONField(name="discount_amount")
	private double discountAmount  ;
	
	//活动类型
	@JSONField(name="pop_policy_group")
	private String popPolicyGroup;
	
	
	//活动总价格
	private double popAmount;
	
	//商品
	private List<Product> products;
	
	//赠品信息
	private List<PromotionGift> gifts;
	
	//页面的满减、满赠等图标
	private String popTypeImgUrl;

	//活动策略标签
	private String popPolicyTag;
	
	public long getPopEventId() {
		return popEventId;
	}

	public void setPopEventId(long popEventId) {
		this.popEventId = popEventId;
	}

	public String getPopDescribe() {
		return popDescribe;
	}

	public void setPopDescribe(String popDescribe) {
		this.popDescribe = popDescribe;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	
	public double getPopAmount() {
		return popAmount;
	}

	public void setPopAmount(double popAmount) {
		this.popAmount = popAmount;
	}

	
	public String getPopPolicyGroup() {
		return popPolicyGroup;
	}

	public void setPopPolicyGroup(String popPolicyGroup) {
		this.popPolicyGroup = popPolicyGroup;
	}

	public long getPopSeqno() {
		return popSeqno;
	}

	public void setPopSeqno(long popSeqno) {
		this.popSeqno = popSeqno;
	}

	
	public String getPopEventBillId() {
		return popEventBillId;
	}

	public void setPopEventBillId(String popEventBillId) {
		this.popEventBillId = popEventBillId;
	}

	public long getPopPolicyId() {
		return popPolicyId;
	}

	public void setPopPolicyId(long popPolicyId) {
		this.popPolicyId = popPolicyId;
	}

	public String getPopPolicyType() {
		return popPolicyType;
	}

	public void setPopPolicyType(String popPolicyType) {
		this.popPolicyType = popPolicyType;
	}

	public List<PromotionGift> getGifts() {
		return gifts;
	}

	public void setGifts(List<PromotionGift> gifts) {
		this.gifts = gifts;
	}

	public String getPopShotDescribe() {
		return popShotDescribe;
	}

	public void setPopShotDescribe(String popShotDescribe) {
		this.popShotDescribe = popShotDescribe;
	}

	public String getPopTypeImgUrl() {
		return popTypeImgUrl;
	}

	public void setPopTypeImgUrl(String popTypeImgUrl) {
		this.popTypeImgUrl = popTypeImgUrl;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPopPolicyTag() {
		return popPolicyTag;
	}

	public void setPopPolicyTag(String popPolicyTag) {
		this.popPolicyTag = popPolicyTag;
	}
	
}
