package com.wechat.manage.pojo.wechat.vo;

public class CouponForMemDto  {
	private String cardtype;//券类型
	private String cardId;//券id
	private String title;//券名称
	private String cardStatus;//券状态
	private String endTime;//有效期
	private String collectionTime;//领取日期
//	@Override
//	public String toString() {
//		return "{cardtype="+cardtype+",cardId="+cardId+",title="+title+",cardStatus="+cardStatus+",endTime="+endTime+",collectionTime="+collectionTime+"}";
//	}
	public String getCardtype() {
		return cardtype;
	}

	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCollectionTime() {
		return collectionTime;
	}

	public void setCollectionTime(String collectionTime) {
		this.collectionTime = collectionTime;
	}



}
