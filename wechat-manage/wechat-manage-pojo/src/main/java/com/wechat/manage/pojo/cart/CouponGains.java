package com.wechat.manage.pojo.cart;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class CouponGains implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	/**账号
	 * 
	 */
	private String account;
	
	/**
	 * 金额
	 */
	private double amount;

	/**
	 * 01-积分账号/02-券账户/03-余额账户
	 */
	@JSONField(name="coupon_group")
	private String couponGroup;
	
	@JSONField(name="coupon_class")
	private String couponClass;
	
	/**
	 * 种类
	 */
	@JSONField(name="coupon_name")
	private String couponName;
	
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	/**
	 * 券种
	 */
	@JSONField(name="coupon_type")
	private String couponType;
	
	/**
	 * 描述
	 */
	private String describe;
	
	/**
	 * 生效日期(YYYY-MM-DD HH:mm:SS)
	 */
	@JSONField(name="eff_date")
	private String effDate;
	
	/**
	 * 到期日期(YYYY-MM-DD HH:mm:SS)
	 */
	@JSONField(name="exp_date")
	private String  expDate;
	
	/**
	 * 付款面值(>0-有面值一次性使用/<=0-多次使用)
	 */
	@JSONField(name="face_value")
	private long faceValue;
	
	//返券活动ID
	@JSONField(name="gain_event_id")
	private long gainEventId;
	
	//返券策略ID
	@JSONField(name="gain_policy_id")
	private long  gainPolicyId;
	
	public String getCouponGroup() {
		return couponGroup;
	}
	public void setCouponGroup(String couponGroup) {
		this.couponGroup = couponGroup;
	}
	public String getCouponType() {
		return couponType;
	}
	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public long getFaceValue() {
		return faceValue;
	}
	public void setFaceValue(long faceValue) {
		this.faceValue = faceValue;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getEffDate() {
		return effDate;
	}
	public void setEffDate(String effDate) {
		this.effDate = effDate;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public String getCouponClass() {
		return couponClass;
	}
	public void setCouponClass(String couponClass) {
		this.couponClass = couponClass;
	}
	public long getGainEventId() {
		return gainEventId;
	}
	public void setGainEventId(long gainEventId) {
		this.gainEventId = gainEventId;
	}
	public long getGainPolicyId() {
		return gainPolicyId;
	}
	public void setGainPolicyId(long gainPolicyId) {
		this.gainPolicyId = gainPolicyId;
	}
	
}
