package com.wechat.manage.controller.wechat.member;

import com.wechat.manage.controller.index.BaseController;
import com.wechat.manage.pojo.wechat.vo.BoundCarePara;
import com.wechat.manage.pojo.system.vo.UserBaseInfoDto;
import com.wechat.manage.pojo.wechat.entity.WxpageBound;
import com.wechat.manage.service.wechat.intf.IWxpageBoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("boundController")
public class WxpageBoundController extends BaseController {
	@Autowired
	IWxpageBoundService boundService;

	@ResponseBody
	@RequestMapping("insertSelective")
	public String insertSelective(String wxHeadHtml, String wxHomeHtml) {
		WxpageBound entity = new WxpageBound();
		UserBaseInfoDto curUserInfo = getCurUserInfo();
		entity.setStoreCode(curUserInfo.getStoreCode());
		entity.setWxHeadHtml(wxHeadHtml);
		entity.setWxHomeHtml(wxHomeHtml);
		entity.setWxPageType(0);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("storeCode", curUserInfo.getStoreCode());
		paramMap.put("wxPageType", 0);
		WxpageBound info = boundService.getInfoByStroeCode(paramMap);
		if (info != null) {
			entity.setSid(info.getSid());
			return boundService.updateByPrimaryKeySelective(entity);
		} else {
			return boundService.insertSelective(entity);
		}

	}

	@ResponseBody
	@RequestMapping("getInfoByStroeCode")
	public WxpageBound getInfoByStroeCode() {
		UserBaseInfoDto curUserInfo = getCurUserInfo();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("storeCode", curUserInfo.getStoreCode());
		paramMap.put("wxPageType", 0);
		return boundService.getInfoByStroeCode(paramMap);
	}

	@ResponseBody
	@RequestMapping("getInfoByParam")
	public WxpageBound getInfoByParam(String stroeCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("storeCode", stroeCode);
		paramMap.put("wxPageType", 0);
		WxpageBound infoByStroeCode = boundService.getInfoByStroeCode(paramMap);
		if (infoByStroeCode != null) {
			return infoByStroeCode;
		}
		return null;
	}

	@ResponseBody
	@RequestMapping("boundCard")
	// 根据openId与storeCode判断是否有卡，1：存在实体卡，需提前解绑；2：存在电子卡，先解绑电子卡再绑定实体卡；3,：无卡直接绑定
	public String boundCard(BoundCarePara para) {
		String boundCard = boundService.boundCard(para);
		return boundCard;
	}

	// @ResponseBody
	// @RequestMapping("getInfoByStroeCode")
	public WxpageBound getInfoByStroeCode(String storeCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("storeCode", storeCode);
		paramMap.put("wxPageType", 0);
		return boundService.getInfoByStroeCode(paramMap);
	}
}
