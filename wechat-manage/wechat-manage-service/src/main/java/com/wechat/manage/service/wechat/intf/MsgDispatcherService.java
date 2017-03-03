package com.wechat.manage.service.wechat.intf;


import com.wechat.manage.pojo.wechat.entity.MsgReply;

import java.util.List;
import java.util.Map;

public interface MsgDispatcherService {
	public String processMessage(Map<String, String> map);

	public String msgReplyText(String openid, String mpid, MsgReply msg);

	public String getReplyXml(String openid, String mpid, List<MsgReply> msgList);
}
