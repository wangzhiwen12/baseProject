package com.wechat.manage.pojo.wechat.vo;

public class BoundCarePara {
	private Long cardSid;
	private String openId;
	private String storeCode;
	private String sjh;
	private String yzm;
	private String sr;
	private String hykh;
	private String pwd;
	private String sfzh;
	private String hykhTwo;

	public Long getCardSid() {
		return cardSid;
	}

	public void setCardSid(Long cardSid) {
		this.cardSid = cardSid;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getSjh() {
		return sjh;
	}

	public void setSjh(String sjh) {
		this.sjh = sjh;
	}

	public String getYzm() {
		return yzm;
	}

	public void setYzm(String yzm) {
		this.yzm = yzm;
	}

	public String getSr() {
		return sr;
	}

	public void setSr(String sr) {
		this.sr = sr;
	}

	public String getHykh() {
		return hykh;
	}

	public void setHykh(String hykh) {
		this.hykh = hykh;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getHykhTwo() {
		return hykhTwo;
	}

	public void setHykhTwo(String hykhTwo) {
		this.hykhTwo = hykhTwo;
	}

	@Override
	public String toString() {
		return "BoundCarePara [openId=" + openId + ", storeCode=" + storeCode + ", sjh=" + sjh
				+ ", yzm=" + yzm + ", sr=" + sr + ", hykh=" + hykh + ", pwd=" + pwd + ", sfzh="
				+ sfzh + ", hykhTwo=" + hykhTwo + "]";
	}

}
