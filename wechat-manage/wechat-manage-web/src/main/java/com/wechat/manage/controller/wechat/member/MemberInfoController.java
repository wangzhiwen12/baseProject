package com.wechat.manage.controller.wechat.member;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wechat.manage.controller.index.BaseController;
import com.wechat.manage.pojo.system.vo.StoreInfoDto;
import com.wechat.manage.pojo.system.vo.UserBaseInfoDto;
import com.wechat.manage.pojo.wechat.entity.MemberInfo;
import com.wechat.manage.pojo.wechat.vo.MemberInfoReturnDto;
import com.wechat.manage.pojo.wechat.vo.MemberPointInfoReturnDto;
import com.wechat.manage.pojo.wechat.vo.MemberPointReturnDto;
import com.wechat.manage.service.util.WechatUtil;
import com.wechat.manage.service.wechat.intf.MemberCardService;
import com.wechat.manage.service.wechat.intf.MemberInfoService;
import com.wechat.manage.service.wechat.intf.MemberPointInfoService;
import com.wechat.manage.service.wechat.intf.MemberPointService;
import com.wechat.manage.vo.DataTableParams;
import com.wechat.manage.vo.DataTableResult;
import com.wechat.manage.utils.StringUtils;
import com.wechat.manage.utils.PropertiesUtils;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxuan on 2016-11-22 0022.
 */
@Controller
@RequestMapping(value = { "/memberInfo" })
public class MemberInfoController extends BaseController {

	private static Logger logger = Logger.getLogger(MemberInfoController.class);

	@Autowired
	private WechatUtil wechatUtil;

	@Autowired
	private MemberInfoService memberInfoService;

	@Autowired
	private MemberCardService memberCardService;

	@Autowired
	private MemberPointInfoService memberPointInfoService;

	@Autowired
	private MemberPointService memberPointService;

	@ResponseBody
	@RequestMapping(value = "/getMsgMember")
	public DataTableResult<MemberInfo> getMsgMember(DataTableParams para, String massSid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		UserBaseInfoDto curUser = getCurUserInfo();
		paramMap.put("storeCode", curUser.getStoreCode());
		paramMap.put("start", para.getiDisplayStart());
		paramMap.put("limit", para.getiDisplayLength());
		paramMap.put("massSid", massSid);
		DataTableResult<MemberInfo> page = memberInfoService.getMsgMember(paramMap);
		page.setiTotalDisplayRecords(page.getiTotalRecords());
		page.setsEcho(para.getsEcho());
		return page;
	}

