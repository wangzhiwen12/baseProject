package com.wechat.manage.pojo.wechat.entity;

import java.util.Date;

public class CouponMember {
	private Long sid;

	private Long couponSid;

	private String memberSid;

	private Integer useState;

	private Date collectionTime;

	private Date useTime;

	private String couponCode;

	private String couponStartNo;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Long getCouponSid() {
		return couponSid;
	}

	public void setCouponSid(Long couponSid) {
		this.couponSid = couponSid;
	}

	public String getMemberSid() {
		return memberSid;
	}

	public void setMemberSid(String memberSid) {
		this.memberSid = memberSid;
	}

	public Integer getUseState() {
		return useState;
	}

	public void setUseState(Integer useState) {
		this.useState = useState;
	}

	public Date getCollectionTime() {
		return collectionTime;
	}

	public void setCollectionTime(Date collectionTime) {
		this.collectionTime = collectionTime;
	}

	public Date getUseTime() {
		return useTime;
	}

	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getCouponStartNo() {
		return couponStartNo;
	}

	public void setCouponStartNo(String couponStartNo) {
		this.couponStartNo = couponStartNo;
	}

	@Override
	public String toString() {
		return "CouponMember [sid=" + sid + ", couponSid=" + couponSid + ", memberSid=" + memberSid
				+ ", useState=" + useState + ", collectionTime=" + collectionTime + ", useTime="
				+ useTime + ", couponCode=" + couponCode + ", couponStartNo=" + couponStartNo + "]";
	}

}