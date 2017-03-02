package com.wechat.manage.controller.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.wechat.manage.controller.index.BaseController;
import com.wechat.manage.pojo.wechat.vo.CumulateUserDto;
import com.wechat.manage.pojo.wechat.vo.StatisticsAllUserDto;
import com.wechat.manage.pojo.wechat.vo.StatisticsUserDto;
import com.wechat.manage.pojo.wechat.vo.YesterdayUserInfoDto;
import com.wechat.manage.pojo.system.entity.AppAccountInfo;
import com.wechat.manage.mapper.system.AppAccountInfoMapper;
import com.wechat.manage.service.wechat.intf.StatisticsService;
import com.wechat.manage.utils.Common;
import com.wechat.manage.utils.HttpUtils;
import com.wechat.manage.utils.RedisUtil;
import com.wechat.manage.service.util.WechatUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/statistics")
public class StatisticsController extends BaseController {
	
	
	private final static Logger logger = LoggerFactory.getLogger(StatisticsController.class);
	@Autowired
	private WechatUtil tokenUtil;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private AppAccountInfoMapper appAccountInfoMapper;
	@Autowired
	private StatisticsService statisticsService;

	
	
	/**
	 * 用户分析，用户增长页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/UserAnalysis")
	public String userAnalysis(HttpServletRequest request,HttpServletResponse response,Model model){

		String access_token = tokenUtil.getAccessToken("wxec6478a757ee2b6e","d53d3d8687f3e5d23971514469244c95");
//		String access_token =getAccessToken();
		String userSummaryUrl = "https://api.weixin.qq.com/datacube/getusersummary?access_token=" + access_token;
		
		Map<Object,Object> map = new HashMap<Object, Object>();
		List<Object> list = new ArrayList<Object>();
		map.put("begin_date", "2016-12-11");
		map.put("end_date", "2016-12-17");
		long time = System.currentTimeMillis();
		logger.info("调查询用户增减数据接口入参:{}",JSON.toJSONString(map));
		logger.info("调查询用户增减数据接口开始时间:{}",time);
//		String result = HttpUtils.doPost(userSummaryUrl, JSON.toJSONString(map));
		String result = "{'list': [{'ref_date': '2016-12-05','user_source': 0,'new_user': 0,'cancel_user': 0},"
				+ "{'ref_date': '2016-12-06','user_source': 30,'new_user': 2,'cancel_user': 0}]}";
		logger.info("调查询用户增减数据接口结束时间:{},共耗时:{}",System.currentTimeMillis(),System.currentTimeMillis()-time);
		logger.info("调查询用户增减数据接口出参:{}",result);
		String userCount = "{'list': [{'ref_date': '2016-12-05','user_source': 0,'cumulate_user': 0},{'ref_date': '2016-12-06','user_source': 0,'cumulate_user': 2}]}";
		
		JSONObject json = JSON.parseObject(result);
		JSONObject jsonCount = JSON.parseObject(userCount);
		JSONArray jsonArr = JSON.parseArray(json.getString("list"));
		JSONArray jsonArrCount = JSON.parseArray(jsonCount.getString("list"));
		StringBuffer sb = new StringBuffer();
		for(Object obj : jsonArr){
			JSONObject jsonObj = JSON.parseObject(obj.toString());
			for(Object objCount : jsonArrCount){
				JSONObject jsonObjCount = JSON.parseObject(objCount.toString());
				if(jsonObj.getString("ref_date").equals(jsonObjCount.getString("ref_date"))){
					model.addAttribute("ref_date", jsonObj.getString("ref_date"));
					model.addAttribute("cumulate_user", jsonObjCount.getString("cumulate_user"));
					model.addAttribute("cancel_user", jsonObj.getString("cancel_user"));
					model.addAttribute("new_user", jsonObj.getString("new_user"));
				}
			}
		}
		logger.info(Common.BACKGROUND_PATH + "/wechat/statistics/useranalysis");
		return Common.BACKGROUND_PATH + "/wechat/statistics/useranalysis";
	}
	
	/**
	 * 用户分析，用户属性页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/userAttribute")
	public String userAttribute(HttpServletRequest request,HttpServletResponse response){
		String xx = request.getParameter("mycontainer");
		String ddd = request.getParameter("editorValue");
		Enumeration<String> aa = request.getParameterNames();
		if(aa.hasMoreElements()){
			String o = aa.nextElement();
			System.out.println(o);
		}
		System.out.println(xx);
		return Common.BACKGROUND_PATH + "/wechat/statistics/userAttribute";
	}

	/**
	 * 用户分析，用户增长页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/userIncreast")
	public String userIncreast(HttpServletRequest request,HttpServletResponse response){

		return Common.BACKGROUND_PATH + "/wechat/statistics/useranalysis";
	}
	
	/**
	 * 获取用户增减数据
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getUserSummary")
	@ResponseBody
	public String getUserSummary(HttpServletRequest request,HttpServletResponse response){
		String access_token = tokenUtil.getAccessToken("wxec6478a757ee2b6e","d53d3d8687f3e5d23971514469244c95");
//		logger.info("access_token:" +access_token );
//		String access_token = getAccessToken();
		String userSummaryUrl = "https://api.weixin.qq.com/datacube/getusersummary?access_token=" + access_token;
		String begin_date = request.getParameter("begin_date");
		String end_date = request.getParameter("end_date");
		logger.info("begindate"+begin_date+"----"+end_date);
		Map<Object,Object> map = new HashMap<Object, Object>();
		List<Object> list = new ArrayList<Object>();
		map.put("begin_date", begin_date);
		map.put("end_date", end_date);
		long time = System.currentTimeMillis();
		logger.info("调查询用户增减数据接口入参:{}",JSON.toJSONString(map));
		logger.info("调查询用户增减数据接口开始时间:{}",time);
		String result1 = HttpUtils.doPost(userSummaryUrl, JSON.toJSONString(map));
		logger.info("result1:" + result1);
		String result = "{'list': [{'ref_date': '2016-12-11','user_source': 0,'new_user': 3,"
				+ "'cancel_user': 0},{'ref_date': '2016-12-10','user_source': 30,'new_user':"
				+ " 1,'cancel_user': 0},{'ref_date': '2016-12-09','user_source': 30,'new_user'"
				+ ": 1,'cancel_user': 1},{'ref_date': '2016-12-08','user_source': 30,'new_user'"
				+ ": 2,'cancel_user': 0},{'ref_date': '2016-12-07','user_source': 30,'new_user'"
				+ ": 1,'cancel_user': 0},{'ref_date': '2016-12-06','user_source': 30,'new_user'"
				+ ": 2,'cancel_user': 1},{'ref_date': '2016-12-05','user_source': 30,'new_user'"
				+ ": 1,'cancel_user': 0}]}";
		logger.info("调查询用户增减数据接口结束时间:{},共耗时:{}",System.currentTimeMillis(),System.currentTimeMillis()-time);
		logger.info("调查询用户增减数据接口出参:{}",result);
		String userCount = "{'list': [{'ref_date': '2016-12-11','user_source': 0,'cumulate_user"
				+ "': 9},{'ref_date': '2016-12-10','user_source': 0,'cumulate_user': 6},"
				+ "{'ref_date': '2016-12-09','user_source': 0,'cumulate_user': 5},"
				+ "{'ref_date': '2016-12-08','user_source': 0,'cumulate_user': 5},"
				+ "{'ref_date': '2016-12-07','user_source': 0,'cumulate_user': 3},"
				+ "{'ref_date': '2016-12-06','user_source': 0,'cumulate_user': 2},"
				+ "{'ref_date': '2016-12-05','user_source': 0,'cumulate_user': 1}]}";
		
		JSONObject json = JSON.parseObject(result);
		JSONObject jsonCount = JSON.parseObject(userCount);
		JSONArray jsonArr = JSON.parseArray(json.getString("list"));
		JSONArray jsonArrCount = JSON.parseArray(jsonCount.getString("list"));
		StringBuffer sb = new StringBuffer();
		int i = 0;
		for(Object obj : jsonArr){
			JSONObject jsonObj = JSON.parseObject(obj.toString());
			for(Object objCount : jsonArrCount){
				JSONObject jsonObjCount = JSON.parseObject(objCount.toString());
				if(jsonObj.getString("ref_date").equals(jsonObjCount.getString("ref_date"))){
					sb.append("<tr id='tr"+i+"'>" + "<td class='table_cell'>" + jsonObj.getString("ref_date") + "</td>")
					.append("<td class='table_cell tr js_new_user'>" + jsonObj.getString("new_user") + "</td>")
					.append("<td class='table_cell tr js_cancel_user'>" + jsonObj.getString("cancel_user") + "</td>")
					.append("<td class='table_cell tr js_netgain_user'>" + String.valueOf(Integer.valueOf(jsonObj.getString("new_user"))-Integer.valueOf(jsonObj.getString("cancel_user"))) + "</td>")
					.append("<td class='table_cell tr js_cumulate_user'>" + jsonObjCount.getString("cumulate_user") + "</td></tr>");
					i ++;
					break;
				}
			}
			
		}
		return JSON.toJSONString(sb);
	}
	
	/**
	 * 获取累计用户数据     
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getUserCumulate")
	@ResponseBody
	public String getUserCumulate(HttpServletRequest request,HttpServletResponse response){
		String access_token = tokenUtil.getAccessToken("wxb501c82fcb37f54e","9bebdf29aad086b4ae737709c5a3ef20");
		String userSummaryUrl = "https://api.weixin.qq.com/datacube/getusercumulate?access_token=" + access_token;
		
		Map<Object,Object> map = new HashMap<Object, Object>();
		map.put("begin_date", "2016-12-01");
		map.put("end_date", "2015-12-06");
		long time = System.currentTimeMillis();
		logger.info("调查询累计用户数据接口入参:{}",JSON.toJSONString(map));
		logger.info("调查询累计用户数据接口开始时间:{}",time);
		String result = HttpUtils.doPost(userSummaryUrl, JSON.toJSONString(map));
		logger.info("调查询累计用户数据口结束时间:{},共耗时:{}",System.currentTimeMillis(),System.currentTimeMillis()-time);
		logger.info("调查询累计用户数据接口出参:{}",result);
		return result;
	}

	
	@RequestMapping("/getCurveData")
	@ResponseBody
	public Map<Object,Object> getCurveData(HttpServletRequest request,HttpServletResponse response){
		String access_token = tokenUtil.getAccessToken("wxb501c82fcb37f54e","9bebdf29aad086b4ae737709c5a3ef20");
		String userSummaryUrl = "https://api.weixin.qq.com/datacube/getusersummary?access_token=" + access_token;
		Map<Object,Object> returnStr = new HashMap<Object, Object>();
		String dateStr = request.getParameter("dateStr");
		String flag = request.getParameter("flag");
		String startDate = request.getParameter("begin_date");
		String endDate = request.getParameter("end_date");
		try {
			Map<Object,Object> map = new HashMap<Object, Object>();
			List<Object> list = new ArrayList<Object>();
			map.put("begin_date", startDate);
			map.put("end_date", endDate);
			long time = System.currentTimeMillis();
			logger.info("调查询用户增减数据接口入参:{}",JSON.toJSONString(map));
			logger.info("调查询用户增减数据接口开始时间:{}",time);
			String result = HttpUtils.doPost(userSummaryUrl, JSON.toJSONString(map));
			String resultStr = "{'list': [{'ref_date': '2016-12-05','user_source': 0,'new_user': 1,'cancel_user': 0},"
					+ "{'ref_date': '2016-12-06','user_source': 30,'new_user': 1,'cancel_user': 0}]}";
//			logger.info("调查询用户增减数据接口结束时间:{},共耗时:{}",System.currentTimeMillis(),System.currentTimeMillis()-time);
//			logger.info("调查询用户增减数据接口出参:{}",result);
			String userCount = "{'list': [{'ref_date': '2016-12-05','user_source': 0,'cumulate_user': 2},{'ref_date': '2016-12-06','user_source': 0,'cumulate_user': 3}]}";
			
			JSONObject json = JSON.parseObject(resultStr);
			JSONArray jsonArr = JSON.parseArray(json.getString("list"));
			StringBuffer sbTime = new StringBuffer();
			StringBuffer sbData = new StringBuffer();
			for(Object obj : jsonArr){
				JSONObject jsonObj = JSON.parseObject(obj.toString());
				sbTime.append(jsonObj.getString("ref_date")+",");
				sbData.append(jsonObj.getString("new_user")+",");
			}
			returnStr.put("list",jsonArr);
			String resultDate = dateTime(dateStr);
			returnStr.put("date",resultDate);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("服务器内部错误:{}",e);
		}
		return returnStr;
	}
	
	public static String dateTime(String dateStr){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		cale.set(Calendar.DATE, cale.get(Calendar.DATE) - 1);
		String dataStr = sdf.format(cale.getTime());
		cale.set(Calendar.DATE, cale.get(Calendar.DATE)-Integer.parseInt(dateStr));
		String dataStr1 = sdf.format(cale.getTime());
		String returnDate = dataStr1 + " - " +dataStr;
		return returnDate;
	}

	@RequestMapping("/getMap")
	@ResponseBody
	public String GetMap(HttpServletRequest request,HttpServletResponse response){
		return "";
	}






	public String getAccessToken() {
		Session session = SecurityUtils.getSubject().getSession();
		String storecode = redisUtil.get(Common.USER_STORE_K + session.getAttribute("userSessionId"), "");
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("storecode", storecode);
		List<AppAccountInfo> appAccountInfoList = appAccountInfoMapper.selectListByParam(paraMap);
		String appid = appAccountInfoList.get(0).getAppid();
		String appsecret = appAccountInfoList.get(0).getAppsecret();
		String accessToken = tokenUtil.getAccessToken(appid, appsecret);
		return accessToken;
	}


	@RequestMapping("/getUserSummary1")
	@ResponseBody
	public String getUserSummary1(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String access_token = tokenUtil.getAccessToken("wxb501c82fcb37f54e","9bebdf29aad086b4ae737709c5a3ef20");
//		String access_token = getAccessToken();
//		String access_token = "POI3xWHImR68S2DNzE4Cn_u8so-B5KoE87RCVKv5s2NVk6AeTTWYkFGwrpxsyxczHTRgWsD2d-CitiLFCgJA2ExA1RTIH9xJAgcI_0-wgJDO8Ma0iQVdtZdWWPBy8K2gEFEdAHAYZY";
		String begin_date = request.getParameter("begin_date");
		String end_date = request.getParameter("end_date");
		Map<String,Object>paramMap = new HashMap<String,Object>();
		paramMap.put("begin_date",begin_date);
		paramMap.put("end_date",end_date);
		paramMap.put("access_token",access_token);
		List<StatisticsUserDto> summaryList =  statisticsService.getUserSummary(paramMap);
		List<CumulateUserDto> cumulatteList =  statisticsService.getUserCumulatte(paramMap);
		StringBuffer sb = new StringBuffer();
		int i = 0;

		for (StatisticsUserDto statisticsUserDto : summaryList) {
			for (CumulateUserDto cumulateUserDto : cumulatteList) {
				if(cumulateUserDto.getRef_date().equals(statisticsUserDto.getRef_date())){
					sb.append("<tr id='tr"+i+"'>" + "<td class='table_cell'>" + statisticsUserDto.getRef_date() + "</td>")
							.append("<td class='table_cell tr js_new_user'>" + statisticsUserDto.getNew_user() + "</td>")
							.append("<td class='table_cell tr js_cancel_user'>" + statisticsUserDto.getCancel_user() + "</td>")
							.append("<td class='table_cell tr js_netgain_user'>" + (Integer.valueOf(statisticsUserDto.getNew_user())-Integer.valueOf(statisticsUserDto.getCancel_user())) + "</td>")
							.append("<td class='table_cell tr js_cumulate_user'>" + cumulateUserDto.getCumulate_user() + "</td></tr>");
					i ++;
					break;
				}
			}
		}

		return JSON.toJSONString(sb);
	}



	@RequestMapping("/getCurveData1")
	@ResponseBody
	public Map<Object,Object> getCurveData1(HttpServletRequest request,HttpServletResponse response){
		String access_token = tokenUtil.getAccessToken("wxec6478a757ee2b6e","d53d3d8687f3e5d23971514469244c95");
//		String access_token = "7Dp31xbeRlm4YPVnWDs_4-wpNE0OmkmEPp8rklE_fLO9cXQ_X0disxidzY_xxD5rFbB6ZHaeRbm_ke0kOMpjIkiHdWhz4it3nsYk8go2C26kh12_l6wJygKSdRwYLcRJGXReADARIC";
//		String userSummaryUrl = "https://api.weixin.qq.com/datacube/getusersummary?access_token=" + access_token;
		Map<Object,Object> returnStr = new HashMap<Object, Object>();
		String dateStr = request.getParameter("dateStr");
		String flag = request.getParameter("flag");
		String begin_date = request.getParameter("begin_date");
		String end_date = request.getParameter("end_date");
		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("begin_date", begin_date);
		map.put("begin_date", "2016-12-11");
//		map.put("end_date", end_date);
		map.put("end_date", "2016-12-17");
		map.put("access_token",access_token);
		try {
			List<StatisticsAllUserDto> dtoList = statisticsService.getCurveData1(map);

			
			List<Object> list = new ArrayList<Object>();

			String resultStr = "{'list': [{'ref_date': '2016-12-05','user_source': 0,'new_user': 0,'cancel_user': 0},"
					+ "{'ref_date': '2016-12-06','user_source': 30,'new_user': 2,'cancel_user': 0}]}";
//			logger.info("调查询用户增减数据接口结束时间:{},共耗时:{}",System.currentTimeMillis(),System.currentTimeMillis()-time);
//			logger.info("调查询用户增减数据接口出参:{}",result);
//			String userCount = "{'list': [{'ref_date': '202-05','user_source': 0,'cumulate_user': 0},{'ref_date': '2016-12-06','user_source': 0,'cumulate_user': 2}]}";

			JSONObject json = JSON.parseObject(resultStr);
			JSONArray jsonArr = JSON.parseArray(json.getString("list"));
			StringBuffer sbTime = new StringBuffer();
			StringBuffer sbData = new StringBuffer();

			for (StatisticsAllUserDto dto : dtoList) {
				sbTime.append(dto.getRef_date()+",");
				sbData.append(dto.getNew_user()+",").append(dto.getCancel_user()+",").append(dto.getJz_user()+",").append(dto.getCumulate_user()+",");
			}
//			for(Object obj : jsonArr){
//				JSONObject jsonObj = JSON.parseObject(obj.toString());
//				sbTime.append(jsonObj.getString("ref_date")+",");
//				sbData.append(jsonObj.getString("new_user")+",");
//			}
			returnStr.put("list",dtoList);
			String resultDate = dateTime(dateStr);
			returnStr.put("date",resultDate);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("服务器内部错误:{}",e);
		}
		return returnStr;
	}
	@RequestMapping("/getYesData")
	@ResponseBody
	public Map<Object,Object> getYesData(HttpServletRequest request,HttpServletResponse response){
		YesterdayUserInfoDto yes = new YesterdayUserInfoDto();
		yes.setName("new_user");
		yes.setValue(1);
		YesterdayUserInfoDto no = new YesterdayUserInfoDto();
		no.setName("cancel_user");
		no.setValue(0);
		YesterdayUserInfoDto a = new YesterdayUserInfoDto();
		a.setName("jz_user");
		a.setValue(1);
		YesterdayUserInfoDto b = new YesterdayUserInfoDto();
		b.setName("cumulate_user");
		b.setValue(1);
		List<YesterdayUserInfoDto> ly = new ArrayList<YesterdayUserInfoDto>();
		ly.add(yes);
		ly.add(no);
		ly.add(a);
		ly.add(b);
		Map<Object,Object> oo = new HashMap<Object,Object>();
		
		oo.put("list", ly);
		
		return oo;
	}
}