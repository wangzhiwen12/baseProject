package com.wechat.manage.pojo.cart;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 除外/追送付款结构
 * @author yfc
 *
 */
public class ExceptPay implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 0-除外付款方式/1-追送支付方式
	 */
	private String mode;
	/**
	 * 除外规则描述/追送规则描述
	 */
	private String describe;
	
	//支付折扣模式（2：折扣率  3：减金额）
	@JSONField(name="popmode")
	private String popMode;
		
	//支付折扣
	private double popzkl;
	/**
	 * 付款类型
	 */
	@JSONField(name="paytype")
	private String payType;	
	/**
	 * 付款代码
	 */
	@JSONField(name="paycode")
	private String payCode;
	/**
	 * 付款名称
	 */
	@JSONField(name="payname")
	private String payName;
	/**
	 * 券种
	 */
	@JSONField(name="coptype")
	private String copType;
	
	//活动ID（对应pop_details中pop_event_id）
	@JSONField(name="event_id")
	private long  eventId;
	
	//策略ID（对应pop_details中pop_policy_id）
	@JSONField(name="policy_id")
	private long policyId;
	
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	public String getPopMode() {
		return popMode;
	}
	public void setPopMode(String popMode) {
		this.popMode = popMode;
	}
	public double getPopzkl() {
		return popzkl;
	}
	public void setPopzkl(double popzkl) {
		this.popzkl = popzkl;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getPayCode() {
		return payCode;
	}
	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}
	public String getPayName() {
		return payName;
	}
	public void setPayName(String payName) {
		this.payName = payName;
	}
	public String getCopType() {
		return copType;
	}
	public void setCopType(String copType) {
		this.copType = copType;
	}
	public long getEventId() {
		return eventId;
	}
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
	public long getPolicyId() {
		return policyId;
	}
	public void setPolicyId(long policyId) {
		this.policyId = policyId;
	}	

	
}
