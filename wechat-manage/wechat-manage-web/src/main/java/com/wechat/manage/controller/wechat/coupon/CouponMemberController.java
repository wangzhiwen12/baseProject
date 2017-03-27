package com.wechat.manage.controller.wechat.coupon;

import com.wechat.manage.annotation.SystemLog;
import com.wechat.manage.controller.index.BaseController;
import com.wechat.manage.mapper.wechat.MemberInfoMapper;
import com.wechat.manage.pojo.wechat.entity.CouponMember;
import com.wechat.manage.pojo.wechat.entity.MemberInfo;
import com.wechat.manage.pojo.wechat.vo.CouponInfoDto;
import com.wechat.manage.pojo.wechat.vo.CouponStatisticsDto;
import com.wechat.manage.pojo.wechat.vo.UserCouponInfoDto;
import com.wechat.manage.service.wechat.intf.CouponMemberService;
import com.wechat.manage.service.wechat.intf.MemberInfoService;
import com.wechat.manage.utils.Common;
import com.wechat.manage.utils.RedisUtil;
import com.wechat.manage.utils.StringUtils;
import com.wechat.manage.vo.DataTableParams;
import com.wechat.manage.vo.DataTableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/couponMember")
public class CouponMemberController extends BaseController {

	@Autowired
	CouponMemberService couponMbService;
	@Autowired
	RedisUtil redisUtil;
	@Autowired
	MemberInfoService memberInfoService;

	@ResponseBody
	@RequestMapping("/addCouponMember")
	@SystemLog(module = "卡券", methods = "卡券管理-新增")
	public String addCouponMember(String couponId, String openId) {
		CouponMember entity = new CouponMember();
		entity.setMemberSid(openId);
		entity.setCouponSid(Long.parseLong(couponId));
		return couponMbService.addCouponMember(entity);
	}

	@ResponseBody
	@RequestMapping("/receiveCoupon")
	@SystemLog(module = "卡券", methods = "领取卡券")
	public String receiveCoupon(String couponId, String mobile) {
		MemberInfo member = new MemberInfo();
		member.setMobile(mobile);
		List<MemberInfo> memberList = memberInfoService.selectListByParam(member);
		CouponMember entity = new CouponMember();
		entity.setMemberSid(memberList.get(0).getOpenid());
		entity.setCouponSid(Long.parseLong(couponId));
		return couponMbService.addCouponMember(entity);
	}



	@ResponseBody
	@RequestMapping("/findCouponMember")
	@SystemLog(module = "卡券", methods = "卡券管理-查询列表")
	public DataTableResult<CouponInfoDto> findCouponMember(String openId, String storeCode,
			DataTableParams para) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("start", para.getiDisplayStart());
		paramMap.put("limit", para.getiDisplayLength());
		paramMap.put("openId", openId);
		paramMap.put("storeCode", storeCode);
		DataTableResult<CouponInfoDto> page = couponMbService
				.getCouponInfoListByOpenIdAndStoreCdoe(paramMap);
		page.setiTotalDisplayRecords(page.getiTotalRecords());
		page.setsEcho(para.getsEcho());
		return page;
	}

	@RequestMapping("/coupondetail")
	public String coupondetail(Model model) {
		String cardId = getPara("cardId");
		String openId = getPara("openId");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("openId", openId);
		paramMap.put("sid", cardId);
		UserCouponInfoDto page = couponMbService.getCouponCodeByOpenIdAndCardId(paramMap);
		model.addAttribute("res", page);
		return Common.BACKGROUND_PATH + "/coupon/grp/coupon_detail";
	}

	@ResponseBody
	@RequestMapping("/getCouponCodeByOpenIdAndCardId")
	@SystemLog(module = "卡券", methods = "卡券管理-查询消费券")
	public UserCouponInfoDto getCouponCodeByOpenIdAndCardId(String openId, String cardId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("openId", openId);
		paramMap.put("sid", cardId);
		UserCouponInfoDto page = couponMbService.getCouponCodeByOpenIdAndCardId(paramMap);
		return page;
	}

	@ResponseBody
	@RequestMapping("/useCouponMember")
	@SystemLog(module = "卡券", methods = "卡券管理-卡券核销")
	public String useCouponMember(String couponCode, String couponQRCode) {
		CouponMember mem = new CouponMember();
		if (StringUtils.isNotEmpty(couponCode)) {
			mem.setCouponCode(couponCode);
		} else if (StringUtils.isNotEmpty(couponQRCode)) {
			String value = redisUtil.getKey(couponQRCode, "0000");
			if (value.equals("0000")) {
				return "二维码已过期";
			} else {
				mem.setCouponCode(value);
			}
		}
		return couponMbService.useCouponMember(mem);
	}

	@ResponseBody
	@RequestMapping("/getCouponStatistics")
	@SystemLog(module = "卡券", methods = "卡券管理-核销统计")
	public DataTableResult<CouponStatisticsDto> getCouponStatistics(DataTableParams para,
			String sid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("start", para.getiDisplayStart());
		paramMap.put("limit", para.getiDisplayLength());
		paramMap.put("sid", sid);
		DataTableResult<CouponStatisticsDto> page = couponMbService.getCouponStatistics(paramMap);
		page.setiTotalDisplayRecords(page.getiTotalRecords());
		page.setsEcho(para.getsEcho());
		return page;
	}

}
