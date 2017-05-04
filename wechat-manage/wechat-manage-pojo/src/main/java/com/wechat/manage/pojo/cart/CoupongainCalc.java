package com.wechat.manage.pojo.cart;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class CoupongainCalc implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * JSON数组描述的返券/积分结果
	 */
	@JSONField(name="coupon_gain")
	private List<CouponGains> couponGain;
	/**
	 * 返回订单返券分摊数据
	 */
	@JSONField(name="bill_detail")
	private BillDetail billDetail;


	

	public List<CouponGains> getCouponGain() {
		return couponGain;
	}
	public void setCouponGain(List<CouponGains> couponGain) {
		this.couponGain = couponGain;
	}
	public BillDetail getBillDetail() {
		return billDetail;
	}
	public void setBillDetail(BillDetail billDetail) {
		this.billDetail = billDetail;
	}
	
}
