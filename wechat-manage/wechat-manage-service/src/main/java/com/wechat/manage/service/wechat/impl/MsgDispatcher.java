package com.wechat.manage.service.wechat.impl;


import com.wechat.manage.mapper.system.AppAccountInfoMapper;
import com.wechat.manage.mapper.wechat.MsgReplyMapper;
import com.wechat.manage.pojo.message.resp.*;
import com.wechat.manage.pojo.system.entity.AppAccountInfo;
import com.wechat.manage.pojo.wechat.entity.MsgReply;
import com.wechat.manage.service.util.MessageUtil;
import com.wechat.manage.service.wechat.intf.MsgDispatcherService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MsgDispatcher implements MsgDispatcherService {
	private static Logger logger = Logger.getLogger(MsgDispatcher.class);

	@Autowired
	private MsgReplyMapper msgReplyMapper;
	@Autowired
	AppAccountInfoMapper appMapper;

	public String processMessage(Map<String, String> map) {
		logger.info(map.toString());
		String openid = map.get("FromUserName"); // 用户 openid
		String mpid = map.get("ToUserName"); // 公众号原始 ID
		logger.info("openid" + openid + "mpid" + mpid);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("developerId", mpid);
		paramMap.put("delFlag", 0);
		List<AppAccountInfo> appList = appMapper.selectListByParam(paramMap);

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { // 文本消息
			// 普通文本消息
			MsgReply msg = new MsgReply();
			String content = map.get("Content");
			if (appList != null && appList.size() > 0) {
				String storeCode = appList.get(0).getStorecode();
				msg.setStoreCode(storeCode);
			}
			msg.setMsgKey(content);
			String msgReplyText = msgReplyText(openid, mpid, msg);
			if (msgReplyText == null) {
				msg.setMsgKey(null);
				msg.setEventType("disReply");
				msgReplyText = msgReplyText(openid, mpid, msg);
			}
			return msgReplyText;
		}

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) { // 图片消息
			logger.info("==============这是图片消息！");
		}

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) { // 链接消息
			logger.info("==============这是链接消息！");
		}

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) { // 位置消息
			logger.info("==============这是位置消息！");
		}

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) { // 视频消息
			logger.info("==============这是视频消息！");
		}

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) { // 语音消息
			logger.info("==============这是语音消息！");
		}
		return null;
	}

	public String msgReplyText(String openid, String mpid, MsgReply msg) {
		List<MsgReply> msgList = msgReplyMapper.selectListByParam(msg);
		if (msgList != null && msgList.size() > 0) {
			return getReplyXml(openid, mpid, msgList);
		} else {
			return null;
		}
	}

	public String getReplyXml(String openid, String mpid, List<MsgReply> msgList) {
		MsgReply msgReply = msgList.get(0);
		if (msgReply.getMsgType().equals(0)) {// (0文本,1图片,2音频,3视频,4音乐,5图文)
			TextMessage txtmsg = new TextMessage();
			txtmsg.setToUserName(openid);
			txtmsg.setFromUserName(mpid);
			txtmsg.setCreateTime(new Date().getTime());
			txtmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			txtmsg.setContent(msgReply.getContent());
			return MessageUtil.textMessageToXml(txtmsg);
		} else if (msgReply.getMsgType().equals(1)) {
			ImageMessage imgMsg = new ImageMessage();
			imgMsg.setToUserName(openid);
			imgMsg.setFromUserName(mpid);
			imgMsg.setCreateTime(new Date().getTime());
			Image image = new Image();
			image.setMediaId(msgReply.getMediaId());
			imgMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_IMAGE);
			imgMsg.setImage(image);
			return MessageUtil.imageMessageToXml(imgMsg);
		} else if (msgReply.getMsgType().equals(2)) {
			VoiceMessage voiMsg = new VoiceMessage();
			voiMsg.setToUserName(openid);
			voiMsg.setFromUserName(mpid);
			voiMsg.setCreateTime(new Date().getTime());
			Voice voice = new Voice();
			voice.setMediaId(msgReply.getMediaId());
			voiMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_VOICE);
			voiMsg.setVoice(voice);
			return MessageUtil.voiceMessageToXml(voiMsg);
		} else if (msgReply.getMsgType().equals(3)) {
			VideoMessage vidMsg = new VideoMessage();
			vidMsg.setToUserName(openid);
			vidMsg.setFromUserName(mpid);
			vidMsg.setCreateTime(new Date().getTime());
			Video video = new Video();
			video.setDescription(msgReply.getDescription());
			video.setMediaId(msgReply.getMediaId());
			video.setTitle(msgReply.getTitle());
			vidMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_VIDEO);
			vidMsg.setVideo(video);
			return MessageUtil.videoMessageToXml(vidMsg);
		} else if (msgReply.getMsgType().equals(4)) {
			MusicMessage musMsg = new MusicMessage();
			musMsg.setToUserName(openid);
			musMsg.setFromUserName(mpid);
			musMsg.setCreateTime(new Date().getTime());
			Music music = new Music();
			music.setDescription(msgReply.getDescription());
			music.setHQMusicUrl(msgReply.getHqmusicUrl());
			music.setMusicUrl(msgReply.getMusicUrl());
			music.setThumbMediaId(msgReply.getThumbMediald());
			music.setTitle(msgReply.getTitle());
			musMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_MUSIC);
			musMsg.setMusic(music);
			return MessageUtil.musicMessageToXml(musMsg);
		} else if (msgReply.getMsgType().equals(5)) {
			NewsMessage newsMsg = new NewsMessage();
			newsMsg.setToUserName(openid);
			newsMsg.setFromUserName(mpid);
			newsMsg.setCreateTime(new Date().getTime());
			List<Article> artList = new ArrayList<Article>();
			for (int i = 0; i < msgList.size(); i++) {
				Article art = new Article();
				art.setDescription(msgList.get(i).getDescription());
				art.setPicUrl(msgList.get(i).getPicUrl());
				art.setTitle(msgList.get(i).getTitle());
				art.setUrl(msgList.get(i).getUrl());
				artList.add(art);
			}
			newsMsg.setArticles(artList);
			newsMsg.setArticleCount(msgList.size());
			newsMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
			return MessageUtil.newsMessageToXml(newsMsg);
		}
		return null;
	}

}