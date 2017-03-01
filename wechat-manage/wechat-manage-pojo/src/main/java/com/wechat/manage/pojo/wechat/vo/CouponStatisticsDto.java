package com.wechat.manage.pojo.wechat.vo;

public class CouponStatisticsDto {
	private Long sid;
	private String cardType;
	private String title;
	private Double sendCount;
	private Double useCount;
	private Double usageRate;

	public Double getUsageRate() {
		return usageRate;
	}

	public void setUsageRate(Double usageRate) {
		this.usageRate = usageRate;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getSendCount() {
		return sendCount;
	}

	public void setSendCount(Double sendCount) {
		this.sendCount = sendCount;
	}

	public Double getUseCount() {
		return useCount;
	}

	public void setUseCount(Double useCount) {
		this.useCount = useCount;
	}

	@Override
	public String toString() {
		return "CouponStatisticsDto [sid=" + sid + ", cardType=" + cardType + ", title=" + title
				+ ", sendCount=" + sendCount + ", useCount=" + useCount + ", usageRate=" + usageRate
				+ "]";
	}

}
