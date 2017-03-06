package com.wechat.manage.pojo.wechat.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class TemplateMessageLog implements Serializable {

	private static final long serialVersionUID = 9158174386347706157L;

	private Long sid; // id
	private String messageContent; // 消息内容
	private String touser; // opendid
	private String appid; // appid
	private Long storeSid; // 门店表外键
	private Timestamp createTime; // 创建时间
	private Timestamp updateTime; // 修改时间
	private String wxResultCode; // 微信返回的编码 0 为正常，其他为不正常
	private String wxResultMsg; // 微信返回的描述
	private String wxMsgId; // 微信返回的发送消息id

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public Long getStoreSid() {
		return storeSid;
	}

	public void setStoreSid(Long storeSid) {
		this.storeSid = storeSid;
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

	public String getWxResultCode() {
		return wxResultCode;
	}

	public void setWxResultCode(String wxResultCode) {
		this.wxResultCode = wxResultCode;
	}

	public String getWxResultMsg() {
		return wxResultMsg;
	}

	public void setWxResultMsg(String wxResultMsg) {
		this.wxResultMsg = wxResultMsg;
	}

	public String getWxMsgId() {
		return wxMsgId;
	}

	public void setWxMsgId(String wxMsgId) {
		this.wxMsgId = wxMsgId;
	}

	@Override
	public String toString() {
		return "TemplateMessageLog [sid=" + sid + ", messageContent="
				+ messageContent + ", touser=" + touser + ", appid=" + appid
				+ ", storeSid=" + storeSid + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", wxResultCode="
				+ wxResultCode + ", wxResultMsg=" + wxResultMsg + ", wxMsgId="
				+ wxMsgId + "]";
	}

}
