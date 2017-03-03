package com.wechat.manage.controller.wechat;

import com.wechat.manage.controller.index.BaseController;
import com.wechat.manage.pojo.wechat.vo.MsgReplyDto;
import com.wechat.manage.pojo.system.vo.UserBaseInfoDto;
import com.wechat.manage.vo.DataTableParams;
import com.wechat.manage.vo.DataTableResult;
import com.wechat.manage.pojo.wechat.entity.MsgReply;
import com.wechat.manage.service.wechat.intf.MsgReplyService;
import com.wechat.manage.utils.Common;
import com.wechat.manage.utils.PropertiesUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息管理模块
 * 
 * @Class Name MsgReplyController
 * @Author yedong
 * @Create In 2016年11月28日
 */
@Controller
@RequestMapping(value = "/msgReply")
public class MsgReplyController extends BaseController {

	@Autowired
	private MsgReplyService msgReplyService;

	@ResponseBody
	@RequestMapping("/getMsgReply")
	public DataTableResult<MsgReply> getMsgReply(DataTableParams para) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		UserBaseInfoDto curUser = getCurUserInfo();
		paramMap.put("storeCode", curUser.getStoreCode());
		paramMap.put("eventType", "ruleKey");
		paramMap.put("start", para.getiDisplayStart());
		paramMap.put("limit", para.getiDisplayLength());
		DataTableResult<MsgReply> page = msgReplyService.getMsgReply(paramMap);
		page.setiTotalDisplayRecords(page.getiTotalRecords());
		page.setsEcho(para.getsEcho());
		return page;
	}

	@ResponseBody
	@RequestMapping(value = "/msgReplyInsertOrUpdate", method = { RequestMethod.POST })
	public String msgReplyInsertOrUpdate(MsgReplyDto dto) {
		UserBaseInfoDto curUser = getCurUserInfo();
		dto.setStoreCode(curUser.getStoreCode());
		MsgReply msgReply = new MsgReply();
		try {
			BeanUtils.copyProperties(msgReply, dto);
			if (dto.getMsgType().equals("text")) {
				msgReply.setMsgType(0);
			} else if (dto.getMsgType().equals("image")) {
				msgReply.setMsgType(1);
			} else if (dto.getMsgType().equals("news")) {
				msgReply.setMsgType(5);
			}
			int iou = msgReplyService.msgReplyInsertOrUpdate(msgReply);
			if (iou == 1) {
				return "success";
			} else {
				return "faile";
			}
		} catch (Exception e) {
			return "faile";
		}
	}

	@RequestMapping("list")
	public String listUI(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/wechat/msgReply/list";
	}

	public List<MsgReply> getMsgReplyList(MsgReply msgReply) {
		return msgReplyService.getMsgReplyList(msgReply);
	}

	@ResponseBody
	@RequestMapping("initMsgReply")
	public List<MsgReply> initMsgReply(String eventType) {
		MsgReply entity = new MsgReply();
		UserBaseInfoDto curUserInfo = getCurUserInfo();
		entity.setStoreCode(curUserInfo.getStoreCode());
		if (eventType != null) {
			entity.setEventType(eventType);
		}
		List<MsgReply> msgReplyList = getMsgReplyList(entity);
		String ftpPath = PropertiesUtils.findPropertiesKey("ftp.path");
		String ftpAddr = PropertiesUtils.findPropertiesKey("ftp.addr");
		for (MsgReply msgReply : msgReplyList) {
			msgReply.setPicLocalUrl(
					"http://" + ftpAddr + "/" + ftpPath + "/" + msgReply.getPicLocalUrl());
		}
		return msgReplyList;
	}

	@ResponseBody
	@RequestMapping("del")
	public String delMsgReply(String sid) {
		int del = msgReplyService.delMsgReply(Integer.parseInt(sid));
		if (del == 1) {
			return "success";
		} else {
			return "faile";
		}
	}
}
