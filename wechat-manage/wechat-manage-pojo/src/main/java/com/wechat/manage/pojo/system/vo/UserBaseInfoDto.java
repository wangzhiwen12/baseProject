package com.wechat.manage.pojo.system.vo;

/**
 * userinfo
 *
 * @author kongqf
 * @create 2016-12-08
 */
public class UserBaseInfoDto {
	private String userId;
	private String userName;
	private String storeCode;
	private String appId;
	private String appSecret;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	@Override
	public String toString() {
		return "UserBaseInfoDto [userId=" + userId + ", userName=" + userName + ", storeCode="
				+ storeCode + ", appId=" + appId + ", appSecret=" + appSecret + "]";
	}

}
