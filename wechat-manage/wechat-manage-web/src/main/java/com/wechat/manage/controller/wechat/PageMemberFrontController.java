/**
 * 
 */
package com.wechat.manage.controller.wechat;

import com.alibaba.fastjson.JSONObject;
import com.wechat.manage.controller.index.BaseController;
import com.wechat.manage.utils.Common;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ghost
 * @version 创建时间：2016年12月29日 上午11:37:49 类说明
 */
@Controller
@RequestMapping("page")
public class PageMemberFrontController extends BaseController {
	@RequestMapping("memberFront")
	public String getlist(String index) {
		String destUrl = "";
		if (StringUtils.isEmpty(index)) {
			destUrl = Common.BACKGROUND_PATH + "/page/memberFront/front";
			return destUrl;
		}
		switch (index) {
		case "1":
			destUrl = "";
			break;
		case "2":
			destUrl = Common.BACKGROUND_PATH + "/page/memberPrivilege/privilege";
			break;
		case "3":
			destUrl = Common.BACKGROUND_PATH + "/page/wxMemberCard/boundCard";
			break;
		case "4":
			destUrl=Common.BACKGROUND_PATH +"/page/ActivatePhone/PhoneManage";
			break;
		case "5":
			destUrl = "";
			break;
		case "6":
			destUrl = Common.BACKGROUND_PATH + "/page/wxVIPCard/wxVIPCard";
			break;
		case "7":
			destUrl=Common.BACKGROUND_PATH + "/page/wxMemberCard/cardManage";
			break;
		default:
			destUrl = Common.BACKGROUND_PATH + "/page/memberFront/front";
			break;
		}
		return destUrl;
	}

	@RequestMapping("getHomeCfg")
	@ResponseBody
	public Map<String, Object> getPageManage(Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("IsError", false);
		map.put("ErrorMsg", null);
		map.put("ErrorCode", 0);
		// 组装数据
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("Id", 68);
		dataMap.put("LogoFullUrl", "http://cdn.ezrpro.com/img/3/ba68b759f5ce.jpg");
		dataMap.put("WxHeadHtml", "Html");
		dataMap.put("WxHomeHtml", "Html");
		dataMap.put("WxHomeHtmlM", M);
		map.put("data", dataMap);
		return map;
	}

