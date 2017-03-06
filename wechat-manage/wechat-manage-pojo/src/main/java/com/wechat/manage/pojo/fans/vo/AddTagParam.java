package com.wechat.manage.pojo.fans.vo;

import java.util.List;

public class AddTagParam {
	private List<FansOpenidsParam> openids;
	private List<FansGroupidsParam> groupids;

	public List<FansOpenidsParam> getOpenids() {
		return openids;
	}

	public void setOpenids(List<FansOpenidsParam> openids) {
		this.openids = openids;
	}

	public List<FansGroupidsParam> getGroupids() {
		return groupids;
	}

	public void setGroupids(List<FansGroupidsParam> groupids) {
		this.groupids = groupids;
	}

}
