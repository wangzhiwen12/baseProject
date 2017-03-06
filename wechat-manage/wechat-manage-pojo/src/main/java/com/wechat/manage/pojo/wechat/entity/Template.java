package com.wechat.manage.pojo.wechat.entity;

import java.io.Serializable;
import java.util.List;

public class Template implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1920967786747612339L;
	private String sid;
	private String typeName;
	private List<TemplateStoreRel> template;
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
	public List<TemplateStoreRel> getTemplate() {
		return template;
	}
	public void setTemplate(List<TemplateStoreRel> template) {
		this.template = template;
	}
	
	
}
