package com.wechat.manage.pojo.wechat.vo;

import com.wechat.manage.pojo.system.entity.OrganizationInfo;

import java.util.List;

public class GroupResult {

	public String code;// 返回标志编码

	public String desc;// 返回信息描述

	public List<OrganizationInfo> obj;// 返回对象

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<OrganizationInfo> getObj() {
		return obj;
	}

	public void setObj(List<OrganizationInfo> obj) {
		this.obj = obj;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
