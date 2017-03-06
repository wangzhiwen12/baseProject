package com.wechat.manage.service.wechat.intf;

import com.wechat.manage.pojo.wechat.vo.WechatTemplateMsgDto;

import net.sf.json.JSONObject;

/**
 * 微信模版消息service接口
 * @author Admin
 *
 */
public interface WechatTemplateMsgService {

	/**
	 * 发送模版消息
	 * @param templateMsgDto
	 * @return
	 */
	JSONObject sendTemplateMsg(WechatTemplateMsgDto templateMsgDto);

}
