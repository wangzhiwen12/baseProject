package com.wechat.manage.controller.wechat;

import com.wechat.manage.controller.index.BaseController;
import com.wechat.manage.pojo.system.vo.UserBaseInfoDto;
import com.wechat.manage.pojo.wechat.entity.DataTableParams;
import com.wechat.manage.pojo.wechat.entity.DataTableResult;
import com.wechat.manage.pojo.wechat.entity.Material;
import com.wechat.manage.pojo.wechat.entity.MsgMass;
import com.wechat.manage.service.wechat.intf.IMsgMassService;
import com.wechat.manage.service.wechat.intf.MaterialLocalService;
import com.wechat.manage.utils.Common;
import com.wechat.manage.utils.PropertiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/msgMass")
public class MsgMassController extends BaseController {

	@Autowired
	private IMsgMassService massService;

	@Autowired
	private MaterialLocalService materialService;

	@RequestMapping("/massList")
	public String massList(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/wechat/msgReply/massList";
	}

	@RequestMapping("/add")
	public String add(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/wechat/msgReply/add";
	}

	@ResponseBody
	@RequestMapping("/msgMassSend")
	public String msgMassSend(String content, String mediaId, String groups, String msgType) {
		UserBaseInfoDto curUserInfo = getCurUserInfo();
		massService.msgMassSend(content, mediaId, groups, curUserInfo, msgType);
		return "success";
	}

	@ResponseBody
	@RequestMapping("/getMsgMass")
	public DataTableResult<MsgMass> getMsgMass(DataTableParams para) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		UserBaseInfoDto curUser = getCurUserInfo();
		paramMap.put("storeCode", curUser.getStoreCode());
		paramMap.put("start", para.getiDisplayStart());
		paramMap.put("limit", para.getiDisplayLength());
		DataTableResult<MsgMass> page = massService.getMsgMass(paramMap);
		page.setiTotalDisplayRecords(page.getiTotalRecords());
		page.setsEcho(para.getsEcho());
		return page;
	}

	@RequestMapping("/massInfo")
	public String massInfo(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/wechat/msgReply/massInfo";
	}

	@ResponseBody
	@RequestMapping("/getMsgMassInfo")
	public Map<String, Object> getMsgMassInfo(String msgSid) {// 获取消息详情
		UserBaseInfoDto curUserInfo = getCurUserInfo();
		String ftpPath = PropertiesUtils.findPropertiesKey("ftp.path");
		String ftpAddr = PropertiesUtils.findPropertiesKey("ftp.addr");
		MsgMass massInfo = massService.selectByPrimaryKey(msgSid);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("msgType", massInfo.getMsgType());
		if (massInfo.getMsgType().equals("text")) {// 文本
			paramMap.put("content", massInfo.getContent());
		} else {
			Material entity = new Material();
			entity.setMediaId(massInfo.getMediaId());
			entity.setStoreCode(curUserInfo.getStoreCode());
			List<Material> selectList = materialService.selectList(entity);
			Material material = selectList.get(0);
			paramMap.put("imgUrl",
					"http://" + ftpAddr + "/" + ftpPath + "/" + material.getLocalUrl());
			if (massInfo.getMsgType().equals("news")) {
				paramMap.put("title", material.getTitle());
			}
		}
		return paramMap;
	}

}
