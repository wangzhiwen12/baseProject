package com.wechat.manage.controller.index;

import com.wechat.manage.mapper.system.ResourcesMapper;
import com.wechat.manage.mapper.system.UserLoginMapper;
import com.wechat.manage.pojo.system.entity.ResFormMap;
import com.wechat.manage.pojo.system.entity.UserAuthorizationStore;
import com.wechat.manage.pojo.system.entity.UserFormMap;
import com.wechat.manage.pojo.system.entity.UserLoginFormMap;
import com.wechat.manage.service.system.intf.UserAuthorizationStoreService;
import com.wechat.manage.utils.*;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 进行管理后台框架界面的类
 *
 * @author lanyuan 2015-04-05
 * @Email: mmm333zzz520@163.com
 * @version 3.0v
 * @mod  Ekko 2015-09-07
 */
@Controller
@RequestMapping("/")
public class BackgroundController extends BaseController {
	private static Logger logger = Logger.getLogger(BackgroundController.class);
	@Inject
	private ResourcesMapper resourcesMapper;

	@Inject
	private UserLoginMapper userLoginMapper;

	@Autowired
	private UserAuthorizationStoreService userAuthorizationStoreService;

	@Autowired
	private RedisUtil redisUtil;

	/**
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
	public String login(HttpServletRequest request) {
			request.removeAttribute("error");
		return "/login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
	public String login(String username, String password, HttpServletRequest request,Model model) {
		try {
			if (!request.getMethod().equals("POST")) {
				request.setAttribute("error", "支持POST方法提交！");
			}
			if (Common.isEmpty(username) || Common.isEmpty(password)) {
				request.setAttribute("error", "用户名或密码不能为空！");
				return "/login";
			}
			// 想要得到 SecurityUtils.getSubject()　的对象．．访问地址必须跟ｓｈｉｒｏ的拦截地址内．不然后会报空指针
			Subject user = SecurityUtils.getSubject();
			// 用户输入的账号和密码,,存到UsernamePasswordToken对象中..然后由shiro内部认证对比,
			// 认证执行者交由ShiroDbRealm中doGetAuthenticationInfo处理
			// 当以上认证成功后会向下执行,认证失败会抛出异常
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			try {
				user.login(token);
			} catch (LockedAccountException lae) {
				token.clear();
				request.setAttribute("error", "用户已经被锁定不能登录，请与管理员联系！");
				return "/login";
			} catch (ExcessiveAttemptsException e) {
				token.clear();
				request.setAttribute("error", "账号：" + username + " 登录失败次数过多,锁定10分钟!");
				return "/login";
			} catch (AuthenticationException e) {
				token.clear();
				request.setAttribute("error", "用户或密码不正确！");
				return "/login";
			}
			UserLoginFormMap userLogin = new UserLoginFormMap();
			Session session = SecurityUtils.getSubject().getSession();
			userLogin.put("userId", session.getAttribute("userSessionId"));
			userLogin.put("accountName", username);
			userLogin.put("loginIP", session.getHost());
			userLoginMapper.addEntity(userLogin);
			request.removeAttribute("error");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "登录异常，请联系管理员！");
			return "/login";
		}
		return "redirect:index.shtml";
//		return Common.BACKGROUND_PATH + "/system/user/userAuthorizationStore"; //加选择门店页面
	}

	/**
	 * @mod Ekko 2015-09-07
	 * @throws Exception
	 */
	@RequestMapping("index")
	public String index(Model model) throws Exception {
		// 获取登录的bean
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		UserFormMap userFormMap = (UserFormMap)Common.findUserSession(request);
		ResFormMap resFormMap = new ResFormMap();
		resFormMap.put("userId", userFormMap.get("id"));
		List<ResFormMap> mps = resourcesMapper.findRes(resFormMap);
		//List<ResFormMap> mps = resourcesMapper.findByWhere(new ResFormMap());
		List<TreeObject> list = new ArrayList<TreeObject>();
		for (ResFormMap map : mps) {
			TreeObject ts = new TreeObject();
			Common.flushObject(ts, map);
			list.add(ts);
		}
		TreeUtil treeUtil = new TreeUtil();
		List<TreeObject> ns = treeUtil.getChildTreeObjects(list, 0);
		model.addAttribute("list", ns);
		// 登陆的信息回传页面

		//====start=========
//		登陆检查是否选择门店，选择过直接取，没选择过默认第一条
		String userId=getCurUserInfo().getUserId();
		List<UserAuthorizationStore> authorizationStorelst=getAuthorizationStore(userId);
					if(StringUtils.isBlank(getCurUserInfo().getStoreCode())){
				if(authorizationStorelst!=null && authorizationStorelst.size()>0){
					testlogin(authorizationStorelst.get(0).getStoreCode(),userId);
					model.addAttribute("storeName",authorizationStorelst.get(0).getBusinessName());
				}else{
					request.setAttribute("error", "该账号没有分配门店，请联系管理员！");
					logger.error("该账号没有分配门店，请联系管理员！");
				}
			}else{
					model.addAttribute("storeName",getstoreNamebyNo(authorizationStorelst,getCurUserInfo().getStoreCode()));
			}
		model.addAttribute("userAuthorizatioStoreList",authorizationStorelst );
		model.addAttribute("userFormMap", userFormMap);
		return "/index";
	}

