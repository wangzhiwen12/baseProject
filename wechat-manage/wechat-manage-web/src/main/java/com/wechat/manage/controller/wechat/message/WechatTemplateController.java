package com.wechat.manage.controller.wechat.message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.wechat.manage.controller.index.BaseController;
import com.wechat.manage.service.wechat.intf.IWeChatTemplateService;
import com.wechat.manage.utils.Common;
import com.wechat.manage.utils.RedisUtil;



/**
 * 微信模板消息
 * 
 * @author Administrator
 * @date 2016年12月28日 上午10:42:07
 */
@Controller
@RequestMapping("/wechatTemplate")
public class WechatTemplateController extends BaseController {
	private Logger logger = Logger.getLogger(WechatTemplateController.class);

	@Autowired
	private IWeChatTemplateService iWeChatTemplateService;
	@Autowired
    private RedisUtil redisUtil;

	@RequestMapping("/list")
	public String listUI(Model model, Integer id) throws Exception {
		logger.info("访问list.jsp");
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/wechat/wechatTemplate/list";
	}
	
	
	/**
	 * 查询模板列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getWechatType")
	@ResponseBody
	public JSONObject selectWechatType(HttpServletRequest request, HttpServletResponse response) {
		Session session = SecurityUtils.getSubject().getSession();
		Object userId = session.getAttribute("userSessionId");
		String store = redisUtil.get(Common.USER_STORE_K+userId.toString(), null);
		JSONObject jsonObject = iWeChatTemplateService.selectWechatInfoType(store);
		return jsonObject;
	}
	
	/**
	 * 修改微信消息模板状态
	 * @param request
	 * @param response
	 */
	@RequestMapping("/updateTemplateState")
	@ResponseBody
	public void updateTemplateState(HttpServletRequest request, HttpServletResponse response) {
		try {
			Session session = SecurityUtils.getSubject().getSession();
			Object userId = session.getAttribute("userSessionId");
			String store = redisUtil.get(Common.USER_STORE_K+userId.toString(), null);
			String messageInfoSid = request.getParameter("infoSid");
			String status = request.getParameter("status");
			iWeChatTemplateService.updateTemplateState(status, store, messageInfoSid);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString(),e);
		}
	}
	
	/**
	 * 修改模板id
	 * @param request
	 * @param response
	 */
	@RequestMapping("/updateTemplateId")
	@ResponseBody
	public JSONObject updateTemplateId(HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		try {
			Session session = SecurityUtils.getSubject().getSession();
			Object userId = session.getAttribute("userSessionId");
			String store = redisUtil.get(Common.USER_STORE_K+userId.toString(), null); //获取当前登录进入的门店编码
			//String wxTemplateNo = request.getParameter("wxTemplateNo");//微信模板字典id（暂时不用）
			String storeTemplateId = request.getParameter("storeTemplateId");//微信模板id 各门店
			String infoSid = request.getParameter("infoSid");//微信模板信息表主键  门店表的message_info_sid字段
			iWeChatTemplateService.updateStoreTemplateId(storeTemplateId, store, infoSid);
			jsonObject.put("success", true);
			return jsonObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString(),e);
			jsonObject.put("success", false);
			return jsonObject;
		}
	}

}
