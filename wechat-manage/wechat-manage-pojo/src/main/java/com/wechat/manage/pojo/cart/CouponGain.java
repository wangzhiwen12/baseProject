/*   
 * @(#)SalesCenterDao.java       2014-12-24 
 *   
 * 王府井集团拥有完全的版权   
 * 使用者必须经过许可   
 */  
package com.wechat.manage.pojo.cart;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**     
 *  商品行项目中的返利分摊
 *     
 * @author  xuc33 
 * @since   
 */
public class CouponGain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4612059902352681893L;
	
	@JSONField(name="coupon_account")
	private String couponAccount;

	//返券活动ID
	@JSONField(name="event_id")
	private long eventId;
	
	//返券策略ID
	@JSONField(name="policy_id")
	private long policyId;
	
	//01-积分账号/02-券账户/03-余额账户
	@JSONField(name="coupon_group")
	private String couponGroup;
	
	//返券批次  对应订单上的CouponBatch
	@JSONField(name="coupon_type")
	private String couponType;
	
	//返券类型 对应订单上的CouponType
	@JSONField(name="coupon_class")
	private String couponClass;
	
	//该商品分摊的返券券金额
	@JSONField(name="amount")
	private double amount;
	
	@JSONField(name="coupon_name")
	private String couponName;

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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCouponClass() {
		return couponClass;
	}

	public void setCouponClass(String couponClass) {
		this.couponClass = couponClass;
	}

	public String getCouponAccount() {
		return couponAccount;
	}

	public void setCouponAccount(String couponAccount) {
		this.couponAccount = couponAccount;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
}
