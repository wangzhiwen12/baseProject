package com.wechat.manage.pojo.wechat.entity;

import java.io.Serializable;

/**
 * 微信模版消息实体类
 * 
 * @author Admin
 *
 */
public class WechatTemplateMsgInfo implements Serializable {

	private static final long serialVersionUID = -6369489710913726082L;

	private String appid;
	private String appsecret;
	private String storeCode; // 门店号
	private String memberCode; // 会员号
	private String openid; // 微信公众号会员的openid

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Override
	public String toString() {
		return "WecatTemplateMsgInfo [appid=" + appid + ", appsecret="
				+ appsecret + ", storeCode=" + storeCode + ", memberCode="
				+ memberCode + ", openid=" + openid + "]";
	}

}
