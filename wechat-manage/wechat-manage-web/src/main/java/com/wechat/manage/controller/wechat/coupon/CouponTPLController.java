package com.wechat.manage.controller.wechat.coupon;

import com.wechat.manage.annotation.SystemLog;
import com.wechat.manage.controller.index.BaseController;
import com.wechat.manage.pojo.wechat.vo.CouponTemplateDto;
import com.wechat.manage.pojo.system.vo.UserBaseInfoDto;
import com.wechat.manage.pojo.wechat.entity.CouponTemplate;
import com.wechat.manage.pojo.wechat.entity.DataTableResult;
import com.wechat.manage.pojo.wechat.entity.GlobalDic;
import com.wechat.manage.service.wechat.intf.CouponTemplateService;
import com.wechat.manage.service.wechat.intf.IGlobalDicService;
import com.wechat.manage.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping("coupontpl")
public class CouponTPLController extends BaseController {

	@Autowired
	private CouponTemplateService couponTPLService;
	@Autowired
	private IGlobalDicService globalService;

	@RequestMapping("getlist")
	public String getlist(Model model) {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/coupon/tpl/list";
	}

	@RequestMapping("addUI")
	public String addUI(Model model) {
		GlobalDic entity = new GlobalDic();
		entity.setKeyword("coupon_bg");
		List<GlobalDic> bgList = globalService.queryDicList(entity);
		model.addAttribute("bgList", bgList);
		return Common.BACKGROUND_PATH + "/coupon/tpl/add";
	}

	@ResponseBody
	@RequestMapping("/findCouponTPLByPage")
	@SystemLog(module = "卡券模板", methods = "卡券模板管理-分页查询")
	public DataTableResult<CouponTemplate> findCouponTPLByPage(CouponTemplateDto para) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("start", para.getiDisplayStart());
		paramMap.put("limit", para.getiDisplayLength());
		UserBaseInfoDto curUser = getCurUserInfo();
		paramMap.put("storeCode", curUser.getStoreCode());
		paramMap.put("createUserId", curUser.getUserId());
		DataTableResult<CouponTemplate> page = couponTPLService.selectPageListByParam(paramMap);
		page.setiTotalDisplayRecords(page.getiTotalRecords());
		page.setsEcho(para.getsEcho());
		return page;
	}

	@ResponseBody
	@RequestMapping("/findCoupon")
	@SystemLog(module = "卡券模板", methods = "卡券模板管理-列表查询")
	public List<CouponTemplate> findCouponTPL() {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		UserBaseInfoDto curUser = getCurUserInfo();
		paramMap.put("storeCode", curUser.getStoreCode());
		paramMap.put("createUserId", curUser.getUserId());
		DataTableResult<CouponTemplate> page = couponTPLService.selectPageListByParam(paramMap);
		List<CouponTemplate> aaData = page.getAaData();
		return aaData;
	}

	@ResponseBody
	@RequestMapping("/addCouponTPL")
	@SystemLog(module = "卡券模板", methods = "卡券模板管理-新增")
	public String addCouponTPL(String selCouponType, String txtCouponValue,
			String txtCouponPriceLimit, String txtCouponName, String noLength, String prefixStr,
			String startNo, String suffixLength, String cpbackground) {
		CouponTemplate entity = new CouponTemplate();
		entity.setCouponType(selCouponType);
		entity.setCouponPriceLimit(txtCouponPriceLimit);
		entity.setCouponValue(txtCouponValue);
		entity.setCouponName(txtCouponName);
		entity.setNoLength(noLength);
		entity.setPrefixStr(prefixStr);
		entity.setStartNo(startNo);
		entity.setSuffixLength(suffixLength);
		entity.setBackground(cpbackground);
		UserBaseInfoDto curUser = getCurUserInfo();
		entity.setStoreCode(curUser.getStoreCode());
		entity.setCreateUserid(curUser.getUserId());
		entity.setCreateUserName(curUser.getUserName());

		int i = couponTPLService.insertSelective(entity);
		if (i == 1) {
			return "success";
		} else {
			return "faile";
		}
	}

	@ResponseBody
	@RequestMapping("/deleteCouponTPL")
	@SystemLog(module = "卡券模板", methods = "卡券模板管理-删除")
	public String deleteCouponTPL(Model model) {
		String sid = getPara("sid");
		int del = couponTPLService.deleteByPrimaryKey(Integer.parseInt(sid));
		if (del > 0) {
			return "success";
		} else {
			return "faile";
		}
	}

}
