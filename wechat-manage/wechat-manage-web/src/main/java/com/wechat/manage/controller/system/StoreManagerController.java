package com.wechat.manage.controller.system;

import com.wechat.manage.annotation.SystemLog;
import com.wechat.manage.controller.index.BaseController;
import com.wechat.manage.mapper.system.AppAccountInfoMapper;
import com.wechat.manage.mapper.system.StoreInfoMapper;
import com.wechat.manage.pojo.system.entity.AppAccountInfo;
import com.wechat.manage.pojo.system.entity.StoreInfo;
import com.wechat.manage.pojo.system.vo.ReturnDto;
import com.wechat.manage.pojo.wechat.vo.GroupResult;
import com.wechat.manage.service.wechat.intf.StoreInfoService;
import com.wechat.manage.utils.Common;
import com.wechat.manage.utils.HttpUtils;
import com.wechat.manage.vo.DataTableParams;
import com.wechat.manage.vo.DataTableResult;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxuan on 2016-11-30 0030. 门店管理
 */
@Controller
@RequestMapping(value = { "/storeManager" })
public class StoreManagerController extends BaseController {

	private static Logger logger = Logger.getLogger(StoreManagerController.class);

	@Inject
	private StoreInfoMapper storeInfoMapper;

	@Inject
	private StoreInfoService storeInfoService;

	@Inject
	private AppAccountInfoMapper appaccountInfoMapper;

	/**
	 * 访问展示页面
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/list" })
	public String listUI(Model model) {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/wechat/storeManager/list";
	}

	/**
	 * 查询门店
	 *
	 * @param
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/findByPage2")
	public DataTableResult findByPage2(DataTableParams dataTableParams, String storeCode, String businessName)
			throws Exception {
		logger.debug("start com.wfj.controller.wechat.StoreManagerController.findByPage2()");
		Integer displayStart = dataTableParams.getiDisplayStart();
		Integer displayLength = dataTableParams.getiDisplayLength();

		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (displayStart != null)
			paramMap.put("start", displayStart);
		if (displayLength != null)
			paramMap.put("limit", displayLength);
		if (Common.isNotEmpty(storeCode))
			paramMap.put("storeCode", storeCode.trim());
		if (Common.isNotEmpty(businessName))
			paramMap.put("businessName", businessName.trim());
		logger.debug("com.wfj.controller.wechat.StoreManagerController.findByPage2,para:" + paramMap.toString());
		List<StoreInfo> storeInfoList = storeInfoMapper.selectListByParamLike(paramMap);

		DataTableResult<StoreInfo> dataTableResult = new DataTableResult<StoreInfo>();
		dataTableResult.setsEcho(dataTableParams.getsEcho());
		dataTableResult.setAaData(storeInfoList);
		dataTableResult.setiTotalDisplayRecords(storeInfoList.size());
		dataTableResult.setiTotalRecords(storeInfoList.size());
		logger.debug("end com.wfj.controller.wechat.StoreManagerController.findByPage2(),return:"
				+ dataTableResult.toString());
		return dataTableResult;
	}

	/**
	 * 跳转添加页面
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/addUI" })
	public String addUI() throws Exception {
		return Common.BACKGROUND_PATH + "/wechat/storeManager/add";
	}

	/**
	 * 添加门店
	 *
	 * @param
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = { "/addStore" })
	@SystemLog(module = "门店管理", methods = "门店管理-添加门店")
	public String addStore(StoreInfo storeInfo) throws Exception {
		ReturnDto returnDto = storeInfoService.addStore(storeInfo);
		return "success";
	}

	/**
	 * 查询具体门店信息
	 *
	 * @param
	 * @return
	 */
	private StoreInfo getStoreInfo(String storeCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		StoreInfo storeInfo = null;
		if (Common.isNotEmpty(storeCode)) {
			paramMap.put("storeCode", storeCode.trim());
			List<StoreInfo> storeInfoList = storeInfoMapper.selectListByParam(paramMap);
			if (storeInfoList.size() == 1) {
				storeInfo = storeInfoList.get(0);
			}
		}
		return storeInfo;
	}