	@RequestMapping("menu")
	public String menu(Model model) {
		return "/framework/menu";
	}

	/**
	 * 获取某个用户的权限资源
	 *
	 * @author lanyuan Email：mmm333zzz520@163.com date：2014-3-4
	 * @param request
	 * @return
	 */
	@RequestMapping("findAuthority")
	@ResponseBody
	public List<String> findAuthority(HttpServletRequest request) {
		return null;
	}

	@RequestMapping("download")
	public void download(String fileName, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		String ctxPath = request.getSession().getServletContext().getRealPath("/") + "\\"
				+ "filezip\\";
		String downLoadPath = ctxPath + fileName;
		System.out.println(downLoadPath);
		try {
			long fileLength = new File(downLoadPath).length();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition",
					"attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout() {
		// 使用权限管理工具进行用户的退出，注销登录
		SecurityUtils.getSubject().logout(); // session
		// 会销毁，在SessionListener监听session销毁，清理权限缓存
		return "redirect:login.shtml";
	}

	/*@RequestMapping("install")
	public String install() throws Exception {

		try {
			Properties props = Resources.getResourceAsProperties("jdbc.properties");
			String url = props.getProperty("jdbc.url");
			String driver = props.getProperty("jdbc.driverClass");
			String username = props.getProperty("jdbc.username");
			String password = props.getProperty("jdbc.password");
			Class.forName(driver).newInstance();
			Connection conn = (Connection) DriverManager.getConnection(url, username, password);
			ScriptRunner runner = new ScriptRunner(conn);
			runner.setErrorLogWriter(null);
			runner.setLogWriter(null);
			runner.runScript((new InputStreamReader(getClass().getResourceAsStream("/intall.sql"),"UTF-8")));

		} catch (Exception e) {
			e.printStackTrace();
			return "初始化失败！请联系管理员" + e;
		}

		return "/install";
	}*/








	private List<UserAuthorizationStore> getAuthorizationStore(String userName){
		Map<String,Object> paramMaps = new HashMap<String, Object>();
		paramMaps.put("userNumber",userName);
		List<UserAuthorizationStore> userAuthorizationStoreDtoList = null;
		try{
			userAuthorizationStoreDtoList =	userAuthorizationStoreService.getselectListByUserId(paramMaps);
		}catch (Exception e){

		}
		return userAuthorizationStoreDtoList;
	}
	private String getstoreNamebyNo(List<UserAuthorizationStore> lst,String storeCode){
		if (lst!=null&&lst.size()>0) {
			for (UserAuthorizationStore item :lst) {
                if(item.getStoreCode().equals(storeCode)){
                    return item.getBusinessName();
                }
            }
		}
		return "";
	}



	@RequestMapping("testlogin")//, method = RequestMethod.GET, produces = "text/html; charset=utf-8"
	@ResponseBody
	public String testlogin(String storeNo,String userId) throws Exception {
		boolean b= redisUtil.set(Common.USER_STORE_K+userId,storeNo);
		logger.debug("存入 redis key:"+Common.USER_STORE_K+userId+" 门店号："+storeNo +"存入是否成功："+b);
//		测试用
//		String resutl=redisUtil.get(Common.USER_STORE_K+userId,"");
//		logger.debug("key:"+Common.USER_S	TORE_K+userId+" 门店号："+storeNo);
		return "/index.shtml";
	}





}
