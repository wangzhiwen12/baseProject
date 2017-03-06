package com.wechat.manage.pojo.wechat.vo;

import java.io.Serializable;
import java.util.List;

public class WechatTemplateMsgDto implements Serializable {

	private static final long serialVersionUID = -2010092842715197483L;

	private String templateCode;
	private String wxTemplateId;
	private String url;
	private List<WechatMessageDto> messages;

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<WechatMessageDto> getMessages() {
		return messages;
	}

	public void setMessages(List<WechatMessageDto> messages) {
		this.messages = messages;
	}

	public String getWxTemplateId() {
		return wxTemplateId;
	}

	public void setWxTemplateId(String wxTemplateId) {
		this.wxTemplateId = wxTemplateId;
	}

	@Override
	public String toString() {
		return "WechatTemplateMsgDto [templateCode=" + templateCode
				+ ", wxTemplateId=" + wxTemplateId + ", url=" + url
				+ ", messages=" + messages + "]";
	}

}
