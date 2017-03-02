package com.wechat.manage.controller.wshop.shopnav;


import com.wechat.manage.controller.index.BaseController;
import com.wechat.manage.pojo.wshopnav.entity.TNavRel;
import com.wechat.manage.pojo.wshopnav.entity.TShopConfig;
import com.wechat.manage.pojo.wshopnav.vo.TNavDto;
import com.wechat.manage.pojo.wshopnav.vo.TNavDtoList;
import com.wechat.manage.service.wshopnav.intf.ITNavRelService;
import com.wechat.manage.service.wshopnav.intf.ITNavService;
import com.wechat.manage.service.wshopnav.intf.ITShopConfigService;
import com.wechat.manage.utils.JsonUtil;
import com.wechat.manage.utils.ResultUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("tNav")
public class TNavController extends BaseController {
	@Autowired
	ITNavService navService;
	@Autowired
	ITNavRelService navRelService;
	@Autowired
	ITShopConfigService configService;

	@ResponseBody
	@RequestMapping("main")
	public void main() {
		// List<TNavDto> navList = new ArrayList<TNavDto>();
		// TNavDto nav = new TNavDto();
		// nav.setLinkUrl("www.baidu.com");
		// nav.setShopSid("1");
		// nav.setTitle("百度");
		// navList.add(nav);
		// List<TNavDtoList> navList_2 = new ArrayList<TNavDtoList>();
		// TNavDtoList nav2 = new TNavDtoList();
		// nav2.setLinkUrl("www.baidu222222.com");
		// nav2.setShopSid("1");
		// nav2.setTitle("百度222222");
		// TNavDtoList nav3 = new TNavDtoList();
		// nav3.setLinkUrl("www.baidu333333.com");
		// nav3.setShopSid("1");
		// nav3.setTitle("百度33333");
		// navList_2.add(nav2);
		// navList_2.add(nav3);
		// nav.setSecond(navList_2);
		// System.out.println(JsonUtil.getJSONString(navList));
		// List<TShopConfig> configList = new ArrayList<TShopConfig>();
		// TShopConfig config = new TShopConfig();
		// config.setConfigKey("1");
		// config.setConfigValue("baidu");
		// config.setShopId(1L);
		// configList.add(config);
		// List<TNavRel> relList = new ArrayList<TNavRel>();
		// TNavRel rel = new TNavRel();
		// rel.setPageTypeSid(1L);
		// rel.setShopId(1L);
		// relList.add(rel);
		// System.out.println(JsonUtil.getJSONString(navList) + "---"
		// + JsonUtil.getJSONString(configList) + "---" +
		// JsonUtil.getJSONString(relList));
		// System.out.println(insertSelective(JsonUtil.getJSONString(navList),
		// JsonUtil.getJSONString(configList),
		// JsonUtil.getJSONString(relList)));
		System.out.println(JsonUtil.getJSONString(sumByShopId("1")));
	}

	@ResponseBody
	@RequestMapping("insertSelective")
	public String insertSelective(String str, String config, String navrel) {
		String re = "";
		List<TNavDto> navList = JsonUtil.getJacksonDTO(str, ArrayList.class);
		List<TNavDto> navList_2 = new ArrayList<TNavDto>();
		for (int i = 0; i < navList.size(); i++) {
			TNavDto navDto = new TNavDto();
			try {
				BeanUtils.copyProperties(navDto, navList.get(i));
				List<TNavDtoList> second = navDto.getSecond();
				List<TNavDtoList> second2 = new ArrayList<TNavDtoList>();
				for (int j = 0; j < second.size(); j++) {
					TNavDtoList navDtoList = new TNavDtoList();
					BeanUtils.copyProperties(navDtoList, second.get(j));
					second2.add(navDtoList);
				}
				navDto.setSecond(second2);
				navList_2.add(navDto);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		List<TShopConfig> configList = JsonUtil.getJacksonDTO(config, ArrayList.class);
		List<TShopConfig> configList_2 = new ArrayList<TShopConfig>();
		for (int i = 0; i < configList.size(); i++) {
			TShopConfig tconfig = new TShopConfig();
			try {
				BeanUtils.copyProperties(tconfig, configList.get(i));
				configList_2.add(tconfig);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		List<TNavRel> relList = JsonUtil.getJacksonDTO(navrel, ArrayList.class);
		List<TNavRel> relList_2 = new ArrayList<TNavRel>();
		for (int i = 0; i < relList.size(); i++) {
			TNavRel tnavRel = new TNavRel();
			try {
				BeanUtils.copyProperties(tnavRel, relList.get(i));
				relList_2.add(tnavRel);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		try {
			re = navService.insertSelective(navList_2);
			re = navRelService.insertSelective(relList_2);
			re = configService.insertSelective(configList_2);
		} catch (Exception e) {
			e.printStackTrace();
			return "添加失败";
		}
		return re;
	}

	@ResponseBody
	@RequestMapping("selectByShopId")
	public Map<String, Object> selectByShopId(String shopId) {
		return ResultUtil.creComSucResult(navService.selectByShopId(shopId));
	}

	@ResponseBody
	@RequestMapping("shopConfigByShopId")
	public Map<String, Object> shopConfigByShopId(String shopId) {
		return ResultUtil.creComSucResult(configService.shopConfigByShopId(shopId));
	}

	@ResponseBody
	@RequestMapping("navRelByShopId")
	public Map<String, Object> navRelByShopId(String shopId) {
		return ResultUtil.creComSucResult(navRelService.navRelByShopId(shopId));
	}

	@ResponseBody
	@RequestMapping("sumByShopId")
	public Map<String, Object> sumByShopId(String shopId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Map<String, Object> selectByShopId = selectByShopId(shopId);
		Map<String, Object> shopConfigByShopId = shopConfigByShopId(shopId);
		Map<String, Object> navRelByShopId = navRelByShopId(shopId);
		paramMap.put("navList", selectByShopId);
		paramMap.put("configList", shopConfigByShopId);
		paramMap.put("relList", navRelByShopId);
		return ResultUtil.creComSucResult(paramMap);
	}
}
