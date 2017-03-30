package com.wechat.manage.controller.system;

import com.wechat.manage.controller.index.BaseController;
import com.wechat.manage.plugin.PageView;
import com.wechat.manage.utils.Common;
import com.wechat.manage.vo.DataTableParams;
import com.wechat.manage.vo.DataTableResult;
import com.wechat.manage.annotation.SystemLog;
import com.wechat.manage.utils.JsonUtil;
import com.wechat.manage.pojo.system.vo.ReturnDto;
import com.wechat.manage.pojo.system.vo.UserBaseInfoDto;
import com.wechat.manage.pojo.system.entity.*;
import  com.wechat.exception.SystemException;
import com.wechat.manage.mapper.system.RoleMapper;
import com.wechat.manage.mapper.system.UserMapper;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.wechat.manage.utils.RedisUtil;
import com.wechat.manage.utils.POIUtils;
import com.wechat.manage.utils.PasswordHelper;

/**
 * 
 * @author lanyuan 2014-11-19
 * @Email: mmm333zzz520@163.com
 * @version 3.0v
 */
@Controller
@RequestMapping("/user/")
public class UserController extends BaseController {

	private static Logger logger = Logger.getLogger(UserController.class);

	@Autowired
    private RedisUtil redisUtil;


	@Inject
	private UserMapper userMapper;

	@Inject
	private RoleMapper roleMapper;
	
	@RequestMapping("list")
	public String listUI(Model model, Integer id) throws Exception {
		model.addAttribute("res", findByRes());
		model.addAttribute("menuId",id);
		return Common.BACKGROUND_PATH + "/system/user/list";
	}

	@RequestMapping("getButton")
	@ResponseBody
	public List getButton(){
		return findByRes();
	}

	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage(String pageNow,
							   String pageSize, String column, String sort) throws Exception {
		UserFormMap userFormMap = getFormMap(UserFormMap.class);
		userFormMap=toFormMap(userFormMap, pageNow, pageSize,userFormMap.getStr("orderby"));
		userFormMap.put("column", column);
		userFormMap.put("sort", sort);
        pageView.setRecords(userMapper.findUserPage(userFormMap));//不调用默认分页,调用自已的mapper中findUserPage
        return pageView;
	}

	@ResponseBody
	@RequestMapping("findByPage2")
	public DataTableResult findByPage(DataTableParams dataTableParams) throws Exception {
		UserFormMap userFormMap = getFormMap(UserFormMap.class);
		UserBaseInfoDto curUserInfo = getCurUserInfo();
		//当前用户子用户
		userFormMap.put("parentId", curUserInfo.getUserId());
		
		String pageNow = ((dataTableParams.getiDisplayStart()+1)%dataTableParams.getiDisplayLength()>0?(dataTableParams.getiDisplayStart()+1)/dataTableParams.getiDisplayLength()+1:(dataTableParams.getiDisplayStart()+1)/dataTableParams.getiDisplayLength())+"";
		userFormMap=toFormMap(userFormMap, pageNow, dataTableParams.getiDisplayLength()+"",userFormMap.getStr("orderby"));
		pageView.setRecords(userMapper.findUserPage(userFormMap));//不调用默认分页,调用自已的mapper中findUserPage
		DataTableResult<UserFormMap> dataTableResult = new DataTableResult<UserFormMap>();
		dataTableResult.setsEcho(dataTableParams.getsEcho());
		dataTableResult.setAaData(userMapper.findUserPage(userFormMap));
		dataTableResult.setiTotalDisplayRecords(pageView.getRowCount());
		dataTableResult.setiTotalRecords(pageView.getRowCount());
		return dataTableResult;
	}
	
	@RequestMapping("/export")
	public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String fileName = "用户列表";
		UserFormMap userFormMap = findHasHMap(UserFormMap.class);
		//exportData = 
		// [{"colkey":"sql_info","name":"SQL语句","hide":false},
		// {"colkey":"total_time","name":"总响应时长","hide":false},
		// {"colkey":"avg_time","name":"平均响应时长","hide":false},
		// {"colkey":"record_time","name":"记录时间","hide":false},
		// {"colkey":"call_count","name":"请求次数","hide":false}
		// ]
		String exportData = userFormMap.getStr("exportData");// 列表头的json字符串

