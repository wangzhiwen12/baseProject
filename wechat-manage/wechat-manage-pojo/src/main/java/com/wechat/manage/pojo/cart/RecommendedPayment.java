/*   
 * @(#)SalesCenterDao.java       2014-12-8 
 *   
 * 王府井集团拥有完全的版权   
 * 使用者必须经过许可   
 */  
package com.wechat.manage.pojo.cart;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**     
 *  推荐支付    
 *     
 * @author  xuc33 
 * @since   
 */
public class RecommendedPayment implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8080423680907000721L;

	//描述
	private String describe;
	
	//支付折扣模式（2：折扣率  3：减金额）
	@JSONField(name="popmode")
	private String popMode;
			
	//支付折扣
	private double popzkl;
	
	//付款类型
	private String paytype;
	
	//付款代码
	private String paycode;
	
	private long  eventId;
	
	private long policyId;

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

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public String getPaycode() {
		return paycode;
	}

	public void setPaycode(String paycode) {
		this.paycode = paycode;
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
