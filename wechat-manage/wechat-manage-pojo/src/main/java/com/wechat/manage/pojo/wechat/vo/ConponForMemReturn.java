package com.wechat.manage.pojo.wechat.vo;

import java.util.List;

public class ConponForMemReturn {
	private String notUseNum  ;// 未使用
	private String used  ;// 已使用
	private String overdue ;// 已过期
	private List<CouponForMemExtendDto> coupons;

	public String getNotUseNum() {
		return notUseNum;
	}

	public void setNotUseNum(String notUseNum) {
		this.notUseNum = notUseNum;
	}

	public String getUsed() {
		return used;
	}

	public void setUsed(String used) {
		this.used = used;
	}

	public String getOverdue() {
		return overdue;
	}

	public void setOverdue(String overdue) {
		this.overdue = overdue;
	}

	public List<CouponForMemExtendDto> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<CouponForMemExtendDto> coupons) {
		this.coupons = coupons;
	}
}
