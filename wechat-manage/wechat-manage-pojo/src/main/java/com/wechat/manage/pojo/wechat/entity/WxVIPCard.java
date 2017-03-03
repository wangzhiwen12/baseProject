package com.wechat.manage.pojo.wechat.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 品牌会员卡实体类
 * 
 * @author Admin
 *
 */
public class WxVIPCard implements Serializable {

	private static final long serialVersionUID = -2737417556074928450L;
	
	private int sid;
	private String headName;
	private String headPictureUrl;
	private String wxHeadPictureUrl;
	private String brandCodeStatus;
	private String onlineCodeStatus;
	private Timestamp CreateTime;
	private Timestamp updateTime;
	private String storeCode;

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getHeadName() {
		return headName;
	}

	public void setHeadName(String headName) {
		this.headName = headName;
	}

	public String getHeadPictureUrl() {
		return headPictureUrl;
	}

	public void setHeadPictureUrl(String headPictureUrl) {
		this.headPictureUrl = headPictureUrl;
	}

	public String getWxHeadPictureUrl() {
		return wxHeadPictureUrl;
	}

	public void setWxHeadPictureUrl(String wxHeadPictureUrl) {
		this.wxHeadPictureUrl = wxHeadPictureUrl;
	}

	public String getBrandCodeStatus() {
		return brandCodeStatus;
	}

	public void setBrandCodeStatus(String brandCodeStatus) {
		this.brandCodeStatus = brandCodeStatus;
	}

	public String getOnlineCodeStatus() {
		return onlineCodeStatus;
	}

	public void setOnlineCodeStatus(String onlineCodeStatus) {
		this.onlineCodeStatus = onlineCodeStatus;
	}

	public Timestamp getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Timestamp createTime) {
		CreateTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	@Override
	public String toString() {
		return "WxVIPCard [sid=" + sid + ", headName=" + headName + ", headPictureUrl=" + headPictureUrl
				+ ", wxHeadPictureUrl=" + wxHeadPictureUrl + ", brandCodeStatus=" + brandCodeStatus
				+ ", onlineCodeStatus=" + onlineCodeStatus + ", CreateTime=" + CreateTime + ", updateTime=" + updateTime
				+ ", storeCode=" + storeCode + "]";
	}

}