	@ResponseBody
	@RequestMapping(value = "/registerMember_2", method = { RequestMethod.GET, RequestMethod.POST })
	public String registerMember_2(String storeCode, String openId, String mobile,String gender,String name,String city, String birthday) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		MemberInfo memberInfo = new MemberInfo();
		memberInfo.setStoreCode(storeCode);
		memberInfo.setOpenid(openId);
		memberInfo.setMobile(mobile);
		if(gender!=null){
			memberInfo.setSex(Integer.valueOf(gender));	
		}
		memberInfo.setNickname(name);
		memberInfo.setCity(city);
		memberInfo.setBrithday(birthday);
		if (StringUtils.isNotEmpty(mobile)) {
			memberInfo.setMobile(mobile.trim());
			memberInfo.setPassword(mobile.trim().substring(5, mobile.length()));// 密码默认手机后六位
		}
		try {
			logger.info("注册参数：" + JSONObject.fromObject(memberInfo).toString());
			Map<String, Object> returnMap = memberInfoService.registerMember_2(memberInfo);
			logger.info("调用注册返回：" + returnMap.toString());
			if ("true".equals(returnMap.get("success") + "")) {
				// resultMap.put("cid", dto.getObject());
				resultMap.put("msg", returnMap.get("desc") + "");
				resultMap.put("success", true);
			} else {
				resultMap.put("msg", returnMap.get("desc") + "");
				resultMap.put("success", false);
			}
		} catch (Exception e) {
			resultMap.put("msg", "系统错误");
			resultMap.put("success", false);
		}
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(resultMap);
	}

	/**
	 * 注册会员
	 *
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/registerMember", method = { RequestMethod.GET, RequestMethod.POST })
	public String registerMember(String storeCode, String openId, String unionId, String idCard,
			String email, String mobile, String appId, String secret) {
		MemberInfo memberInfo = new MemberInfo();
		if (StringUtils.isNotEmpty(storeCode)) {
			memberInfo.setStoreCode(storeCode.trim());
		}
		if (StringUtils.isNotEmpty(mobile)) {
			memberInfo.setMobile(mobile.trim());
			memberInfo.setPassword(mobile.trim().substring(5, mobile.length()));// 密码默认手机后六位
		}
		if (StringUtils.isNotEmpty(openId)) {
			memberInfo.setOpenid(openId.trim());
		}
		if (StringUtils.isNotEmpty(unionId)) {
			memberInfo.setUnionid(unionId.trim());
		}
		try {
			com.wechat.manage.pojo.wechat.vo.MemberInfo openid_userinfo = wechatUtil.Openid_userinfo(openId, appId,
					secret);
			memberInfo.setSubscribe(openid_userinfo.getSubscribe());
			memberInfo.setNickname(openid_userinfo.getNickname());
			memberInfo.setSex(openid_userinfo.getSex());
			memberInfo.setCity(openid_userinfo.getCity());
			memberInfo.setCountry(openid_userinfo.getCountry());
			memberInfo.setProvince(openid_userinfo.getProvince());
			memberInfo.setLanguage(openid_userinfo.getLanguage());
			memberInfo.setHeadimgurl(openid_userinfo.getHeadimgurl());
			memberInfo.setSubscribeTime(openid_userinfo.getSubscribe_time());
			memberInfo.setRemark(openid_userinfo.getRemark());
			memberInfo.setGroupid(openid_userinfo.getGroupid());
			String openid_userinfoUnionid = openid_userinfo.getUnionid();
			if (StringUtils.isNotEmpty(openid_userinfoUnionid)) {
				memberInfo.setUnionid(openid_userinfoUnionid);
			}
			String openid_userinfoOpenid = openid_userinfo.getOpenid();
			if (StringUtils.isNotEmpty(openid_userinfoOpenid)) {
				memberInfo.setOpenid(openid_userinfoOpenid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		memberInfo.setEmail(email);
		memberInfo.setIdCard(idCard);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			logger.info("注册参数：" + JSONObject.fromObject(memberInfo).toString());
			Map<String, Object> returnMap = memberInfoService.registerMember(memberInfo);
			logger.info("调用注册返回：" + returnMap.toString());
			if ("true".equals(returnMap.get("success") + "")) {
				// resultMap.put("cid", dto.getObject());
				resultMap.put("msg", returnMap.get("desc") + "");
				resultMap.put("success", true);
			} else {
				resultMap.put("msg", returnMap.get("desc") + "");
				resultMap.put("success", false);
			}
		} catch (Exception e) {
			resultMap.put("msg", "系统错误");
			resultMap.put("success", false);
		}
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(resultMap);
	}

	/**
	 * 注册会员
	 *
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/registerMember2", method = { RequestMethod.GET, RequestMethod.POST })
	public String registerMember2(String storeCode, String openId, String unionId, String idCard,
			String email, String mobile, String appId, String secret) {
		MemberInfo memberInfo = new MemberInfo();
		if (StringUtils.isNotEmpty(storeCode)) {
			memberInfo.setStoreCode(storeCode.trim());
		}
		if (StringUtils.isNotEmpty(mobile)) {
			memberInfo.setMobile(mobile.trim());
			memberInfo.setPassword(mobile.trim().substring(5, mobile.length()));// 密码默认手机后六位
		}
		if (StringUtils.isNotEmpty(openId)) {
			memberInfo.setOpenid(openId.trim());
		}
		if (StringUtils.isNotEmpty(unionId)) {
			memberInfo.setUnionid(unionId.trim());
		}
		try {
			com.wechat.manage.pojo.wechat.vo.MemberInfo openid_userinfo = wechatUtil.Openid_userinfo(openId, appId,
					secret);
			memberInfo.setSubscribe(openid_userinfo.getSubscribe());
			memberInfo.setNickname(openid_userinfo.getNickname());
			memberInfo.setSex(openid_userinfo.getSex());
			memberInfo.setCity(openid_userinfo.getCity());
			memberInfo.setCountry(openid_userinfo.getCountry());
			memberInfo.setProvince(openid_userinfo.getProvince());
			memberInfo.setLanguage(openid_userinfo.getLanguage());
			memberInfo.setHeadimgurl(openid_userinfo.getHeadimgurl());
			memberInfo.setSubscribeTime(openid_userinfo.getSubscribe_time());
			memberInfo.setRemark(openid_userinfo.getRemark());
			memberInfo.setGroupid(openid_userinfo.getGroupid());
			String openid_userinfoUnionid = openid_userinfo.getUnionid();
			if (StringUtils.isNotEmpty(openid_userinfoUnionid)) {
				memberInfo.setUnionid(openid_userinfoUnionid);
			}
			String openid_userinfoOpenid = openid_userinfo.getOpenid();
			if (StringUtils.isNotEmpty(openid_userinfoOpenid)) {
				memberInfo.setOpenid(openid_userinfoOpenid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		memberInfo.setEmail(email);
		memberInfo.setIdCard(idCard);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			logger.info("注册参数：" + JSONObject.fromObject(memberInfo).toString());
			Map<String, Object> returnMap = memberInfoService.registerMember(memberInfo);
			logger.info("调用注册返回：" + returnMap.toString());
			if ("true".equals(returnMap.get("success") + "")) {
				// resultMap.put("cid", dto.getObject());
				resultMap.put("msg", returnMap.get("desc") + "");
				resultMap.put("success", true);
			} else {
				resultMap.put("msg", returnMap.get("desc") + "");
				resultMap.put("success", false);
			}
		} catch (Exception e) {
			resultMap.put("msg", "系统错误");
			resultMap.put("success", false);
		}
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(resultMap);
	}

	/**
	 * 绑定卡
	 *
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bindMemberCard", method = { RequestMethod.GET, RequestMethod.POST })
	public String bindMemberCard(String storeCode, String cardCode, String mobile, String openId,
			String appId, String secret, String unionId, String password, String cardType,
			String cardLevel) {
		logger.info("start com.wfj.controller.member.MemberInfoController.bindMemberCard()");
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(storeCode)) {
			paraMap.put("storeCode", storeCode);
		}
		if (StringUtils.isNotEmpty(cardCode)) {
			paraMap.put("cardCode", cardCode);
		}
		if (StringUtils.isNotEmpty(mobile)) {
			paraMap.put("mobile", mobile);
		}
		if (StringUtils.isNotEmpty(openId)) {
			paraMap.put("openid", openId);
		}
		if (StringUtils.isNotEmpty(appId)) {
			paraMap.put("appid", appId);
		}
		if (StringUtils.isNotEmpty(secret)) {
			paraMap.put("secret", secret);
		}
		if (StringUtils.isNotEmpty(unionId)) {
			paraMap.put("unionid", unionId);
		}
		if (StringUtils.isNotEmpty(password)) {
			paraMap.put("password", password);
		}
		if (StringUtils.isNotEmpty(cardType)) {
			paraMap.put("cardType", cardType);
		}
		if (StringUtils.isNotEmpty(cardLevel)) {
			paraMap.put("cardLevel", cardLevel);
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			logger.info("绑定会员卡,请求数据：" + paraMap.toString());
			Map<String, Object> returnMap = memberCardService.bindMemberCard(paraMap);
			logger.info("绑定会员卡,响应数据：" + returnMap.toString());
			if ("true".equals(returnMap.get("success") + "")) {
				// resultMap.put("cid", dto.getObject());
				resultMap.put("msg", returnMap.get("desc") + "");
				resultMap.put("success", true);
			} else {
				resultMap.put("msg", returnMap.get("desc") + "");
				resultMap.put("success", false);
			}
		} catch (Exception e) {
			logger.error("绑定会员卡异常", e);
			resultMap.put("msg", "系统错误");
			resultMap.put("success", false);
		}
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(resultMap);
	}

	/**
	 * 解绑实体卡
	 *
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/cardCancleBind", method = { RequestMethod.GET, RequestMethod.POST })
	public String cardCancleBind(String openId, String appId, String storeCode, String cardCode) {
		logger.info("start com.wfj.controller.member.MemberInfoController.cardCancleBind()");
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(openId)) {
			paraMap.put("openid", openId.trim());
		}
		if (StringUtils.isNotEmpty(appId)) {
			paraMap.put("appid", appId);
		}
		if (StringUtils.isNotEmpty(storeCode)) {
			paraMap.put("storeCode", storeCode.trim());
		}
		if (StringUtils.isNotEmpty(cardCode)) {
			paraMap.put("cardCode", cardCode.trim());
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			logger.info("解绑实体卡,请求数据：" + paraMap.toString());
			Map<String, Object> returnMap = memberCardService.cardCancleBind(paraMap);
			logger.info("解绑实体卡,响应数据：" + returnMap.toString());
			if ("true".equals(returnMap.get("success") + "")) {
				resultMap.put("msg", returnMap.get("desc") + "");
				resultMap.put("success", true);
			} else {
				resultMap.put("msg", returnMap.get("desc") + "");
				resultMap.put("success", false);
			}
		} catch (Exception e) {
			logger.error("解绑实体卡异常", e);
			resultMap.put("msg", "系统错误");
			resultMap.put("success", false);
		}
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(resultMap);
	}

	/**
	 * 返回首页时去取地址(跳转页面、转向注册页面或者我的会员页面)
	 *
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getBackIndexUrl", method = { RequestMethod.GET, RequestMethod.POST })
	public String getBackIndexUrl(String state, String openId) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();

		if (StringUtils.isNotEmpty(state)) {
			paramMap.put("state", state);
		}
		if (StringUtils.isNotEmpty(openId)) {
			paramMap.put("openid", openId);
		}
		String json = "";
		try {
			logger.info("访问参数：" + JSONObject.fromObject(paramMap).toString());
			json = memberIndex(paramMap);
			logger.info("返回参数：" + json);
			resultMap.put("data", json);
			resultMap.put("success", true);
		} catch (Exception e) {
			resultMap.put("msg", "系统错误");
			resultMap.put("success", false);
		}
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(resultMap);
	}

	/**
	 * 跳转页面
	 *
	 * @param
	 * @return
	 */
	private String memberIndex(Map<String, Object> paramMap) {
		long starTime = System.currentTimeMillis();
		String state = (String) paramMap.get("state");
		String openid = (String) paramMap.get("openid");
		String url = "";
		try {
			// 1 通过门店接口获取appID,appSecret
			StoreInfoDto storeInfo = wechatUtil.getStoreInfo(state);
			System.out.println("storeInfo ================ " + storeInfo);
			// 2 通过appID,appSecret获取access_token
			String accessToken = wechatUtil.getAccessToken(storeInfo.getAppId(),
					storeInfo.getSecret());
			System.out.println("accessToken ================ " + accessToken);
			// 4 通过access_token,openID获取用户信息
			com.wechat.manage.pojo.wechat.vo.MemberInfo memberInfo = wechatUtil.Openid_userinfo(openid,
					storeInfo.getAppId(), storeInfo.getSecret());
			System.out.println("memberInfo ================ " + memberInfo);
			String name = wechatUtil.getURLEncoder(memberInfo.getNickname());
			String para = "&appId=" + storeInfo.getAppId() + "&openId=" + openid + "&headimgurl="
					+ memberInfo.getHeadimgurl() + "&nickname=" + name + "&registType="
					+ storeInfo.getIsChannel() + "&unionId=" + memberInfo.getUnionid()
					+ "&storeCode=" + storeInfo.getStoreCode();
			// Map<String, String> paramMap_1 = new HashMap<String, String>();
			// paramMap_1.put("appid", storeInfo.getAppId());
			// paramMap_1.put("openid", openid);

			String sendGet = getMemberInfo(storeInfo.getAppId(), openid, storeInfo.getStoreCode());// HttpUtil.sendGet(getMemberInfo,
			// paramMap_1);

			// String sendGet = getMemberInfo(storeInfo.getAppId(), openid);
			url = sendGet + para;
			long endTime = System.currentTimeMillis();
			System.out.println("------------------------------------" + (endTime - starTime)
					+ "getMemberInfo");
			System.out.println("url ================ " + url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	/**
	 * 获取会员用户及会员卡信息(跳转页面)
	 *
	 * @param
	 * @return
	 * @throws Exception
	 */
	private String getMemberInfo(String appid, String openid, String storeCode) throws Exception {
		logger.info(
				"start com.wfj.controller.member.MemberInfoController.getMemberInfo(),para:{appid:"
						+ appid + ",openid:" + openid + "}");
		String url = "";
		String para = "";
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("appid", appid);
		paraMap.put("openid", openid);
		paraMap.put("storeCode", storeCode);
		Map<String, Object> returnMap = memberInfoService.getMemberInfo(paraMap);
		if ("0".equals(returnMap.get("code") + "")) {// 未注册、未实体绑卡 跳转至头像首页
			logger.info("com.wfj.controller.member.MemberInfoController.getMemberInfo:未注册、未实体绑卡");
			url = PropertiesUtils.findPropertiesKey("myMemberInfoInit");
		} else if ("1".equals(returnMap.get("code") + "")) {
			MemberInfoReturnDto returnDto = new MemberInfoReturnDto();
			BeanUtils.copyProperties(returnDto, returnMap.get("obj"));
			returnDto.setQrcode(
					returnDto.getStoreCode() + returnDto.getMemberCode() + returnDto.getCardCode());

			String mobile = returnDto.getMobile();

			para = "memberCode=" + returnDto.getMemberCode() + "&cardNo=" + returnDto.getCardCode()
					+ "&custType=" + returnDto.getCardLevel() + "&qrcode=" + returnDto.getQrcode()
					+ "&mobile=" + mobile;

			Integer cardType = returnDto.getCardType();
			if (cardType != 1) {// 是否存在有实体卡,无实体卡
				url = PropertiesUtils.findPropertiesKey("myMemberInfo");
				para = para + "&entityCard=0&updatePWD=0";
			} else if (cardType == 1) {// 有实体卡
				url = PropertiesUtils.findPropertiesKey("myMemberInfo");
				if (StringUtils.isNotEmpty(mobile)) {// 有手机号
					para = para + "&unBund=0&updatePWD=0";
				} else {// 没有手机号
					para = para + "&unBund=0&boundPhone=0";
				}
			}
		}
		logger.info("end com.wfj.controller.member.MemberInfoController.getMemberInfo(),return:"
				+ url + para);
		return url + para;
	}

	/**
	 * 修改支付密码
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = { "/newChangePayPwd" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String newChangePayPwd(String storeCode, String openId, String appId, String oldPayPwd,
			String newPayPwd, String newPayPwdCheck) {
		logger.info("start com.wfj.controller.member.MemberInfoController.newChangePayPwd()");
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(storeCode)) {
			paraMap.put("storeCode", storeCode.trim());
		}
		if (StringUtils.isNotEmpty(openId)) {
			paraMap.put("openid", openId.trim());
		}
		if (StringUtils.isNotEmpty(appId)) {
			paraMap.put("appid", appId.trim());
		}
		if (StringUtils.isNotEmpty(oldPayPwd)) {
			paraMap.put("oldPayPwd", oldPayPwd.trim());
		}
		if (StringUtils.isNotEmpty(newPayPwd)) {
			paraMap.put("newPayPwd", newPayPwd.trim());
		}
		logger.info("请求参数：" + paraMap.toString() + "--newPayPwdCheck:" + newPayPwdCheck);
		logger.info("开始判断新密码与确认密码是否一致！");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (newPayPwd.trim().equals(newPayPwdCheck.trim())) {
			try {
				Map<String, Object> returnMap = memberInfoService.changePayPassword(paraMap);
				String success = returnMap.get("success") + "";
				String desc = returnMap.get("desc") + "";
				if ("true".equals(success)) {
					resultMap.put("msg", desc);
					resultMap.put("success", true);
				} else {
					resultMap.put("msg", desc);
					resultMap.put("success", false);
				}
			} catch (Exception e) {
				resultMap.put("msg", "系统错误");
				resultMap.put("success", false);
			}
		} else {
			resultMap.put("msg", "新密码与确认密码不一致！");
			resultMap.put("success", false);
		}
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		logger.info("end com.wfj.controller.member.MemberInfoController.newChangePayPwd(),return:"
				+ resultMap.toString());
		return gson.toJson(resultMap);
	}

	/**
	 * 查询积分明细
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getMemberPointInfo", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String getMemberPointInfo(String storeCode, String memberCode, String cardNo,
			String pageNo, String pageSize) {
		logger.info("start com.wfj.controller.member.MemberInfoController.getMemberPointInfo()");
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(storeCode)) {
			paraMap.put("storeCode", storeCode.trim());
		}
		if (StringUtils.isNotEmpty(memberCode)) {
			paraMap.put("memberCode", memberCode.trim());
		}
		if (StringUtils.isNotEmpty(cardNo)) {
			paraMap.put("cardCode", cardNo.trim());
		}
		if (StringUtils.isNotEmpty(pageNo)) {
			paraMap.put("pageNo", pageNo.trim());
		}
		if (StringUtils.isNotEmpty(pageSize)) {
			paraMap.put("pageSize", pageSize.trim());
		}
		logger.info("查询参数：" + paraMap.toString());
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<MemberPointInfoReturnDto> returnDtoList = memberPointInfoService
					.findMemberPointDetailByPara(paraMap);
			resultMap.put("obj", returnDtoList);
			resultMap.put("success", true);
		} catch (Exception e) {
			resultMap.put("msg", "系统错误");
			resultMap.put("success", false);
		}

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		logger.info(
				"end com.wfj.controller.member.MemberInfoController.getMemberPointInfo(),return:"
						+ resultMap.toString());
		return gson.toJson(resultMap);
	}

	/**
	 * 查询积分总额
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getMemberPoint", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getMemberPoint(String storeCode, String memberCode) {
		logger.info("start com.wfj.controller.member.MemberInfoController.getMemberPoint()");
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(storeCode)) {
			paraMap.put("storeCode", storeCode.trim());
		}
		if (StringUtils.isNotEmpty(memberCode)) {
			paraMap.put("memberCode", memberCode.trim());
		}
		logger.info("查询参数：" + paraMap.toString());
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			MemberPointReturnDto returnDto = memberPointService.findMemberPointByPara(paraMap);
			resultMap.put("obj", returnDto);
			resultMap.put("success", true);
		} catch (Exception e) {
			resultMap.put("msg", "系统错误");
			resultMap.put("success", false);
		}

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		logger.info("end com.wfj.controller.member.MemberInfoController.getMemberPoint(),return:"
				+ resultMap.toString());
		return gson.toJson(resultMap);
	}

	/**
	 * 查询个人资料
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/findMemberAndStoreInfoByPara", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String findMemberAndStoreInfoByPara(String openId, String storeCode) {
		logger.info(
				"start com.wfj.controller.member.MemberInfoController.findMemberAndStoreInfoByPara()");
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(openId)) {
			paraMap.put("openid", openId.trim());
		}
		if (StringUtils.isNotEmpty(storeCode)) {
			paraMap.put("storeCode", storeCode.trim());
		}
		logger.info("查询参数：" + paraMap.toString());
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			MemberInfoReturnDto returnDto = memberInfoService.findMemberAndStoreInfoByPara(paraMap);
			resultMap.put("obj", returnDto);
			resultMap.put("success", true);
		} catch (Exception e) {
			resultMap.put("msg", "系统错误");
			resultMap.put("success", false);
		}

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		logger.info(
				"end com.wfj.controller.member.MemberInfoController.findMemberAndStoreInfoByPara(),return:"
						+ resultMap.toString());
		return gson.toJson(resultMap);
	}

	/**
	 * 修改个人资料
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = { "/modifyMemberInfo" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String modifyMemberInfo(String storeCode, String openId, String memberCode,
			String mobile, String idCard, String email) {
		logger.info("start com.wfj.controller.member.MemberInfoController.modifyMemberInfo()");
		MemberInfo memberInfo = new MemberInfo();
		if (StringUtils.isNotEmpty(storeCode)) {
			memberInfo.setStoreCode(storeCode.trim());
		}
		if (StringUtils.isNotEmpty(openId)) {
			memberInfo.setOpenid(openId.trim());
		}
		if (StringUtils.isNotEmpty(memberCode)) {
			memberInfo.setMemberCode(memberCode.trim());
		}
		if (StringUtils.isNotEmpty(mobile)) {
			memberInfo.setMobile(mobile.trim());
		}
		if (StringUtils.isNotEmpty(idCard)) {
			memberInfo.setIdCard(idCard.trim());
		}
		if (StringUtils.isNotEmpty(email)) {
			memberInfo.setEmail(email.trim());
		}
		logger.info("请求参数：" + memberInfo.toString());
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Map<String, Object> returnMap = memberInfoService.modifyMemberInfo(memberInfo);
			String success = returnMap.get("success") + "";
			String desc = returnMap.get("desc") + "";
			if ("true".equals(success)) {
				resultMap.put("msg", desc);
				resultMap.put("success", true);
			} else {
				resultMap.put("msg", desc);
				resultMap.put("success", false);
			}
		} catch (Exception e) {
			resultMap.put("msg", "系统错误");
			resultMap.put("success", false);
		}

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		logger.info("end com.wfj.controller.member.MemberInfoController.modifyMemberInfo(),return:"
				+ resultMap.toString());
		return gson.toJson(resultMap);
	}

}
