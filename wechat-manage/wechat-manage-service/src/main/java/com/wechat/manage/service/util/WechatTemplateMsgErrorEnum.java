package com.wechat.manage.service.util;

public enum WechatTemplateMsgErrorEnum {

	SUCCESS("0", "OK"),
	PARAM_NULL_ERROR("1001", "微信推送传递参数为空"),
	TEMPLATE_CODE_NULL_ERROR("1002", "自定义模版id为空"),
	WX_TEMPLATE_CODE_NULL_ERROR("1003", "微信方模版id为空");

	private String code;
	private String msg;

	private WechatTemplateMsgErrorEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	private static String getMsg(String code) {
		for (WechatTemplateMsgErrorEnum errorEnum : WechatTemplateMsgErrorEnum.values()) {
			if (code.equals(errorEnum.getCode())) {
				return errorEnum.getMsg();
			}
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
