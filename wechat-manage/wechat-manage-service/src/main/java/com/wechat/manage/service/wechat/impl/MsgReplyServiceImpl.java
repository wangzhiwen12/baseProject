package com.wechat.manage.service.wechat.impl;


import com.wechat.manage.mapper.wechat.MaterialMapper;
import com.wechat.manage.mapper.wechat.MediaLocalUrlMapper;
import com.wechat.manage.mapper.wechat.MsgReplyMapper;
import com.wechat.manage.pojo.wechat.entity.Material;
import com.wechat.manage.pojo.wechat.entity.MediaLocalUrl;
import com.wechat.manage.pojo.wechat.entity.MsgReply;
import com.wechat.manage.service.wechat.intf.MsgReplyService;
import com.wechat.manage.vo.DataTableResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MsgReplyServiceImpl implements MsgReplyService {
	private static Logger logger = Logger.getLogger(MsgReplyServiceImpl.class);
	@Autowired
	private MsgReplyMapper msgReplyMapper;
	@Autowired
	private MediaLocalUrlMapper localUrlMapper;
	@Autowired
	private MaterialMapper materialMapper;

	public int msgReplyInsertOrUpdate(MsgReply msgReply) {
		logger.info("start-msgReplyInsertOrUpdate");
		if (msgReply.getMediaId() != null) {
			Material material = new Material();
			material.setMediaId(msgReply.getMediaId());
			List<Material> materList = materialMapper.selectListByParam(material);
			if (materList != null && materList.size() > 0) {
				msgReply.setPicLocalUrl(materList.get(0).getLocalUrl());
				if (msgReply.getMsgType() == 1) {// image
					msgReply.setPicUrl(materList.get(0).getPicUrl());
				} else if (msgReply.getMsgType() == 5) {// news
					Material news = materList.get(0);
					msgReply.setTitle(news.getTitle());
					msgReply.setPicUrl(news.getThumbUrl());
					msgReply.setUrl(news.getPicUrl());
					msgReply.setDescription(news.getDigest());
					msgReply.setArticleCount("1");
				}
			}
		}
		MsgReply entity = new MsgReply();
		entity.setMsgKey(msgReply.getMsgKey());
		entity.setEventType(msgReply.getEventType());
		entity.setStoreCode(msgReply.getStoreCode());
		List<MsgReply> msgReplyList = msgReplyMapper.selectListByParam(entity);
		if (msgReplyList != null && msgReplyList.size() > 0) {
			msgReply.setSid(msgReplyList.get(0).getSid());
			return msgReplyMapper.updateByPrimaryKeySelective(msgReply);
		} else {
			return msgReplyMapper.insertSelective(msgReply);
		}
	}

	public int msgReplyInsertOrUpdate1(MsgReply msgReply) {
		MediaLocalUrl url = new MediaLocalUrl();
		url.setMediaId(msgReply.getMediaId());
		url.setStoreCode(msgReply.getStoreCode());
		List<MediaLocalUrl> urlList = localUrlMapper.selectListByParam(url);
		msgReply.setPicUrl(urlList.get(0).getLocalUrl());
		logger.info("start-msgReplyInsertOrUpdate");
		MsgReply entity = new MsgReply();
		entity.setMsgKey(msgReply.getMsgKey());
		entity.setEventType(msgReply.getEventType());
		entity.setStoreCode(msgReply.getStoreCode());
		List<MsgReply> msgReplyList = msgReplyMapper.selectListByParam(entity);
		if (msgReplyList != null && msgReplyList.size() > 0) {
			msgReply.setSid(msgReplyList.get(0).getSid());
			return msgReplyMapper.updateByPrimaryKeySelective(msgReply);
		} else {
			// String[] split = msgReply.getUrl().split("\\?");
			// msgReply.setUrl(split[0]);
			return msgReplyMapper.insertSelective(msgReply);
		}
	}

	/**
	 * 获取自定义规则回复
	 */
	public DataTableResult<MsgReply> getMsgReply(Map<String, Object> paramMap) {
		DataTableResult<MsgReply> page = new DataTableResult<MsgReply>();
		List<MsgReply> replyList = msgReplyMapper.selectPageListByParam(paramMap);
		page.setAaData(replyList);
		paramMap.put("start", null);
		paramMap.put("limit", null);
		Integer count = msgReplyMapper.getCountByParam(paramMap);
		page.setiTotalRecords(count);
		return page;
	}

	public List<MsgReply> getMsgReplyList(MsgReply msgReply) {
		return msgReplyMapper.selectListByParam(msgReply);
	}

	public int delMsgReply(int sid) {
		return msgReplyMapper.deleteByPrimaryKey(sid);
	}
}
