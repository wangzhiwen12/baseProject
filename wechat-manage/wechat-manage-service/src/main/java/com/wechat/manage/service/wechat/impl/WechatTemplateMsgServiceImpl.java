package com.wechat.manage.service.wechat.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wechat.manage.mapper.wechat.TemplateMessageLogMapper;
import com.wechat.manage.mapper.wechat.TemplateStoreRelMapper;
import com.wechat.manage.mapper.wechat.WechatTemplateMsgInfoMapper;
import com.wechat.manage.pojo.wechat.entity.TemplateMessageLog;
import com.wechat.manage.pojo.wechat.entity.TemplateStoreRel;
import com.wechat.manage.pojo.wechat.entity.WechatTemplateMsgInfo;
import com.wechat.manage.pojo.wechat.vo.WechatMessageDetailDto;
import com.wechat.manage.pojo.wechat.vo.WechatMessageDto;
import com.wechat.manage.pojo.wechat.vo.WechatTemplateMsgDto;
import com.wechat.manage.service.util.Common;
import com.wechat.manage.service.util.PropertiesUtils;
import com.wechat.manage.service.util.WechatTemplateMsgErrorEnum;
import com.wechat.manage.service.util.WechatUtil;
import com.wechat.manage.service.wechat.intf.WechatTemplateMsgService;
import com.wechat.manage.utils.HttpUtil;

import net.sf.json.JSONObject;

/**
 * 微信发送模版消息service实现类
 * 
 * @author Admin
 *
 */
@Service
public class WechatTemplateMsgServiceImpl implements WechatTemplateMsgService {
	private static final Logger log = LoggerFactory
			.getLogger(WechatTemplateMsgServiceImpl.class);

	@Autowired
	private WechatTemplateMsgInfoMapper templateMsgInfoMapper;
	@Autowired
	private WechatUtil wechatUtil;
	@Autowired
	private TemplateStoreRelMapper templateStoreRelMapper;
	@Autowired
	private TemplateMessageLogMapper messageLogMapper;

	/**
	 * 发送模版消息方法
	 */
	public JSONObject sendTemplateMsg(WechatTemplateMsgDto templateMsgDto) {
		JSONObject jsonObject = new JSONObject();
		TemplateMessageLog messageLog = new TemplateMessageLog();
		List<WechatMessageDto> messages = templateMsgDto.getMessages();
		for (WechatMessageDto messageeDto : messages) {
			WechatTemplateMsgInfo templateMsgInfo = templateMsgInfoMapper
					.selectTemplateMsgInfoBy(messageeDto.getMemberCode());
			if (templateMsgInfo == null) {
				log.info("不存在该会员，memberCode={}", messageeDto.getMemberCode());
			}
			String accessToken = wechatUtil.getAccessToken(
					templateMsgInfo.getAppid(), templateMsgInfo.getAppsecret());
			log.info("查询到的token为：{}", accessToken);
			if (Common.isEmpty(accessToken)) {
				log.info("token为空, memberCode为：{}, appid为： {}",
						messageeDto.getMemberCode(), templateMsgInfo.getAppid());
			}

			// 微信方的模版id
			String storeTemplateId = null;
			// 查看详情跳转的url
			String directUrl = null;
			//判断是否为外部消息
			if (Common.isEmpty(templateMsgDto.getWxTemplateId())) {
				TemplateStoreRel templateStoreRel = templateStoreRelMapper
						.selectTemplateStoreRelByStoreId(
								templateMsgInfo.getStoreCode(),
								templateMsgDto.getTemplateCode());
				if (templateStoreRel == null
						|| "0".equals(templateStoreRel.getOpenStatus())) {
					log.info("memberCode为：{}的用户所在的门店没有开通{}模版",
							messageeDto.getMemberCode(),
							templateMsgDto.getTemplateCode());
					// jsonObject.put("code",
					// WechatTemplateMsgErrorEnum.param_nuu_error.getCode());
					// jsonObject.put("msg",
					// WechatTemplateMsgErrorEnum.param_nuu_error.getMsg());
					// return jsonObject;
				}
				if (Common.isEmpty(templateStoreRel.getStoreTemplateId())) {
					log.info("memberCode为：{}的用户所在的门店{}模版未添加微信方的模版id",
							messageeDto.getMemberCode(),
							templateMsgDto.getTemplateCode());
				}
				storeTemplateId = templateStoreRel.getStoreTemplateId();
				directUrl = templateStoreRel.getDirectUrl();
			} else {
				storeTemplateId = templateMsgDto.getWxTemplateId();
				directUrl = templateMsgDto.getUrl();
			}
			log.info("查询到的微信模版id为：{}", storeTemplateId);

			// 拼接模版消息json串
			String templateMsg = getWechatTemplateMsgStr(messageeDto,
					accessToken, storeTemplateId, templateMsgInfo.getOpenid(),
					directUrl);
			log.info("memberCode:{}发送的模版消息为:{}", messageeDto.getMemberCode(),
					templateMsg);
			try {
				String result = HttpUtil.sendPostBuffer(
						PropertiesUtils.findPropertiesKey("sendTemplateMsgUrl")
								+ accessToken, templateMsg);
				log.info("memberCode:{}发送模版消息返回结果:{}",
						messageeDto.getMemberCode(), result);
				
				messageLog.setMessageContent(templateMsg);
				messageLog.setTouser(templateMsgInfo.getOpenid());
				messageLog.setAppid(templateMsgInfo.getAppid());
				messageLog.setSid(Long.valueOf(templateMsgInfo.getStoreCode()));
				messageLog.setCreateTime(new Timestamp(System.currentTimeMillis()));
				messageLog.setUpdateTime(new Timestamp(System.currentTimeMillis()));
				insertMessageLog(result, messageLog);
			} catch (ClientProtocolException e) {
				log.info("memberCode:{}发送的模版消息失败", messageeDto.getMemberCode());
				e.printStackTrace();
				log.error(e.toString(), e);
			} catch (IOException e) {
				log.info("memberCode:{}发送的模版消息失败", messageeDto.getMemberCode());
				e.printStackTrace();
				log.error(e.toString(), e);
			}
		}
		jsonObject.put("code", WechatTemplateMsgErrorEnum.SUCCESS.getCode());
		jsonObject.put("msg", WechatTemplateMsgErrorEnum.SUCCESS.getMsg());
		return jsonObject;
	}

