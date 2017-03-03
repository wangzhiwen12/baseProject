package com.wechat.manage.service.wechat.intf;

import com.wechat.manage.pojo.system.vo.UserBaseInfoDto;
import com.wechat.manage.pojo.wechat.entity.MsgMass;
import com.wechat.manage.vo.DataTableResult;

import java.util.Map;

public interface IMsgMassService {
	public String msgMassSend(String content, String mediaId, String groups,
							  UserBaseInfoDto curUserInfo, String msgType);

	/**
	 * 获取群发列表
	 */
	public DataTableResult<MsgMass> getMsgMass(Map<String, Object> paramMap);

	public MsgMass selectByPrimaryKey(String msgSid);
}
