package com.wechat.manage.pojo.wechat.vo;

import com.wechat.manage.pojo.wechat.entity.DataTableParams;

public class CouponMemberDto extends DataTableParams {
	private String memberSid;// 会员ID

	private String useState;// 使用状态

	public String getMemberSid() {
		return memberSid;
	}

	public void setMemberSid(String memberSid) {
		this.memberSid = memberSid;
	}

	public String getUseState() {
		return useState;
	}

	public void setUseState(String useState) {
		this.useState = useState;
	}

}
