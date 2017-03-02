package com.wechat.manage.controller.system;


import com.alibaba.fastjson.JSON;
import com.wechat.manage.controller.index.BaseController;
import  com.wechat.manage.pojo.system.vo.AuthorizationStoreDto;
import com.wechat.manage.pojo.system.vo.UserAuthorizationStoreDto;
import com.wechat.manage.pojo.system.entity.UserAuthorizationStore;
import com.wechat.manage.pojo.system.vo.UserBaseInfoDto;
import com.wechat.manage.service.system.intf.UserAuthorizationStoreService;
import com.wechat.manage.utils.Common;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author zhaocj 2016-12-07
 */
@Controller
@RequestMapping("/userAuthorizatioStore/")
public class UserAuthorizatioStoreController extends BaseController {

	@Autowired
	private UserAuthorizationStoreService userAuthorizationStoreService;

	/**
	 *
	 * @author lanyuan Email：mmm333zzz520@163.com date：2014-2-19
	 * @param name
	 * @return
	 */
	@RequestMapping("test")
	@ResponseBody
	public String isExist(String name) {
			return "OK";
	}


	@RequestMapping("userAuthorizatioStoreweb")
	public String permissionsweb(String userId, Model model) {
		UserBaseInfoDto userBaseInfoDto= getCurUserInfo();
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId",userId);//客户端 选中的用户ID
		paramMap.put("userNumber",userBaseInfoDto.getUserId());//登陆用户的ID
		List<UserAuthorizationStoreDto> userAuthorizationStoreDtoList = null;
		try{
//			userAuthorizationStoreDtoList =	userAuthorizationStoreService.selectListByUserId(paramMap);
			userAuthorizationStoreDtoList =	userAuthorizationStoreService.selectListByUserIdweb(paramMap);

		}catch (Exception e){

		}

		model.addAttribute("userAuthorizatioStoreList", userAuthorizationStoreDtoList);
		model.addAttribute("userNumber",userId);//客户端 选中的用户ID
		System.out.println(JSON.toJSONString(userAuthorizationStoreDtoList));
		return Common.BACKGROUND_PATH + "/system/userAuthorizationStore/userAuthorizationStore";
	}


	@RequestMapping("userAuthorizatioStore")
	public String permissions(String userId, Model model) {
		UserBaseInfoDto userBaseInfoDto= getCurUserInfo();
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId",userId);//客户端 选中的用户ID
		paramMap.put("userNumber",userBaseInfoDto.getUserId());//登陆用户的ID
		List<UserAuthorizationStoreDto> userAuthorizationStoreDtoList = null;
		try{
//			userAuthorizationStoreDtoList =	userAuthorizationStoreService.selectListByUserId(paramMap);
			userAuthorizationStoreDtoList =	userAuthorizationStoreService.selectListByUserIdweb(paramMap);

		}catch (Exception e){

		}

		model.addAttribute("userAuthorizatioStoreList", userAuthorizationStoreDtoList);
		model.addAttribute("userNumber",userId);//客户端 选中的用户ID
		System.out.println(JSON.toJSONString(userAuthorizationStoreDtoList));
		return Common.BACKGROUND_PATH + "/system/userAuthorizationStore/userAuthorizationStore";
	}


	/**
	 *
	 * @param userId
	 * @param model
     * @return
     */
	@RequestMapping("getUserAuthorizatioStore")
	public String getUserAuthorizatioStore(String userId, Model model) {
		Map<String,Object> paramMaps = new HashMap<String, Object>();
		paramMaps.put("userId",userId);
		List<UserAuthorizationStore> userAuthorizationStoreDtoList = null;
		try{
			userAuthorizationStoreDtoList =	userAuthorizationStoreService.getselectListByUserId(paramMaps);
		}catch (Exception e){

		}

		model.addAttribute("userAuthorizatioStoreList", userAuthorizationStoreDtoList);
		System.out.println(JSON.toJSONString(userAuthorizationStoreDtoList));
		return Common.BACKGROUND_PATH + "/system/userAuthorizationStore/userAuthorizationStore";
	}


	@ResponseBody
	@RequestMapping(value = "adduserAuthorizationStore" ,method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String addUserRes(HttpServletRequest request) throws Exception {
		try{
		String s = getData(request);
		JSONArray array = JSONArray.fromObject(s);
		List<UserAuthorizationStoreDto> dtoList = new ArrayList<UserAuthorizationStoreDto>();
		for(int i = 0; i < array.size(); i++){
			net.sf.json.JSONObject jsonObject = array.getJSONObject(i);
			AuthorizationStoreDto dto =  JSON.parseObject(jsonObject.toString(),AuthorizationStoreDto.class);
			UserAuthorizationStoreDto userAuthorizationStoreDto = new UserAuthorizationStoreDto();
			userAuthorizationStoreDto.setIsLoseEfficacy(Integer.valueOf(dto.getIsLoseEfficacy()));
			userAuthorizationStoreDto.setUserId(dto.getUserId().trim());
			userAuthorizationStoreDto.setStoreCode(dto.getStoreCode().trim());
			userAuthorizationStoreDto.setBusinessName(dto.getBusinessName().trim());
			dtoList.add(userAuthorizationStoreDto);
		}
			userAuthorizationStoreService.addUserAuthorizationStore(dtoList);
		}catch (Exception e){
			return "failure";
		}
		return "success";
	}


	/**
	 * 获取流数据
	 *
	 * @param request
	 * @return
	 */
	protected String getData(HttpServletRequest request) {
		InputStream stream = null;
		try {
			stream = request.getInputStream();
			Reader reader = new InputStreamReader(request.getInputStream(), "UTF-8");
			StringBuilder response = new StringBuilder();
			final char[] buff = new char[1024];
			int read = 0;
			while ((read = reader.read(buff)) > 0) {
				response.append(buff, 0, read);
			}

			return response.toString();
		} catch (Exception e) {
			e.getMessage();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}


}