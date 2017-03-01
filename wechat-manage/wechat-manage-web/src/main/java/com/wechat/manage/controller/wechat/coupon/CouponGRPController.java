package com.wechat.manage.controller.wechat.coupon;

import com.wechat.manage.annotation.SystemLog;
import com.wechat.manage.controller.index.BaseController;
import com.wechat.manage.pojo.wechat.vo.CouponInfoDto;
import com.wechat.manage.pojo.system.vo.UserBaseInfoDto;
import com.wechat.manage.pojo.wechat.entity.CouponInfo;
import com.wechat.manage.pojo.wechat.entity.DataTableResult;
import com.wechat.manage.pojo.system.entity.UserFormMap;
import com.wechat.manage.mapper.system.UserMapper;
import com.wechat.manage.service.wechat.intf.CouponInfoService;
import com.wechat.manage.utils.Common;
import com.wechat.manage.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CouponTPLController
 *
 * @author kongqf
 * @create 2016-12-08
 */
@Controller
@RequestMapping("coupongrp")
public class CouponGRPController extends BaseController {

	@Autowired
	CouponInfoService couponInfoService;
	@Autowired
	UserMapper userMapper;

	@RequestMapping("getlist")
	public String getlist(Model model) {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/coupon/grp/list";
	}

	@RequestMapping("addUI")
	public String addUI(Model model) {
		return Common.BACKGROUND_PATH + "/coupon/grp/add";
	}

	@RequestMapping("listTpl")
	public String listTpl(Model model) {
		return Common.BACKGROUND_PATH + "/coupon/grp/listTpl";
	}

	@RequestMapping("listGrp")
	public String listGrp(Model model) {
		return Common.BACKGROUND_PATH + "/coupon/grp/listGrp";
	}

	@RequestMapping("userList")
	public String userList(Model model) {
		return Common.BACKGROUND_PATH + "/coupon/grp/userlist";
	}

	@RequestMapping("addgener")
	public String addgener(Model model) {
		return Common.BACKGROUND_PATH + "/coupon/grp/addgener";
	}

	@RequestMapping("generList")
	public String generate(Model model) {
		return Common.BACKGROUND_PATH + "/coupon/grp/generList";
	}

	@RequestMapping("listrep")
	public String listrep(Model model) {
		return Common.BACKGROUND_PATH + "/coupon/grp/listrep";
	}

	@ResponseBody
	@RequestMapping("/findCouponInfoByPage")
	@SystemLog(module = "卡券", methods = "卡券管理-分页查询")
	public DataTableResult<CouponInfo> findCouponInfoByPage(CouponInfoDto para) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("start", para.getiDisplayStart());
		paramMap.put("limit", para.getiDisplayLength());
		paramMap.put("cardType", para.getCardType());
		paramMap.put("title", para.getTitle());
		UserBaseInfoDto curUser = getCurUserInfo();
		paramMap.put("storeCode", curUser.getStoreCode());
		if (para.getOperationType().equals("0")) {// 0提交1审批
			paramMap.put("createUserId", curUser.getUserId());
		} else {
			paramMap.put("approvalUserId", curUser.getUserId());
		}
		DataTableResult<CouponInfo> page = couponInfoService.findCouponInfoByPage(paramMap);
		page.setiTotalDisplayRecords(page.getiTotalRecords());
		page.setsEcho(para.getsEcho());
		return page;
	}

	@ResponseBody
	@RequestMapping("/addCouponInfo")
	@SystemLog(module = "卡券", methods = "卡券管理-新增")
	public String addCouponInfo(String txtTplName, String txtCouponName, String txtSubtitle,
			String selSys, String txtUseDesc, String txtApprovalUserName, String couponExpStart,
			String couponExpEnd, String getLimit, String quantity, String localUrl) {
		CouponInfo entity = new CouponInfo();
		entity.setImageName(localUrl);
		UserBaseInfoDto curUser = getCurUserInfo();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		Date beginTimestamp = null;
		Date endTimestamp = null;
		try {
			beginTimestamp = sim.parse(couponExpStart);
			endTimestamp = sim.parse(couponExpEnd);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		entity.setBeginTimestamp(beginTimestamp);
		entity.setEndTimestamp(endTimestamp);
		entity.setGetLimit(Integer.parseInt(getLimit));
		entity.setQuantity(Integer.parseInt(quantity));
		entity.setStoreCode(curUser.getStoreCode());
		entity.setCreateUserid(curUser.getUserId());
		entity.setTplSid(Integer.parseInt(txtTplName));
		entity.setTitle(txtCouponName);
		entity.setSubTitle(txtSubtitle);
		entity.setAppSystem(selSys);
		entity.setDescription(txtUseDesc);
		entity.setApprovalUserid(txtApprovalUserName);
		UserFormMap userFormMap = getFormMap(UserFormMap.class);
		userFormMap.put("id", txtApprovalUserName);
		List<UserFormMap> findUserPage = userMapper.findUserPage(userFormMap);
		String approvalUserName = (String) findUserPage.get(0).get("userName");
		entity.setApprovalUsername(approvalUserName);

		int addCouponInfo = couponInfoService.addCouponInfo(entity);
		if (addCouponInfo == 1) {
			return "success";
		} else {
			return "faile";
		}
	}

	@ResponseBody
	@RequestMapping("/editCouponInfo")
	@SystemLog(module = "卡券", methods = "卡券管理-修改-审核")
	public String editCouponInfo(String sid, String couponStatus) {
		CouponInfo entity = new CouponInfo();
		if (StringUtils.isNotEmpty(sid)) {
			entity.setSid(Long.parseLong(sid));
		}
		if (StringUtils.isNotEmpty(couponStatus)) {
			entity.setCouponStatus(couponStatus);
		}
		int addCouponInfo = couponInfoService.editCouponInfo(entity);
		if (addCouponInfo == 1) {
			return "success";
		} else {
			return "faile";
		}
	}
}
