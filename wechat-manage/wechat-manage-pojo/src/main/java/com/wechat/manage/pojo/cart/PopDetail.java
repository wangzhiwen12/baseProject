package com.wechat.manage.pojo.cart;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 优惠信息
 * @author yfc
 */
public class PopDetail implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 0-商品促销明细/1-商品活动标签
	 */
	@JSONField(name="pop_mode")
	private String popMode;
	/**
	 * 促销商品参与ID
	 */
	@JSONField(name="pop_seqno")
	private Long popSeqno;	
	/**
	 * 手工折扣(GRANT)、会员折扣(VIP)、各种促销活动类型
	 */
	@JSONField(name="pop_policy_group")
	private String popPolicyGroup;
	/**
	 * 促销活动ID
	 */
	@JSONField(name="pop_event_id")
	private Long popEventId;
	
	//促销活动业务单号
	@JSONField(name="pop_event_billid")
	private String popEventBillId;
	
	/**
	 * 促销活动级别(同类型活动叠加的级别,例如：单品级、柜组级、全场级)
	 */
	@JSONField(name="pop_event_level")
	private Long popEventLevel;	
	/**
	 * 促销规则ID
	 */
	@JSONField(name="pop_policy_id")
	private Long popPolicyId;		
	/**
	 * 促销规则类型
	 */
	@JSONField(name="pop_policy_type")
	private String popPolicyType;
	/**
	 * 做活动时人为定义的促销助记符
	 */
	@JSONField(name="pop_policy_symbol")
	private String popPolicySymbol;
	/**
	 * 促销活动描述
	 */
	@JSONField(name="pop_describe")
	private String popDescribe;
	/**
	 * 0-任选（终端要按同活动Id+pop_select=0进行分组选择）
	 */
	@JSONField(name="pop_select")
	private String popSelect;
	/**
	 * 商品在促销活动中分摊的折扣金额
	 */
	@JSONField(name="discount_amount")
	private Double discountAmount;
	/**
	 * 商品在促销活动中分摊的折扣中供应商承担比例
	 */
	@JSONField(name="discount_share")
	private Double discountShare;
	/**
	 * 0-减免多少/1-减免到
	 */
	@JSONField(name="freight_mode")
	private String freightMode;
	
	//营销活动类型
	@JSONField(name="pop_event_scd_name")
	private String popEventScdName;
	
	/**
	 * 减免金额
	 */
	@JSONField(name="freight_amount")
	private Double freightAmount;
	
	//活动策略标签
	@JSONField(name="pop_policy_tag")
	private String popPolicyTag;
	
	//赠品分摊金额
	@JSONField(name="giftallot_amount")
	private double giftallotAmount;
	
	public String getPopMode() {
		return popMode;
	}
	public void setPopMode(String popMode) {
		this.popMode = popMode;
	}
	public Long getPopSeqno() {
		return popSeqno;
	}
	public void setPopSeqno(Long popSeqno) {
		this.popSeqno = popSeqno;
	}

	public String getPopPolicyGroup() {
		return popPolicyGroup;
	}
	public void setPopPolicyGroup(String popPolicyGroup) {
		this.popPolicyGroup = popPolicyGroup;
	}
	public Long getPopEventId() {
		return popEventId;
	}
	
	public String getPopEventBillId() {
		return popEventBillId;
	}
	public void setPopEventBillId(String popEventBillId) {
		this.popEventBillId = popEventBillId;
	}
	public void setPopEventId(Long popEventId) {
		this.popEventId = popEventId;
	}
	public Long getPopEventLevel() {
		return popEventLevel;
	}
	public void setPopEventLevel(Long popEventLevel) {
		this.popEventLevel = popEventLevel;
	}
	public Long getPopPolicyId() {
		return popPolicyId;
	}
	public void setPopPolicyId(Long popPolicyId) {
		this.popPolicyId = popPolicyId;
	}
	public String getPopPolicyType() {
		return popPolicyType;
	}
	public void setPopPolicyType(String popPolicyType) {
		this.popPolicyType = popPolicyType;
	}
	public String getPopPolicySymbol() {
		return popPolicySymbol;
	}
	public void setPopPolicySymbol(String popPolicySymbol) {
		this.popPolicySymbol = popPolicySymbol;
	}
	public String getPopDescribe() {
		return popDescribe;
	}
	public void setPopDescribe(String popDescribe) {
		this.popDescribe = popDescribe;
	}
	public String getPopSelect() {
		return popSelect;
	}
	public void setPopSelect(String popSelect) {
		this.popSelect = popSelect;
	}
	public Double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public Double getDiscountShare() {
		return discountShare;
	}
	public void setDiscountShare(Double discountShare) {
		this.discountShare = discountShare;
	}
	public String getFreightMode() {
		return freightMode;
	}
	public void setFreightMode(String freightMode) {
		this.freightMode = freightMode;
	}
	public Double getFreightAmount() {
		return freightAmount;
	}
	public void setFreightAmount(Double freightAmount) {
		this.freightAmount = freightAmount;
	}
	public String getPopEventScdName() {
		return popEventScdName;
	}
	public void setPopEventScdName(String popEventScdName) {
		this.popEventScdName = popEventScdName;
	}
	public String getPopPolicyTag() {
		return popPolicyTag;
	}
	public void setPopPolicyTag(String popPolicyTag) {
		this.popPolicyTag = popPolicyTag;
	}
	public double getGiftallotAmount() {
		return giftallotAmount;
	}
	public void setGiftallotAmount(double giftallotAmount) {
		this.giftallotAmount = giftallotAmount;
	}	

	
}
