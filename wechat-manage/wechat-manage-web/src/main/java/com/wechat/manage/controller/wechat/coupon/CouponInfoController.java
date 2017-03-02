package com.wechat.manage.controller.wechat.coupon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wechat.manage.controller.index.BaseController;
import com.wechat.manage.pojo.wechat.vo.CouponDto;
import com.wechat.manage.pojo.wechat.vo.MemberViewDto;
import com.wechat.manage.pojo.wechat.vo.MsgMassReDto;
import com.wechat.manage.pojo.system.entity.AppAccountInfo;
import com.wechat.manage.pojo.wechat.entity.CouponMember;
import com.wechat.manage.service.wechat.intf.CouponInfoService;
import com.wechat.manage.service.wechat.intf.CouponMemberService;
import com.wechat.manage.service.wechat.intf.IAppAccountInfoService;
import com.wechat.manage.utils.HttpUtils;
import com.wechat.manage.utils.JsonUtil;
import com.wechat.manage.service.util.WechatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("couponInfo")
public class CouponInfoController extends BaseController {
	@Autowired
	CouponInfoService couponInfoService;
	@Autowired
	CouponMemberService couponMbService;
	@Autowired
	private WechatUtil tokenUtil;
	@Autowired
	private IAppAccountInfoService appService;

	@ResponseBody
	@RequestMapping("/sendCoupon")
	public String sendCoupon(String openIds, String infoSid, String storeCode) {
		String[] split = openIds.split(";");
		for (String string : split) {
			CouponMember entity = new CouponMember();
			entity.setMemberSid(string);
			entity.setCouponSid(Long.parseLong(infoSid));
			String addCouponMember = couponMbService.addCouponMember(entity);
			if (!addCouponMember.equals("success")) {
				return addCouponMember;
			}
		}
		Map<String, Object> wechatMap = new HashMap<String, Object>();
		Map<String, Object> textMap = new HashMap<String, Object>();
		wechatMap.put("touser", split);
		textMap.put("content", "导购向您发送了一张券，请注意查收");
		wechatMap.put("text", textMap);
		wechatMap.put("msgtype", "text");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("storeCode", storeCode);
		List<AppAccountInfo> list = appService.queryAppAccount(paramMap);
		String access_token = tokenUtil.getAccessToken(list.get(0).getAppid(),
				list.get(0).getAppsecret());
		String sendUrl = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token="
				+ access_token;
		String reString = HttpUtils.doPost(sendUrl, JsonUtil.getJSONString(wechatMap));
		MsgMassReDto media = JsonUtil.getJacksonDTO(reString, MsgMassReDto.class);
		if (!media.getErrcode().equals("0")) {
			return media.getErrmsg();
		} else {
			return "success";
		}
	}

	@ResponseBody
	@RequestMapping("/getCouponInfoByOpenIdAndStoreCode")
	public String getCouponInfoByOpenIdAndStoreCode(String openId, String storeCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("openId", openId);
		paramMap.put("storeCode", storeCode);
		List<CouponDto> list = couponInfoService.getCouponInfoByOpenIdAndStoreCode(paramMap);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		System.out.println("gson.toJson(list)" + gson.toJson(list));
		return gson.toJson(list);
	}

	@ResponseBody
	@RequestMapping("/getCouponInfoByStoreCode")
	public String getCouponInfoByStoreCode(String storeCode, String infoSid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("storeCode", storeCode);
		if (infoSid != null) {
			paramMap.put("infoSid", Long.parseLong(infoSid));
		}
		List<CouponDto> list = couponInfoService.getCouponInfoByStoreCode(paramMap);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		System.out.println("gson.toJson(list)" + gson.toJson(list));
		return gson.toJson(list);
	}

	@ResponseBody
	@RequestMapping("/getMemberViewList")
	public String getMemberViewList(String couponSid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("couponSid", Long.parseLong(couponSid));
		List<MemberViewDto> list = couponInfoService.getMemberViewList(paramMap);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		System.out.println("gson.toJson(list)" + gson.toJson(list));
		return gson.toJson(list);
	}

}
