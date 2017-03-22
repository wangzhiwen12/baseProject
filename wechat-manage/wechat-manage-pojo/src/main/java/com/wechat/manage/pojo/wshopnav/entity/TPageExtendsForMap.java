package com.wechat.manage.pojo.wshopnav.entity;


import java.sql.Timestamp;

import com.wechat.manage.utils.FormMap;


public class TPageExtendsForMap extends FormMap<String,Object> {
	
	 private String sid;
	 private String wpageTitle; //页面名称
	 private String pageLink; //页面链接',
	 private String pageCode; //页面二维码',
	 private String type; //1 微页面   2 分类页',
	 private String isHome; // '是否为主页。 1 是  2 否',
	 private String status; // '页面状态。 0 草稿  1 非草稿',
	 private String shopId; //店铺表的外键（t_shop）',
	 private String createUser; //用户表的外键',
	 private Timestamp createTime; //datetime NOT NULL,
	 private Timestamp updateTime;//datetime NOT NULL,
	 private Integer seqNo; //页面的序号',
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	
	public String getWpageTitle() {
		return wpageTitle;
	}
	public void setWpageTitle(String wpageTitle) {
		this.wpageTitle = wpageTitle;
	}
	public String getPageLink() {
		return pageLink;
	}
	public void setPageLink(String pageLink) {
		this.pageLink = pageLink;
	}
	public String getPageCode() {
		return pageCode;
	}
	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIsHome() {
		return isHome;
	}
	public void setIsHome(String isHome) {
		this.isHome = isHome;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
	 

}
