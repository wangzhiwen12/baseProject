package com.wechat.manage.pojo.wechat.vo;

public class MemberViewDto {
	private String openId;
	private String nickName;
	private String headImgUrl;
	private String mobile;
	private String count;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "MemberViewDto [openId=" + openId + ", nickName=" + nickName + ", headImgUrl="
				+ headImgUrl + ", mobile=" + mobile + ", count=" + count + "]";
	}

}
