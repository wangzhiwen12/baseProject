package com.wechat.manage.service.wechat.intf;


import com.wechat.manage.pojo.wechat.entity.MsgReply;
import com.wechat.manage.vo.DataTableResult;

import java.util.List;
import java.util.Map;

public interface MsgReplyService {
	public int msgReplyInsertOrUpdate(MsgReply msgReply);

	public List<MsgReply> getMsgReplyList(MsgReply msgReply);

	public int delMsgReply(int sid);

	public DataTableResult<MsgReply> getMsgReply(Map<String, Object> paramMap);
}