	@RequestMapping("getWxCardCfg")
	@ResponseBody
	public Map<String, Object> getWxCardCfg(Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("IsError", false);
		map.put("ErrorMsg", null);
		map.put("ErrorCode", 0);
		// 组装数据
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("Id", 18);
		dataMap.put("CopId", 68);
		dataMap.put("CardId", "p6Wt5vwf-H_cVnpim-4wcMiTEcbA");
		dataMap.put("CardBrandName", "北京市百货大楼");
		dataMap.put(
				"CardLogoPic",
				"http://wx.qlogo.cn/mmopen/egCTBhvlZLl3lv9wlX2jN1icWG8Z3QNic19gHsHnMQWXSJR4YiaIFHrqfOJyP5YSeIfjcgMRWW4WeMTK3kUjdLSQXpiaBPgG9dvw/0");
		dataMap.put(
				"FullCardLogoPic",
				"http://wx.qlogo.cn/mmopen/egCTBhvlZLl3lv9wlX2jN1icWG8Z3QNic19gHsHnMQWXSJR4YiaIFHrqfOJyP5YSeIfjcgMRWW4WeMTK3kUjdLSQXpiaBPgG9dvw/0");
		dataMap.put("CenterType", "CS");
		dataMap.put("CardNoType", "OCODE");
		dataMap.put("CodeType", "CODE_TYPE_TEXT");
		dataMap.put("CoverType", "PC");
		dataMap.put("CoverPic", "/2/c25421ed5d65.jpg");
		dataMap.put("FullCoverPic", "http://cdn.ezrpro.com/img/2/c25421ed5d65.jpg");
		dataMap.put(
				"CoverWxPic",
				"http://mmbiz.qpic.cn/mmbiz_jpg/L0flmI3CX63JLibia5Tjbvqo6HEEpddN1FyRf70dQKgw4mz5TfRhJ2VQ5tK9cYGVqlpl40wkIEM4vEficoWxoUeHw/0");
		dataMap.put("CoverColor", "#000000");
		dataMap.put("CenterTitle", "使用会员卡");
		dataMap.put("CenterSubTitle", "门店支付时请出示会员卡");
		dataMap.put("CenterUrl", "http://m.ezrpro.cn/vip/vipqrcode/68");
		dataMap.put("SupplyBonus", false);
		dataMap.put("SupplyCoupon", false);
		dataMap.put("SupplyGrade", false);
		dataMap.put("SupplyBalance", false);
		dataMap.put("BalanceUrl", null);
		dataMap.put("BalanceName", null);
		dataMap.put("IsBonusDiscount", false);
		dataMap.put("IsCouponDiscount", false);
		dataMap.put("BonusRule", null);
		dataMap.put("Discount", 0);
		dataMap.put("Prerogative", "1.微信会员卡实体卡会员权益相同；\n2.具体会员权益可详见门店会员中心明示；\n3.跨店会员消费参照发卡店积分兑换标准。");
		dataMap.put("Description", "交款时直接出示微信会员卡，嘀一下就积分，即刻解放钱包超便捷！");
		dataMap.put("CustomCell1",
				"{\"Name\":\"绑定会员卡\",\"Tips\":null,\"Url\":\"/vip/bindentitycard/\"}");
		dataMap.put("Notice", "到店出示微信卡包");
		dataMap.put("Quantity", 0);
		dataMap.put("CustomUrlName", "我的特权");
		dataMap.put("CustomUrl", "/vip/myprivilege/");
		dataMap.put("CustomUrlSubTitle", null);
		dataMap.put("PromotionUrlName", "");
		dataMap.put("PromotionUrl", "");
		dataMap.put("PromotionUrlSubTitle", "");
		dataMap.put("WxCardUrl", null);
		dataMap.put("Status", 2);
		dataMap.put("StatusMsg", "审核成功");
		dataMap.put("CreateUser", "运维-zxr");
		dataMap.put("CreateDate", "/Date(1477903748000-0000)/");
		dataMap.put("LastModifiedUser", "LastModifiedUser");
		dataMap.put("LastModifiedDate", "/Date(1479092268000-0000)/");
		dataMap.put("BonusRuleObj", null);
		dataMap.put("BrandName", "北京市百货大楼");
		dataMap.put(
				"WxHeadImg",
				"http://wx.qlogo.cn/mmopen/egCTBhvlZLl3lv9wlX2jN1icWG8Z3QNic19gHsHnMQWXSJR4YiaIFHrqfOJyP5YSeIfjcgMRWW4WeMTK3kUjdLSQXpiaBPgG9dvw/0");
		dataMap.put("IsWxVipCard", true);
		dataMap.put("PageUrl", null);
		dataMap.put("CustomUrl", "/vip/myprivilege/");
		JSONObject obj = new JSONObject();
		obj.put("Name", "绑定会员卡");
		obj.put("Tips", "绑定会员卡");
		obj.put("Url", "/vip/bindentitycard/");
		dataMap.put("CustomCell1Obj", obj);
		map.put("Data", dataMap);
		return map;
	}

	private static final String M = "{\"actual\":{\"comheader_default\":{\"title\":\"王府井会员卡\",\"cardno\":\"{会员老卡号}\",\"img\":\"http://cdn.ezrpro.com/img/1/ba693652ff4e.jpg\",\"template\":\"head-style-2\"},\"text-navigation_1477904092260\":{\"list\":[{\"picture\":\"http://static.ezrpro.com/assets/icon/ico-nav-3.png\",\"linkname\":\"我的积分\",\"link\":\"/vip/myBonus/{品牌}\",\"title\":\"我的积分\"},{\"picture\":\"http://static.ezrpro.com/assets/icon/ico-nav-2.png\",\"linkname\":\"绑定实体会员卡\",\"link\":\"/vip/bindentitycard/{品牌}\",\"title\":\"绑定实体会员卡\"},{\"picture\":\"http://static.ezrpro.com/assets/icon/ico-nav-15.png\",\"linkname\":\"修改密码\",\"link\":\"/vip/updatepwd/{品牌}\",\"title\":\"修改支付密码\"},{\"picture\":\"http://static.ezrpro.com/assets/icon/ico-nav-4.png\",\"linkname\":\"我的优惠券\",\"link\":\"/coupon/mycoupons/{品牌}\",\"title\":\"我的优惠券\"},{\"title\":\"会员权益\",\"picture\":\"http://static.ezrpro.com/assets/icon/ico-nav-6.png\",\"linkname\":\"我的特权\",\"link\":\"/vip/myprivilege/{品牌}\"}]}}}";
}
