package com.wechat.manage.pojo.wechat.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 微信模版消息
 * 
 * @author Admin
 *
 */
public class WechatMessageDto implements Serializable {

	private static final long serialVersionUID = 978446699287008848L;
	private String memberCode;
	private List<WechatMessageDetailDto> data;

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public List<WechatMessageDetailDto> getData() {
		return data;
	}

	public void setData(List<WechatMessageDetailDto> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "WechatMessageDto [memberCode=" + memberCode + ", data=" + data
				+ "]";
	}

}
