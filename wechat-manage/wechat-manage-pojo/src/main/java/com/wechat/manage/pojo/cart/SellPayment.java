/*   
 * @(#)SalesCenterDao.java       2014-11-6 
 *   
 * 王府井集团拥有完全的版权   
 * 使用者必须经过许可   
 */  
package com.wechat.manage.pojo.cart;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**     
 *  付款明细  
 *     
 * @author  xuc33 
 * @since   JDK1.7
 */
public class SellPayment implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JSONField(name="already_allot")
	private boolean alreadyAllot;//是否已分摊

	@JSONField(name="rowno")
	private int	rowNo;//付款唯一行号
	
	private String	flag	;//0-促销预付款(满抵/买换)/1-除外付款/2-普通付款
	
	@JSONField(name="paytype")
	private String	payType	;//现金类/券/积分/…
	
	@JSONField(name="paycode")
	private String	payCode	;//支付编码
	
	@JSONField(name="payname")
	private String	payName	;//支付名称
	
	@JSONField(name="coptype")
	private String	copType	;//券种/积分种类
	
	@JSONField(name="payno")
	private String	payNo	;//支付卡号/券号/..
	
	@JSONField(name="payMmemo")
	private String	payMemo	;//支付备注
	
	private Double	rate	;//费率
	
	private Double	amount	;//券值或积分
	
	private Double	money	;//amount * rate,兑换后金额
	
	private Double	overage	;//sum(money - overage) = fact_pay
	
	private Double overpay ;//结余
	
	@JSONField(name="consumers_id")
	private String	consumersId;//	用于支付券或积分的会员账号
	
	@JSONField(name="coupon_group")
	private String	couponGroup;//	用券类型(couponuse.get服务返回)
	
	@JSONField(name="coupon_event_scd")
	private Long couponEventScd;//	用券档期ID(couponuse.get服务返回)
	
	@JSONField(name="coupon_event_id")
	private Long couponEventId;//	用券活动ID(couponuse.get服务返回)
	
	@JSONField(name="coupon_policy_id")
	private Long couponPolicyId;//	用券策略ID(couponuse.get服务返回)
	
	@JSONField(name="coupon_mutex")
	private List<String> couponMutex;//	券互斥规则(couponuse.get服务返回)
	
	@JSONField(name="coupon_trace_seqno")
	private Long couponTraceSeqno;//	券交易流水号(券锁定支付后返回)
	
	@JSONField(name="coupon_balance")
	private Double couponBalance;//	券支付后账号余额(券锁定支付后返回)
	
	@JSONField(name="coupon_pay_token")
	private String couponPayToken;//账户支付密码验证TOKEN
	
	@JSONField(name="coupon_is_cash")
	private String couponIsCash;

	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	public String getPayNo() {
		return payNo;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}

	public String getPayMemo() {
		return payMemo;
	}

	public void setPayMemo(String payMemo) {
		this.payMemo = payMemo;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Double getOverage() {
		return overage;
	}

	public void setOverage(Double overage) {
		this.overage = overage;
	}

	public String getConsumersId() {
		return consumersId;
	}

	public void setConsumersId(String consumersId) {
		this.consumersId = consumersId;
	}

	public String getCouponGroup() {
		return couponGroup;
	}

	public void setCouponGroup(String couponGroup) {
		this.couponGroup = couponGroup;
	}

	public Long getCouponEventScd() {
		return couponEventScd;
	}

	public void setCouponEventScd(Long couponEventScd) {
		this.couponEventScd = couponEventScd;
	}

	public Long getCouponEventId() {
		return couponEventId;
	}

	public void setCouponEventId(Long couponEventId) {
		this.couponEventId = couponEventId;
	}

	public Long getCouponPolicyId() {
		return couponPolicyId;
	}

	public void setCouponPolicyId(Long couponPolicyId) {
		this.couponPolicyId = couponPolicyId;
	}






	public List<String> getCouponMutex() {
		return couponMutex;
	}

	public void setCouponMutex(List<String> couponMutex) {
		this.couponMutex = couponMutex;
	}

	public Long getCouponTraceSeqno() {
		return couponTraceSeqno;
	}

	public void setCouponTraceSeqno(Long couponTraceSeqno) {
		this.couponTraceSeqno = couponTraceSeqno;
	}

	public Double getCouponBalance() {
		return couponBalance;
	}

	public void setCouponBalance(Double couponBalance) {
		this.couponBalance = couponBalance;
	}

	public String getCouponPayToken() {
		return couponPayToken;
	}

	public void setCouponPayToken(String couponPayToken) {
		this.couponPayToken = couponPayToken;
	}

	public boolean isAlreadyAllot() {
		return alreadyAllot;
	}

	public void setAlreadyAllot(boolean alreadyAllot) {
		this.alreadyAllot = alreadyAllot;
	}

	public Double getOverpay() {
		return overpay;
	}

	public void setOverpay(Double overpay) {
		this.overpay = overpay;
	}

	public String getCouponIsCash() {
		return couponIsCash;
	}

	public void setCouponIsCash(String couponIsCash) {
		this.couponIsCash = couponIsCash;
	}
	
}
