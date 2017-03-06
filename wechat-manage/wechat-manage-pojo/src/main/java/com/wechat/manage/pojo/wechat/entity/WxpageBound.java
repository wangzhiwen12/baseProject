package com.wechat.manage.pojo.wechat.entity;

import java.util.Date;

public class WxpageBound {
	private Long sid;

	private String storeCode;

	private Integer wxPageType;

	private Date createTime;

	private String createUser;

	private Date updateTime;

	private String updateUser;

	private String wxHeadHtml;

	private String wxHomeHtml;

	public Integer getWxPageType() {
		return wxPageType;
	}

	public void setWxPageType(Integer wxPageType) {
		this.wxPageType = wxPageType;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getWxHeadHtml() {
		return wxHeadHtml;
	}

	public void setWxHeadHtml(String wxHeadHtml) {
		this.wxHeadHtml = wxHeadHtml;
	}

	public String getWxHomeHtml() {
		return wxHomeHtml;
	}

	public void setWxHomeHtml(String wxHomeHtml) {
		this.wxHomeHtml = wxHomeHtml;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Override
	public String toString() {
		return "WxpageBound [sid=" + sid + ", storeCode=" + storeCode + ", wxPageType="
				+ wxPageType + ", createTime=" + createTime + ", createUser=" + createUser
				+ ", updateTime=" + updateTime + ", updateUser=" + updateUser + ", wxHeadHtml="
				+ wxHeadHtml + ", wxHomeHtml=" + wxHomeHtml + "]";
	}

}