package com.wechat.manage.service.wechat.impl;


import com.wechat.manage.mapper.system.AppAccountInfoMapper;
import com.wechat.manage.pojo.system.entity.AppAccountInfo;
import com.wechat.manage.pojo.wechat.entity.MsgReply;
import com.wechat.manage.pojo.wechat.entity.MemberInfo;
import com.wechat.manage.service.util.MessageUtil;
import com.wechat.manage.service.util.WechatUtil;
import com.wechat.manage.utils.StringUtils;
import com.wechat.manage.service.wechat.intf.MemberInfoService;
import com.wechat.manage.service.wechat.intf.MsgDispatcherService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EventDispatcher {
	private static Logger logger = Logger.getLogger(EventDispatcher.class);

	@Autowired
	MsgDispatcherService msgDispatcher;
	@Autowired
	AppAccountInfoMapper appMapper;

	@Autowired
	MemberInfoService memberInfoService;

	@Autowired
	WechatUtil wechatUtil;

	public String processEvent(Map<String, String> map) {
		String para = null;
		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) { // 关注事件
			// msgDispatcher.msgReplyText(openid, mpid, msg);
			logger.info("-------------------" + map);
			logger.info("==============这是关注事件！");
			String openid = map.get("FromUserName"); // 用户 openid
			String mpid = map.get("ToUserName"); // 公众号原始 ID
			MsgReply msgRelpy = new MsgReply();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("developerId", mpid);
			paramMap.put("delFlag", 0);
			List<AppAccountInfo> appList = appMapper.selectListByParam(paramMap);
			if (appList != null && appList.size() > 0) {
				String storeCode = appList.get(0).getStorecode();
				msgRelpy.setStoreCode(storeCode);
				try {
					MemberInfo memberInfo = new MemberInfo();
					com.wechat.manage.pojo.wechat.vo.MemberInfo openid_userinfo = wechatUtil.Openid_userinfo(openid,
							appList.get(0).getAppid(), appList.get(0).getAppsecret());
					memberInfo.setOpenid(openid);
					memberInfo.setStoreCode(storeCode);
					memberInfo.setSubscribe(openid_userinfo.getSubscribe());
					memberInfo.setNickname(openid_userinfo.getNickname());
					memberInfo.setSex(openid_userinfo.getSex());
					memberInfo.setCity(openid_userinfo.getCity());
					memberInfo.setCountry(openid_userinfo.getCountry());
					memberInfo.setProvince(openid_userinfo.getProvince());
					memberInfo.setLanguage(openid_userinfo.getLanguage());
					memberInfo.setHeadimgurl(openid_userinfo.getHeadimgurl());
					memberInfo.setSubscribeTime(openid_userinfo.getSubscribe_time());
					memberInfo.setRemark(openid_userinfo.getRemark());
					memberInfo.setGroupid(openid_userinfo.getGroupid());
					String openid_userinfoUnionid = openid_userinfo.getUnionid();
					if (StringUtils.isNotEmpty(openid_userinfoUnionid)) {
						memberInfo.setUnionid(openid_userinfoUnionid);
					}
					String openid_userinfoOpenid = openid_userinfo.getOpenid();
					if (StringUtils.isNotEmpty(openid_userinfoOpenid)) {
						memberInfo.setOpenid(openid_userinfoOpenid);
					}
					memberInfoService.memberInfoInit(memberInfo);
				} catch (Exception e) {
					logger.error("失败");
				}

			}
			msgRelpy.setEventType("subscribe");
			para = msgDispatcher.msgReplyText(openid, mpid, msgRelpy);
		}

		if (map.get("Event").equals(MessageUtil.USER_GET_CARD)) {// 推送事件
			logger.info("==============这是推送事件！");
			// 1 获取门店信息2 获取用户SID 3 update
			String openid = map.get("FromUserName"); // 用户 openid
			String mpid = map.get("ToUserName"); // 公众号原始 ID
			String code = map.get("UserCardCode");//
			MemberInfo memberInfo = new MemberInfo();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("developerId", mpid);
			paramMap.put("delFlag", 0);
			List<AppAccountInfo> appList = appMapper.selectListByParam(paramMap);
			logger.info("====appList====" + appList);
			if (appList != null && appList.size() > 0) {
				String storeCode = appList.get(0).getStorecode();
				memberInfo.setStoreCode(storeCode);
				memberInfo.setOpenid(openid);
				List<MemberInfo> infoList = memberInfoService.selectListByParam(memberInfo);
				logger.info("====infoList====" + infoList);
				if (infoList != null && infoList.size() > 0) {
					memberInfo.setCodeId(code + ";" + appList.get(0).getCardId());
					memberInfo.setSid(infoList.get(0).getSid());
					logger.info("====memberInfo====" + memberInfo);
					int u = memberInfoService.updateByPrimaryKeySelective(memberInfo);
					logger.info("====memberInfo + u====" + u);
				}
			}
		}

		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) { // 取消关注事件
			logger.info("==============这是取消关注事件！");
		}

		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SCAN)) { // 扫描二维码事件
			logger.info("==============这是扫描二维码事件！");
		}

		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_LOCATION)) { // 位置上报事件
			logger.info("==============这是位置上报事件！");
		}

		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_CLICK)) { // 自定义菜单点击事件
			logger.info("==============这是自定义菜单点击事件！");
		}

		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_VIEW)) { // 自定义菜单
																	// View事件
			logger.info("==============这是自定义菜单 View 事件！");
		}
		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_CLICK)) { // 自定义菜单点击事件
			logger.info("==============这是自定义菜单 CLICK 事件！");
			String openid = map.get("FromUserName"); // 用户 openid
			String mpid = map.get("ToUserName"); // 公众号原始 ID
			String msgKey = map.get("EventKey");
			MsgReply msgRelpy = new MsgReply();
			msgRelpy.setMsgKey(msgKey);
			para = msgDispatcher.msgReplyText(openid, mpid, msgRelpy);
		}

		return para;
	}
}