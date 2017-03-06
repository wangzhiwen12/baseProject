package com.wechat.manage.pojo.wechat.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 门店模板
 * 
 * @author Administrator
 * @date 2016年12月29日 上午10:26:31
 */
public class TemplateStoreRel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9014559754266768131L;
	private String sid;
	private String storeSid; // 门店表的外键(store_info)
	private String messageInfoSid; // 模板信息表的外键（template_message_info）
	private String openStatus; // 开启状态 1 开启 0关闭
	private String storeTemplateId;// 各门店公众号生成的模板id
	private Timestamp createTime; // 创建时间
	private Timestamp updateTime; // 更新时间
	private String directUrl; // 跳转的url
	private Integer messageTypeId; // 模板类型的外键
	private String typeName; // 模板消息的类型
	private String templateContent; // 模板内容
	private String templateCode; // 自定义模板编码
	private String templateName; // 自定义模板名
	private String wxTemplateName; // 微信模板名称
	private String wxTemplateNo; // 微信模板字典编号
	private String infoSid; // 模板信息表主键

	public String getInfoSid() {
		return infoSid;
	}

	public void setInfoSid(String infoSid) {
		this.infoSid = infoSid;
	}

	public String getWxTemplateNo() {
		return wxTemplateNo;
	}

	public void setWxTemplateNo(String wxTemplateNo) {
		this.wxTemplateNo = wxTemplateNo;
	}

	public String getTemplateContent() {
		return templateContent;
	}

	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getWxTemplateName() {
		return wxTemplateName;
	}

	public void setWxTemplateName(String wxTemplateName) {
		this.wxTemplateName = wxTemplateName;
	}

	public Integer getMessageTypeId() {
		return messageTypeId;
	}

	public void setMessageTypeId(Integer messageTypeId) {
		this.messageTypeId = messageTypeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getStoreSid() {
		return storeSid;
	}

	public void setStoreSid(String storeSid) {
		this.storeSid = storeSid;
	}

	public String getMessageInfoSid() {
		return messageInfoSid;
	}

	public void setMessageInfoSid(String messageInfoSid) {
		this.messageInfoSid = messageInfoSid;
	}

	public String getOpenStatus() {
		return openStatus;
	}

	public void setOpenStatus(String openStatus) {
		this.openStatus = openStatus;
	}

	public String getStoreTemplateId() {
		return storeTemplateId;
	}

	public void setStoreTemplateId(String storeTemplateId) {
		this.storeTemplateId = storeTemplateId;
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

	public String getDirectUrl() {
		return directUrl;
	}

	public void setDirectUrl(String directUrl) {
		this.directUrl = directUrl;
	}

}
