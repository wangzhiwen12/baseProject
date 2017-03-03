package com.wechat.manage.service.wechat.impl;

import com.wechat.manage.mapper.wechat.MemberInfoMapper;
import com.wechat.manage.mapper.wechat.MsgMassMapper;
import com.wechat.manage.mapper.wechat.MsgMemberMapper;
import com.wechat.manage.pojo.system.vo.UserBaseInfoDto;
import com.wechat.manage.pojo.wechat.entity.MemberInfo;
import com.wechat.manage.pojo.wechat.entity.MsgMass;
import com.wechat.manage.pojo.wechat.entity.MsgMember;
import com.wechat.manage.pojo.wechat.vo.MsgMassReDto;
import com.wechat.manage.service.util.WechatUtil;
import com.wechat.manage.service.wechat.intf.IMsgMassService;
import com.wechat.manage.utils.HttpUtils;
import com.wechat.manage.utils.JsonUtil;
import com.wechat.manage.vo.DataTableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MsgMassServiceImpl implements IMsgMassService {
	@Autowired
	private WechatUtil tokenUtil;
	@Autowired
	MemberInfoMapper memberMapper;
	@Autowired
	MsgMassMapper msgMassMapper;
	@Autowired
	MsgMemberMapper msgMemberMapper;

	public String msgMassSend(String content, String mediaId, String groups,
			UserBaseInfoDto curUserInfo, String msgType) {
		List<Long> memberSidList = new ArrayList<Long>();
		Map<String, Object> wechatMap = new HashMap<String, Object>();
		// 1根据groups判断0入会1没有2全部获取openId 2拼串发送 3存数据库
		Map<String, Object> paramMap = new HashMap<String, Object>();
		MsgMass msgMass = new MsgMass();
		msgMass.setStoreCode(curUserInfo.getStoreCode());
		paramMap.put("groups", groups);
		paramMap.put("storeCode", curUserInfo.getStoreCode());
		List<MemberInfo> memberList = memberMapper.getMemberListBy(paramMap);
		String[] str = new String[memberList.size()];
		int i = 0;
		for (MemberInfo memberInfo : memberList) {
			memberSidList.add(memberInfo.getSid());
			str[i] = memberInfo.getOpenid();
			i = i + 1;
		}
		wechatMap.put("touser", str);
		if (msgType.equals("news")) {
			wechatMap.put("send_ignore_reprint", 0);
			wechatMap.put("msgtype", "mpnews");
			Map<String, Object> mediaMap = new HashMap<String, Object>();
			mediaMap.put("media_id", mediaId);
			wechatMap.put("mpnews", mediaMap);
			msgMass.setMsgType("news");
			msgMass.setMediaId(mediaId);
		} else if (msgType.equals("text")) {
			Map<String, Object> textMap = new HashMap<String, Object>();
			textMap.put("content", content);
			wechatMap.put("text", textMap);
			wechatMap.put("msgtype", "text");
			msgMass.setMsgType("text");
			msgMass.setContent(content);
		} else if (msgType.equals("image")) {
			wechatMap.put("msgtype", "image");
			Map<String, Object> imageMap = new HashMap<String, Object>();
			imageMap.put("media_id", mediaId);
			wechatMap.put("image", imageMap);
			msgMass.setMsgType("image");
			msgMass.setMediaId(mediaId);
		}
		String access_token = tokenUtil.getAccessToken(curUserInfo.getAppId(),
				curUserInfo.getAppSecret());
		String sendUrl = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token="
				+ access_token;
		try {
			String reString = HttpUtils.doPost(sendUrl, JsonUtil.getJSONString(wechatMap));
			// String reString =
			// "{\"errcode\":0,\"errmsg\":\"\",\"msg_id\":192083}";
			MsgMassReDto media = JsonUtil.getJacksonDTO(reString, MsgMassReDto.class);
			if (!media.getErrcode().equals("0")) {
				return media.getErrmsg();
			} else {// 3
				msgMass.setMsgId(Integer.parseInt(media.getMsg_id()));
				msgMassMapper.insertSelective(msgMass);
				for (Long memberSid : memberSidList) {
					MsgMember record = new MsgMember();
					record.setMemberSid(memberSid);
					record.setMsgSid(msgMass.getSid());
					msgMemberMapper.insertSelective(record);
				}
			}
		} catch (Exception e) {
		}
		return "success";
	}

	/**
	 * 获取群发列表
	 */
	public DataTableResult<MsgMass> getMsgMass(Map<String, Object> paramMap) {
		DataTableResult<MsgMass> page = new DataTableResult<MsgMass>();
		List<MsgMass> replyList = msgMassMapper.selectPageListByParam(paramMap);
		page.setAaData(replyList);
		paramMap.put("start", null);
		paramMap.put("limit", null);
		Integer count = msgMassMapper.getCountByParam(paramMap);
		page.setiTotalRecords(count);
		return page;
	}

	public MsgMass selectByPrimaryKey(String msgSid) {
		return msgMassMapper.selectByPrimaryKey(Long.parseLong(msgSid));
	}
}
