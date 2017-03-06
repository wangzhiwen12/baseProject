package com.wechat.manage.pojo.usercenter.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 微商城data数据实体类
 * 
 * @author Admin
 *
 */
public class TUserCenter implements Serializable {

	private static final long serialVersionUID = 5213099500722109757L;

	private String sid;
	private String data; // data数据
	private Timestamp createTime; // 创建时间
	private Timestamp updateTime; // 修改时间
	private String storeCode; // 门店编码

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
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

}
