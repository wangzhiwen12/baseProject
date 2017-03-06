package com.wechat.manage.pojo.wechat.vo;

import java.io.Serializable;

/**
 * 微信推送消息模版
 * @author Admin
 *
 */
public class WechatMessageDetailDto implements Serializable{

	private static final long serialVersionUID = -5863781581978319976L;
	private String name;
	private String value;
	private String color;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
