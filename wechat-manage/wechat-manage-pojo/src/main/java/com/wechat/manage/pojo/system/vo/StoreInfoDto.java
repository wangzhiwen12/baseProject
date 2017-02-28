package com.wechat.manage.pojo.system.vo;

public class StoreInfoDto {
	/**
	 * 门店编号
	 */
	private String storeCode;

	/**
	 * 门店名称
	 */
	private String storeName;

	/**
	 * appid
	 */
	private String appId;

	/**
	 * secret
	 */
	private String secret;

	/**
	 * isChannel
	 */
	private String isChannel;

	/**
	 * storeFlag 64位编码
	 */
	private String storeFlag;

	private String cardId;
	private String cardUrl;
	private String logoPic;
	private String logoWxPic;


	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getIsChannel() {
		return isChannel;
	}

	public void setIsChannel(String isChannel) {
		this.isChannel = isChannel;
	}

	public String getStoreFlag() {
		return storeFlag;
	}

	public void setStoreFlag(String storeFlag) {
		this.storeFlag = storeFlag;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getLogoWxPic() {
		return logoWxPic;
	}

	public void setLogoWxPic(String logoWxPic) {
		this.logoWxPic = logoWxPic;
	}

	public String getLogoPic() {
		return logoPic;
	}

	public void setLogoPic(String logoPic) {
		this.logoPic = logoPic;
	}

	public String getCardUrl() {
		return cardUrl;
	}

	public void setCardUrl(String cardUrl) {
		this.cardUrl = cardUrl;
	}

	@Override
	public String toString() {
		return "StoreInfoDto{" +
				"storeCode='" + storeCode + '\'' +
				", storeName='" + storeName + '\'' +
				", appId='" + appId + '\'' +
				", secret='" + secret + '\'' +
				", isChannel='" + isChannel + '\'' +
				", storeFlag='" + storeFlag + '\'' +
				", cardId='" + cardId + '\'' +
				", cardUrl='" + cardUrl + '\'' +
				", logoPic='" + logoPic + '\'' +
				", logoWxPic='" + logoWxPic + '\'' +
				'}';
	}
}
