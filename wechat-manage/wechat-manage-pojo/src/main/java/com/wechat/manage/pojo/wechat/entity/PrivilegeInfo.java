package com.wechat.manage.pojo.wechat.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 特权内容实体类
 * 
 * @author Admin
 *
 */
public class PrivilegeInfo implements Serializable {

	private static final long serialVersionUID = 4141941218616051143L;

	private String sid;
	private String headerStatus;
	private String privilegeContent;
	private Timestamp createTime;
	private Timestamp updateTime;
	private String storeCode;

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getHeaderStatus() {
		return headerStatus;
	}

	public void setHeaderStatus(String headerStatus) {
		this.headerStatus = headerStatus;
	}

	public String getPrivilegeContent() {
		return privilegeContent;
	}

	public void setPrivilegeContent(String privilegeContent) {
		this.privilegeContent = privilegeContent;
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

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	@Override
	public String toString() {
		return "PrivilegeInfo [sid=" + sid + ", headerStatus=" + headerStatus + ", privilegeContent=" + privilegeContent
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", storeCode=" + storeCode + "]";
	}

}
