package com.wechat.manage.controller.wechat.message;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wechat.manage.pojo.wechat.vo.WechatMessageDetailDto;
import com.wechat.manage.pojo.wechat.vo.WechatMessageDto;
import com.wechat.manage.pojo.wechat.vo.WechatTemplateMsgDto;
import com.wechat.manage.service.util.WechatTemplateMsgErrorEnum;
import com.wechat.manage.service.wechat.intf.WechatTemplateMsgService;
import com.wechat.manage.utils.Common;
import com.wechat.manage.utils.RequestAndResponse;

import net.sf.json.JSONObject;

/**
 * 发送模版消息的controller
 * @author Admin
 *
 */
@Controller
public class WechatMessageController {
	private static final Logger log = LoggerFactory.getLogger(WechatMessageController.class);

	@Autowired
	private WechatTemplateMsgService templateMsgService;
	
	@RequestMapping("api/templateMessage/sendByTempCode")
	@ResponseBody
	public JSONObject sendByTempCode(HttpServletRequest request, HttpServletResponse response){
		JSONObject jsonObject = new JSONObject();
		String requestData = null;
		try {
			requestData = RequestAndResponse.getData(request);
			log.info("微信推送模版消息参数: {}", requestData);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.toString(), e);
		}
		
		if (Common.isEmpty(requestData)) {
			jsonObject.put("code", WechatTemplateMsgErrorEnum.PARAM_NULL_ERROR.getCode());
			jsonObject.put("msg", WechatTemplateMsgErrorEnum.PARAM_NULL_ERROR.getMsg());
			log.info("微信推送模版消息结果：{}", jsonObject.toString());
			return jsonObject;
		}
		
		//解析传递过来的json参数  并封装
		WechatTemplateMsgDto templateMsgDto = jsonParse(requestData);
		if (Common.isEmpty(templateMsgDto.getTemplateCode())) {
			jsonObject.put("code", WechatTemplateMsgErrorEnum.TEMPLATE_CODE_NULL_ERROR.getCode());
			jsonObject.put("msg", WechatTemplateMsgErrorEnum.TEMPLATE_CODE_NULL_ERROR.getMsg());
			log.info("微信推送模版消息结果：{}", jsonObject.toString());
			return jsonObject;
		}
		jsonObject = templateMsgService.sendTemplateMsg(templateMsgDto);
		log.info("微信推送模版消息结果：{}", jsonObject.toString());
		return jsonObject;
	}
	
	@RequestMapping("api/templateMessage/sendByWxTempCode")
	@ResponseBody
	public JSONObject sendByWxTempCode(HttpServletRequest request, HttpServletResponse response){
		JSONObject jsonObject = new JSONObject();
		String requestData = null;
		try {
			requestData = RequestAndResponse.getData(request);
			log.info("对外微信推送模版消息参数: {}", requestData);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.toString(), e);
		}
		
		if (Common.isEmpty(requestData)) {
			jsonObject.put("code", WechatTemplateMsgErrorEnum.PARAM_NULL_ERROR.getCode());
			jsonObject.put("msg", WechatTemplateMsgErrorEnum.PARAM_NULL_ERROR.getMsg());
			log.info("对外微信推送模版消息结果：{}", jsonObject.toString());
			return jsonObject;
		}
		
		//解析传递过来的json参数  并封装
		WechatTemplateMsgDto templateMsgDto = jsonParse(requestData);
		if (Common.isEmpty(templateMsgDto.getTemplateCode())) {
			jsonObject.put("code", WechatTemplateMsgErrorEnum.TEMPLATE_CODE_NULL_ERROR.getCode());
			jsonObject.put("msg", WechatTemplateMsgErrorEnum.TEMPLATE_CODE_NULL_ERROR.getMsg());
			log.info("对外微信推送模版消息结果：{}", jsonObject.toString());
			return jsonObject;
		}
		templateMsgDto.setWxTemplateId(templateMsgDto.getTemplateCode());
		jsonObject = templateMsgService.sendTemplateMsg(templateMsgDto);
		return jsonObject;
	}
	
	public WechatTemplateMsgDto jsonParse(String json) {
		JSONObject jsonObject = JSONObject.fromObject(json);
		Map<String, Class> messageMap = new HashMap<String, Class>();
		messageMap.put("messages", WechatMessageDto.class);
		messageMap.put("data", WechatMessageDetailDto.class);
		return (WechatTemplateMsgDto) JSONObject.toBean(jsonObject, WechatTemplateMsgDto.class, messageMap);
	}
}
