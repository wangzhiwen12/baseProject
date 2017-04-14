package com.wechat.manage.pojo.product.vo;

import java.util.ArrayList;
import java.util.List;

public class PcmProNewStanInfoDto {
	private String stanName;// 规格名称
	private List<PcmProNewColorInfoDto> colorNewList = new ArrayList<PcmProNewColorInfoDto>();// 色系列表

	public String getStanName() {
		return stanName;
	}

	public void setStanName(String stanName) {
		this.stanName = stanName;
	}

	public List<PcmProNewColorInfoDto> getColorNewList() {
		return colorNewList;
	}

	public void setColorNewList(List<PcmProNewColorInfoDto> colorNewList) {
		this.colorNewList = colorNewList;
	}

	@Override
	public String toString() {
		return "PcmProNewStanInfoDto [stanName=" + stanName + ", colorNewList=" + colorNewList
				+ "]";
	}

}