		List<Map<String, Object>> listMap = JsonUtil.parseJSONList(exportData);

		List<UserFormMap> lis = userMapper.findUserPage(userFormMap);
		POIUtils.exportToExcel(response, listMap, lis, fileName);
	}

	@RequestMapping("addUI")
	public String addUI(Model model) throws Exception {
		RoleFormMap roleFormMap = getFormMap(RoleFormMap.class);
		Object userId = roleFormMap.get("userId");
		if(null!=userId){
			List<RoleFormMap> list = roleMapper.seletUserRole(roleFormMap);
			String ugid = "";
			for (RoleFormMap ml : list) {
				ugid += ml.get("id")+",";
			}
			ugid = Common.trimComma(ugid);
			model.addAttribute("txtRoleSelect", ugid);
			model.addAttribute("userRole", list);
			if(StringUtils.isNotBlank(ugid)){
				roleFormMap.put("where", " where id not in ("+ugid+")");
			}
		}
		List<RoleFormMap> roles = roleMapper.findByWhere(roleFormMap);
		model.addAttribute("role", roles);
		return Common.BACKGROUND_PATH + "/system/user/add";
	}

	@ResponseBody
	@RequestMapping("addEntity")
	@SystemLog(module="系统管理",methods="用户管理-新增用户")//凡需要处理业务逻辑的.都需要记录操作日志
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String addEntity(String txtGroupsSelect){
		//,String offsetType,String businessName,String organizationCode,String organizationName
		try {
			UserFormMap userFormMap = getFormMap(UserFormMap.class);
			userFormMap.put("txtGroupsSelect", txtGroupsSelect);
			PasswordHelper passwordHelper = new PasswordHelper();
			userFormMap.set("password","123456789");
			passwordHelper.encryptPassword(userFormMap);
			userFormMap.put("parentId",getCurUserInfo().getUserId());
			userMapper.addEntity(userFormMap);//新增后返回新增信息
			if (!Common.isEmpty(txtGroupsSelect)) {
				String[] txt = txtGroupsSelect.split(",");
				UserGroupsFormMap userGroupsFormMap = new UserGroupsFormMap();
				for (String roleId : txt) {
					userGroupsFormMap.put("userId", userFormMap.get("id"));
					userGroupsFormMap.put("roleId", roleId);
					userMapper.addEntity(userGroupsFormMap);
				}
			}
		} catch (Exception e) {
			 throw new SystemException("添加账号异常");
		}
		return "success";
	}

	@ResponseBody
	@RequestMapping("deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="系统管理",methods="用户管理-删除用户")//凡需要处理业务逻辑的.都需要记录操作日志
	public String deleteEntity() throws Exception {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			userMapper.deleteByAttribute("userId", id, UserGroupsFormMap.class);
			userMapper.deleteByAttribute("userId", id, ResUserFormMap.class);
			userMapper.deleteByAttribute("id", id, UserFormMap.class);
		}
		return "success";
	}

	@RequestMapping("editUI")
	public String editUI(Model model) throws Exception {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			model.addAttribute("user", userMapper.findbyFrist("id", id, UserFormMap.class));
		}
		return Common.BACKGROUND_PATH + "/system/user/edit";
	}

	@ResponseBody
	@RequestMapping("editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="系统管理",methods="用户管理-修改用户")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editEntity(String txtGroupsSelect) throws Exception {
		UserFormMap userFormMap = getFormMap(UserFormMap.class);
		userFormMap.put("txtGroupsSelect", txtGroupsSelect);
		userMapper.editEntity(userFormMap);
		userMapper.deleteByAttribute("userId", userFormMap.get("id")+"", UserGroupsFormMap.class);
		if(!Common.isEmpty(txtGroupsSelect)){
			String[] txt = txtGroupsSelect.split(",");
			for (String roleId : txt) {
				UserGroupsFormMap userGroupsFormMap = new UserGroupsFormMap();
				userGroupsFormMap.put("userId", userFormMap.get("id"));
				userGroupsFormMap.put("roleId", roleId);
				userMapper.addEntity(userGroupsFormMap);
			}
		}
		return "success";
	}
	/**
	 * 验证账号是否存在
	 * 
	 * @author lanyuan Email：mmm333zzz520@163.com date：2014-2-19
	 * @param name
	 * @return
	 */
	@RequestMapping("isExist")
	@ResponseBody
	public boolean isExist(String name) {
		UserFormMap account = userMapper.findbyFrist("accountName", name, UserFormMap.class);
		if (account == null) {
			return true;
		} else {
			return false;
		}
	}
	
	//密码修改
	@RequestMapping("updatePassword")
	public String updatePassword(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/system/user/updatePassword";
	}
	
	//保存新密码
	@RequestMapping("editPassword")
	@ResponseBody
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="系统管理",methods="用户管理-修改密码")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editPassword() throws Exception {
		// 当验证都通过后，把用户信息放在session里
		UserFormMap userFormMap = getFormMap(UserFormMap.class);
		userFormMap.put("password", userFormMap.get("newpassword"));
		//这里对修改的密码进行加密
		PasswordHelper passwordHelper = new PasswordHelper();
		passwordHelper.encryptPassword(userFormMap);
		userMapper.editEntity(userFormMap);
		return "success";
	}


	@RequestMapping("test")
	public String test(HttpServletRequest request) throws Exception {
		return Common.BACKGROUND_PATH + "/system/user/userAuthorizationStore";
//		return "redirect:index.shtml";
	}

	@RequestMapping("testlogin")//, method = RequestMethod.GET, produces = "text/html; charset=utf-8"
	@ResponseBody
	public String testlogin(String storeNo, String userId) throws Exception {
		boolean b= redisUtil.set(Common.USER_STORE_K+userId,storeNo);
		logger.debug("存入 redis key:"+Common.USER_STORE_K+userId+" 门店号："+storeNo +"存入是否成功："+b);
//		测试用
//		String resutl=redisUtil.get(Common.USER_STORE_K+userId,"");
//		logger.debug("key:"+Common.USER_S	TORE_K+userId+" 门店号："+storeNo);
		return "/index.shtml";
	}



	@RequestMapping("getUserInfo")//, method = RequestMethod.GET, produces = "text/html; charset=utf-8"
	@ResponseBody
	public ReturnDto getUserInfo(){
		ReturnDto rd=new ReturnDto();
		Map map=new HashMap();
		try {
			Session session = SecurityUtils.getSubject().getSession();
			String userId=session.getAttribute("userSessionId").toString();
			String username = (String)SecurityUtils.getSubject().getPrincipal();
			map.put("userId", userId);
			map.put("accountName",username);
			String resutl1=redisUtil.getKey(Common.USER_STORE_K+userId,"");
			logger.debug("取 redis key:"+Common.USER_STORE_K+userId+" 取出结果："+resutl1);
			map.put("storeCode",resutl1);
			rd.setObj(map);
			rd.setCode("0");
		}catch (Exception e){
			rd.setCode("1");
			rd.setDesc(e.getMessage());
			rd.setObj(new HashMap());
			e.printStackTrace();
		}
		return rd;
	}



	@ResponseBody
	@RequestMapping("getUserInfobyid")
	public ReturnDto getUserInfobyid() throws Exception {
		ReturnDto rd=new ReturnDto();
		UserFormMap userFormMap = getFormMap(UserFormMap.class);
		userFormMap.put("id",getCurUserInfo().getUserId());
	    List<UserFormMap> lst=userMapper.findUserPage(userFormMap);
		rd.setCode("0");
		rd.setObj(lst);
		return rd;
	}
}