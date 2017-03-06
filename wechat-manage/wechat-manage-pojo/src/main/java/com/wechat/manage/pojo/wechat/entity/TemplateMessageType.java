package com.wechat.manage.pojo.wechat.entity;

import java.io.Serializable;
/**
 * 模板消息类型
 * @author Administrator
 * @date 2016年12月29日 上午10:23:13
 */
public class TemplateMessageType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2193652661257131486L;
	
	private String sid; 
	private String typeName; //模板消息的类型
	private Integer sort;  //排序的依据
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
	

}