	/**
	 * 跳转修改页面
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/editUI")
	public String editUI(Model model, String storeCode) throws Exception {
		StoreInfo storeInfo = getStoreInfo(storeCode);
		Map<String, Object> map = new HashMap<String, Object>();
		if (Common.isNotEmpty(storeCode)) {
			map.put("storecode", storeCode);
			map.put("delFlag", "0");
			List<AppAccountInfo> applist = appaccountInfoMapper.selectListByParam(map);
			if (applist.size() > 0) {
				storeInfo.setAppid(applist.get(0).getAppid());
				storeInfo.setAppsecret(applist.get(0).getAppsecret());
			}
		}
		model.addAttribute("store", storeInfo);
		return Common.BACKGROUND_PATH + "/wechat/storeManager/edit";
	}

	/**
	 * 跳转详情页面
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getDetailUI")
	public String getDetailUI(Model model, String storeCode) throws Exception {
		StoreInfo storeInfo = getStoreInfo(storeCode);
		Map<String, Object> map = new HashMap<String, Object>();
		if (Common.isNotEmpty(storeCode)) {
			map.put("storecode", storeCode);
			map.put("delFlag", "0");
			List<AppAccountInfo> applist = appaccountInfoMapper.selectListByParam(map);
			if (applist.size() > 0) {
				storeInfo.setAppid(applist.get(0).getAppid());
				storeInfo.setAppsecret(applist.get(0).getAppsecret());
			}
		}
		model.addAttribute("store", storeInfo);
		return Common.BACKGROUND_PATH + "/wechat/storeManager/detail";
	}

	/**
	 * 修改门店
	 *
	 * @param
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/editStore")
	@SystemLog(module = "门店管理", methods = "门店管理-修改门店")
	public String editStore(StoreInfo storeInfo) throws Exception {
		ReturnDto returnDto = storeInfoService.editStore(storeInfo);
		return "success";
	}

	/**
	 * 批量删除门店
	 *
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/batchDelStore")
	@SystemLog(module = "门店管理", methods = "门店管理-删除门店")
	public String batchDelStore(@RequestParam(value = "storeCodes[]") String[] storeCodes) {
		List<String> storeCodeList = new ArrayList<String>();
		if (storeCodes != null && storeCodes.length != 0) {
			for (String storeCode : storeCodes) {
				if (Common.isNotEmpty(storeCode) && !"undefined".equals(storeCode))
					storeCodeList.add(storeCode);
			}
		}
		if (storeCodeList.size() > 0) {
			ReturnDto returnDto = storeInfoService.batchDelStore(storeCodeList);
		}
		return "success";
	}

	/**
	 * 加载集团下拉框
	 *
	 * @return
	 */
	@RequestMapping(value = { "/findGroupInfoList2" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> findStoreInfoList2() {
		String result;
		GroupResult groupResult = new GroupResult();
		try {
//			result = HttpUtil.sendGet("http://localhost:8080/notebook/group/getGroupList.json", null);
			result= HttpUtils.HttpGetByUtf("http://localhost:8080/notebook/group","getGroupList.json", null);
			groupResult = com.alibaba.fastjson.JSON.parseObject(result, GroupResult.class);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Object groups[] = new Object[groupResult.getObj().size()];
		if (groupResult.getObj() != null && groupResult.getObj().size() > 0) {
			for (int i = 0; i < groupResult.getObj().size(); i++) {

				Map<String, String> group = new HashMap<String, String>();

				group.put("organizationCode", groupResult.getObj().get(i).getOrganizationCode().toString());
				group.put("organizationName", groupResult.getObj().get(i).getOrganizationName());
				groups[i] = group;
			}
			resultMap.put("success", true);
			resultMap.put("list", groups);
		} else {
			resultMap.put("success", false);
			resultMap.put("list", "");
		}
		return resultMap;
	}

	/**
	 * 根据集团编码查询具体门店信息
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = {"/getStoreInfoBygroup"}, method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	private ReturnDto getStoreInfoBygroup(String groupId) {
		ReturnDto rd = new ReturnDto();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		StoreInfo storeInfo = null;
		try {
			if (Common.isNotEmpty(groupId)) {
				paramMap.put("groupId", groupId.trim());
				List<StoreInfo> storeInfoList = storeInfoMapper.selectListByParam(paramMap);
				if (storeInfoList.size()>0) {
					rd.setCode("0");
					rd.setObj(storeInfoList);
				}else {
					rd.setCode("0");
					rd.setDesc("没查到结果");
				}
			}
		} catch (Exception e) {
			rd.setCode("1");
			rd.setDesc(e.getMessage());
			e.printStackTrace();
		}
		return rd;
	}
}
