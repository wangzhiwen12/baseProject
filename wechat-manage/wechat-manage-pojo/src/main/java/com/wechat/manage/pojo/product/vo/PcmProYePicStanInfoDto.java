package com.wechat.manage.pojo.product.vo;

public class PcmProYePicStanInfoDto {
	private String stanName;// 规格名称
	private String isSoldOut;// 是否售罄 0有货，1无货

	public String getStanName() {
		return stanName;
	}

	public void setStanName(String stanName) {
		this.stanName = stanName;
	}

	public String getIsSoldOut() {
		return isSoldOut;
	}

	public void setIsSoldOut(String isSoldOut) {
		this.isSoldOut = isSoldOut;
	}

	@Override
	public String toString() {
		return "PcmProYePicStanInfoDto [stanName=" + stanName + ", isSoldOut=" + isSoldOut + "]";
	}

}
