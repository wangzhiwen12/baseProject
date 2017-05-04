/*   
 * @(#)SalesCenterDao.java       2014-12-16 
 *   
 * 王府井集团拥有完全的版权   
 * 使用者必须经过许可   
 */  
package com.wechat.manage.pojo.cart;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**     
 *  用券分摊表
 *     
 * @author  xuc33 
 * @since   
 */
public class CouponUse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7270456776575577997L;

	//分摊付款行的唯一行号
	@JSONField(name="pay_rowno")
	private String payRowNo;
	
	//01-积分账号/02-券账户/03-余额账户/04-成长值
	@JSONField(name="coupon_group")
	private String couponGroup;
	
	//券种/积分种类
	@JSONField(name="coupon_type")
	private String  couponType;
	
	@JSONField(name="coupon_class")
	private String couponClass;
	
	//该商品分摊的收券金额
	@JSONField(name="amount")
	private String amount;
	
	//分摊原币金额
	@JSONField(name="oriamount")
	private String oriamount;

	//收券活动ID
	@JSONField(name="event_id")
	private String eventId;
	
	//收券规则ID
	@JSONField(name="policy_id")
	private String policyId;
	
	//对应分摊付款的付款代码
	@JSONField(name="pay_code")
	private String payCode;

	public String getPayRowNo() {
		return payRowNo;
	}

	public void setPayRowNo(String payRowNo) {
		this.payRowNo = payRowNo;
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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getCouponClass() {
		return couponClass;
	}

	public void setCouponClass(String couponClass) {
		this.couponClass = couponClass;
	}

	public String getOriamount() {
		return oriamount;
	}

	public void setOriamount(String oriamount) {
		this.oriamount = oriamount;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public String getPayCode() {
		return payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}
	
	
}