	/**
	 * 获得消息模版串
	 * 
	 * @param messageDetailList
	 *            消息内容的list
	 * @param token
	 *            微信token
	 * @param TemplateId
	 *            微信的模版id
	 * @param openId
	 *            会员的openid
	 * @param url
	 *            点击查看详情跳转的url
	 * @return
	 */
	public String getWechatTemplateMsgStr(WechatMessageDto messageeDto,
			String token, String TemplateId, String openId, String url) {
		List<WechatMessageDetailDto> messageDetailList = messageeDto.getData();

		Map<String, String> map = new HashMap<String, String>();
		StringBuilder templateMsg = new StringBuilder();
		templateMsg.append("{\"touser\":\"");
		templateMsg.append(openId);
		templateMsg.append("\",\"template_id\":\"");
		templateMsg.append(TemplateId);
		templateMsg.append("\",\"url\":\"");
		templateMsg.append(url);
		templateMsg.append("\",\"data\":");
		for (int i = 0; i < messageDetailList.size(); i++) {
			WechatMessageDetailDto dto = messageDetailList.get(i);
			if (i == 0) {
				templateMsg
						.append("{\"first\": {\"value\":\"{firstValue}\",\"color\":\"{firstColor}\"},");
			} else if (i == (messageDetailList.size() - 1)) {
				templateMsg
						.append("\"remark\":{\"value\":\"{remarkValue}\",\"color\":\"{remarkColor}\"}");
			} else {
				templateMsg.append("\"keyword" + i + "\":{\"value\":\"{keynote"
						+ i + "Value}\",\"color\":\"{keynote" + i
						+ "Color}\"},");
			}

			if ("first".equals(dto.getName())) {
				map.put("{firstValue}", dto.getValue());
				map.put("{firstColor}", dto.getColor());
			} else if ("remark".equals(dto.getName())) {
				map.put("{remarkValue}", dto.getValue());
				map.put("{remarkColor}", dto.getColor());
			} else {
				for (int j = 1; j < (messageDetailList.size() - 1); j++) {
					if (("keyword" + j).equals(dto.getName())) {
						map.put("{keynote" + j + "Value}", dto.getValue());
						if (Common.isEmpty(dto.getColor())) {
							dto.setColor("#173177");
						}
						map.put("{keynote" + j + "Color}", dto.getColor());
					}
				}
			}

		}
		templateMsg.append("}}");

		return formatText(templateMsg.toString(), map);
	}

	/**
	 * 文本格式化 将文本里{param}的占位文字进行替换
	 * 
	 * @param text
	 *            待替换的文本
	 * @param params
	 *            替换的参数map
	 * @return
	 */
	public static String formatText(String text, Map<String, String> params) {
		for (Entry<String, String> param : params.entrySet()) {
			String key = param.getKey();
			text = text.replace(key, params.get(key));
		}
		return text;
	}
	
	/**
	 * 保存消息日志
	 * @param result
	 * @param messageLog
	 */
	private void insertMessageLog(String result, TemplateMessageLog messageLog) {
		JSONObject jsonObject = JSONObject.fromObject(result);
		messageLog.setWxResultCode(jsonObject.getString("errcode"));
		messageLog.setWxResultMsg(jsonObject.getString("errmsg"));
		if (jsonObject.containsKey("msgid")) {
			messageLog.setWxResultMsg(jsonObject.getString("msgid"));
		}
		messageLogMapper.insertLog(messageLog);
	}

}
