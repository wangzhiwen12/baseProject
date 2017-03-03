package com.wechat.manage.pojo.fans.vo;

import java.util.List;

public class AddTagData {

	private List<String> openids;
	private List<String> groupids;
	private String oldgroups;
	
	public String getOldgroups() {
		return oldgroups;
	}
	public void setOldgroups(String oldgroups) {
		this.oldgroups = oldgroups;
	}
	public List<String> getOpenids() {
		return openids;
	}
	public void setOpenids(List<String> openids) {
		this.openids = openids;
	}
	public List<String> getGroupids() {
		return groupids;
	}
	public void setGroupids(List<String> groupids) {
		this.groupids = groupids;
	}
}
