package com.wechat.manage.controller.wshop.wpage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.wechat.manage.controller.index.BaseController;
import com.wechat.manage.pojo.system.entity.UserFormMap;
import com.wechat.manage.pojo.usercenter.entity.TPage;
import com.wechat.manage.pojo.wshopnav.entity.TPageEdit;
import com.wechat.manage.pojo.wshopnav.entity.TPageExtendsForMap;
import com.wechat.manage.pojo.wshopnav.vo.TPageDto;
import com.wechat.manage.service.usercenter.intf.TPageService;
//import com.wfj.dto.TPageDto;
//import com.wfj.entity.DataTableParams;
//import com.wfj.entity.DataTableResult;
//import com.wfj.entity.TPage;
//import com.wfj.entity.TPageEdit;
//import com.wfj.entity.TPageExtendsForMap;
//import com.wfj.entity.UserFormMap;
//import com.wfj.entity.fans.Result;
//import com.wfj.service.intf.TPageService;
//import com.wfj.service.intf.TWPageService;
//import com.wfj.util.Common;
//import com.wfj.util.FTPUtils;
//import com.wfj.util.HttpUtil;
//import com.wfj.util.PropertiesUtils;
//import com.wfj.util.ResultUtil;
//import com.wfj.util.StringUtils;
//import com.wfj.util.UUIDUtils;
import com.wechat.manage.service.usercenter.intf.TWPageService;
import com.wechat.manage.service.util.FTPUtils;
import com.wechat.manage.service.util.PropertiesUtils;
import com.wechat.manage.service.util.UUIDUtils;
import com.wechat.manage.utils.Common;
import com.wechat.manage.utils.HttpUtil;
import com.wechat.manage.utils.ResultUtil;
import com.wechat.manage.vo.DataTableParams;
import com.wechat.manage.vo.DataTableResult;

/**
 * 
 * @author Administrator
 * @date 2017年1月17日 上午11:46:28
 */
@Controller
@RequestMapping("/wechatShopPage")
public class WPageController extends BaseController {

	private Logger logger = Logger.getLogger(WPageController.class);
	@Autowired
	private TWPageService tWPageService;

	@Autowired
	private TPageService tpPageService;

	@RequestMapping("/list")
	public String list(Model model, Integer id) throws Exception {
		logger.info("访问list.jsp" + id);
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/wshop/wpage/list";
	}

	@ResponseBody
	@RequestMapping("selectTPage")
	public List<TPage> selectTPage(TPage tPage) {
		return tWPageService.selectTPage(tPage);
	}

	/**
	 * 分页查询
	 * 
	 * @param dataTableParams
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("findByPage")
	public DataTableResult findByPage(DataTableParams dataTableParams) throws Exception {
		// 获取当前登录对象
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		UserFormMap userFormMap = (UserFormMap) Common.findUserSession(request);
		String userId = String.valueOf(userFormMap.get("id"));
		Long longs = Long.valueOf(dataTableParams.getsEcho());
		String pageSize = String.valueOf(dataTableParams.getiDisplayLength());
		List<TPage> list = tWPageService.selectWPageInfo(userId, String.valueOf(dataTableParams.getiDisplayStart()),
				pageSize);
		Long count = tWPageService.selectWPageInfoCount(userId);

		List<TPageExtendsForMap> listMap = new ArrayList<TPageExtendsForMap>();

		if (list.size() > 0) {
			for (TPage tPage : list) {
				listMap.add(com.alibaba.fastjson.JSON.parseObject(net.sf.json.JSONObject.fromObject(tPage).toString(),
						TPageExtendsForMap.class));
			}
		}
		DataTableResult<TPageExtendsForMap> dataTableResult = new DataTableResult<TPageExtendsForMap>();
		dataTableResult.setAaData(listMap);
		dataTableResult.setiTotalDisplayRecords(count);
		dataTableResult.setiTotalRecords(count);
		dataTableResult.setsEcho(longs.toString());
		return dataTableResult;
	}

	/**
	 * 分页查询(草稿)
	 * 
	 * @param dataTableParams
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("findByPage2")
	public DataTableResult findByPage2(DataTableParams dataTableParams) throws Exception {
		// 获取当前登录对象
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		UserFormMap userFormMap = (UserFormMap) Common.findUserSession(request);
		String userId = String.valueOf(userFormMap.get("id"));
		Long longs = Long.valueOf(dataTableParams.getsEcho());
		String pageSize = String.valueOf(dataTableParams.getiDisplayLength());
		List<TPage> list = tWPageService.selectWPageInfo2(userId, String.valueOf(dataTableParams.getiDisplayStart()),
				pageSize);
		Long count = tWPageService.selectWPageInfoCount2(userId);

		List<TPageExtendsForMap> listMap = new ArrayList<TPageExtendsForMap>();

		if (list.size() > 0) {
			for (TPage tPage : list) {
				listMap.add(com.alibaba.fastjson.JSON.parseObject(net.sf.json.JSONObject.fromObject(tPage).toString(),
						TPageExtendsForMap.class));
			}
		}
		DataTableResult<TPageExtendsForMap> dataTableResult = new DataTableResult<TPageExtendsForMap>();
		dataTableResult.setAaData(listMap);
		dataTableResult.setiTotalDisplayRecords(count);
		dataTableResult.setiTotalRecords(count);
		dataTableResult.setsEcho(longs.toString());
		return dataTableResult;
	}
	/**
	 * 删除微页面
	 * 
	 * @param request
	 */
	@ResponseBody
	@RequestMapping("delPage")
	public void delWPage(HttpServletRequest request) {
		try {
			String sid = request.getParameter("sid");
			String createUser = request.getParameter("createUser");
			String pageid=request.getParameter("pageLink");
			if(StringUtils.isNotEmpty(pageid)){
				tpPageService.deleteWPage(pageid);
			}
			String pageLink = pageid + ".html";
			logger.info("请求参数:sid:" + sid + ",用户id:" + createUser);
			tWPageService.deleteWPage(sid, createUser);// 删除数据库
			// String[] split = pageLink.split("/");
			// String fileName = split[split.length-1];
			
			boolean deleteFile = deleteFile(pageLink);
			logger.info("删除成功" + deleteFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改序号
	 * 
	 * @param request
	 */
	@ResponseBody
	@RequestMapping("updatePageSeqNo")
	public void updatePageSeqNo(HttpServletRequest request) {
		try {
			String sid = request.getParameter("sid");
			String createUser = request.getParameter("createUser");
			String seqNo = request.getParameter("seqNo");
			TPage tpage = new TPage();
			tpage.setSid(sid);
			tpage.setCreateUser(createUser);
			tpage.setSeqNo(Integer.valueOf(seqNo));
			tWPageService.updateWpage(tpage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 复制微页面
	 * 
	 * @param request
	 */
	@ResponseBody
	@RequestMapping("copyFile")
	public void copyFile(HttpServletRequest request) {
		String oldName = "";
		String newName = "";
		try {
			String sid = request.getParameter("sid");
			String createUser = request.getParameter("createUser");
			oldName = request.getParameter("pageLink") + ".html";
			newName = UUIDUtils.generateUUID() + ".html";
			logger.info("复制微页面:复制页面地址:" + oldName + ",新页面地址:" + newName);
			TPage tPage = new TPage();
			tPage.setSid(sid);
			tPage.setCreateUser(createUser);
			tWPageService.inestCopyFile(tPage, newName);// 数据库复制
			logger.info("数据库复制页面成功！");
			boolean file = copyFile(oldName, newName);// FDT服务器复制
			logger.info("FTP复制页面成功:" + file);
		} catch (Exception e) {
			logger.error("复制页面失败:复制页面地址:" + oldName + ",新页面地址:" + newName);
			e.printStackTrace();
		}
	}

	@RequestMapping("/add_wpageUI")
	public String addWpage(Model model, Integer id) throws Exception {
		return Common.BACKGROUND_PATH + "/wshop/wpage/wpageAdd";
	}
	@RequestMapping("/edit_wpageUI")
	public String editWpage(Model model, String id) throws Exception {
		TPage tPage =new TPage();
		tPage.setSid(id);
		List<TPage> tps =tWPageService.selectTPage(tPage);
		String link=tps.get(0).getPageLink();
		String tpeditId=link.substring(link.indexOf("e/")+2,link.length()-5);
		List<TPageEdit> tpedits =tpPageService.selectById(tpeditId);
		TPageDto tPageEdit=new TPageDto();
		if(tpedits.size()>0){
		 tPageEdit = com.alibaba.fastjson.JSON.parseObject(tpedits.get(0).getParam(),TPageDto.class);
		}
		
		model.addAttribute("notice", tPageEdit.getNotice());
		model.addAttribute("pageId",tpeditId);
		model.addAttribute("wpageTitle",tPageEdit.getWpageTitle());
		return Common.BACKGROUND_PATH + "/wshop/wpage/wpageEdit";
	}
	@RequestMapping(value = {"/previewTPage"}, method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> saveUserCenterPage(HttpServletRequest request, HttpServletResponse response) {
		String html = "";
		String id=null;
		boolean flag=false;
		List<String> propertiesKeys = new ArrayList<String>();
		propertiesKeys.add("ftp.addr");
		propertiesKeys.add("ftp.port");
		propertiesKeys.add("ftp.username");
		propertiesKeys.add("ftp.password");
		Map<String, String> valueMap = analyProperties(propertiesKeys);
        try {
            JSONObject jsonData = HttpUtil.HttpGetRequest(request);
            html =jsonData.getString("html"); 
            id=jsonData.getString("id");
        } catch (IOException ex) {
        	logger.error(ex.toString(),ex);
        }
		
		
		InputStream input = new ByteArrayInputStream(html.getBytes());
		FTPUtils util = FTPUtils.getInstance();
		flag = util.uploadFile(valueMap.get("ftp.addr"), Integer.valueOf(valueMap.get("ftp.port")), 
				valueMap.get("ftp.username"), valueMap.get("ftp.password"), "/wshop/page",
				id + ".html", input);
		return ResultUtil.creComSucResult("");
	}
	/**
	 * 获得properties里对应值
	 * @param list
	 * @return
	 */
	public Map<String, String> analyProperties(List<String> list) {
		Map<String, String> map = new HashMap<String, String>();
		for (String key : list) {
			String value = PropertiesUtils.findPropertiesKey(key);
			map.put(key, value);
		}
		return map;
	}
	@RequestMapping("/preview")
	public String toPreview(Model model,String id) {
		String html="";
		String link = null;
		if(id!=null&&!"".equals(id)){
			link="http://10.6.100.100/page/"+id+".html";
		}
		try {
			html = HttpUtil.sendGet(link, null);
		} catch (Exception e) {
			logger.error(e.toString(),e);
		}
		model.addAttribute("html", html);
		return Common.BACKGROUND_PATH + "/wshop/wpage/wpagePreview";
	}
	/**
	 * 上传文件
	 * 
	 * @param fileContent
	 * @param fileName
	 * @return
	 */
	public boolean upLoadFile(String fileContent, String fileName) {
		InputStream input = new ByteArrayInputStream(fileContent.getBytes());
		FTPUtils util = FTPUtils.getInstance();
		boolean result = util.uploadFile("10.6.100.100", 21, "images", "123456", "/wshop/page", fileName, input);
		logger.info("上传文件：" + fileName + "。。。结果：" + result);
		return result;
	}

	/**
	 * 删除文件
	 * 
	 * @param fileName
	 * @return
	 */
	public boolean deleteFile(String fileName) {
		FTPUtils util = FTPUtils.getInstance();
		boolean result = util.delete("/wshop/page/" + fileName, "10.6.100.100", 21, "images", "123456");
		logger.info("删除文件：" + fileName + "。。。结果：" + result);
		return result;
	}

	/**
	 * 查询文件
	 * 
	 * @param fileName
	 * @return
	 */
	public String findFile(String fileName) {
		FTPUtils util = FTPUtils.getInstance();
		String result = null;
		try {
			result = util.readFile(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 远程复制文件
	 * 
	 * @param oldName
	 * @param newName
	 * @return
	 */
	public boolean copyFile(String oldName, String newName) {
		FTPUtils util = FTPUtils.getInstance();
		boolean result = false;

		try {
			result = util.copyFile(oldName, newName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 设置主页
	 * 
	 * @param request
	 */
	@ResponseBody
	@RequestMapping("updateHomePage")
	public void updateHomePage(HttpServletRequest request) {
		try {
			String sid = request.getParameter("sid");
			String createUser = request.getParameter("createUser");
			TPage tpage = new TPage();
			tpage.setSid(sid);
			tpage.setCreateUser(createUser);
			tWPageService.updateHomePage(tpage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/saveDraft", method = RequestMethod.POST)
	public String saveDraft(Model model, HttpServletRequest request) throws Exception {
		String param = null;
		String uuid = UUIDUtils.generateUUID();
		boolean upresult=false;
		String userId=null;
		param = getData(request);
		String data = param.replace("\\", "");
		data = data.replace("\"{", "{");
		data = data.replace("}\"", "}");
		TPageEdit tPageEdit = com.alibaba.fastjson.JSON.parseObject(data, TPageEdit.class);
		TPage tPage = com.alibaba.fastjson.JSON.parseObject(tPageEdit.getParam(), TPage.class);
		
		UserFormMap userFormMap = (UserFormMap) Common.findUserSession(request);
		userId = String.valueOf(userFormMap.get("id"));
		tPage.setCreateUser(userId);
		
		if(tPageEdit.getId()==null){
			tPage.setPageLink("http://10.6.100.100/wshop/page/" + uuid + ".html");
			tPageEdit.setId(uuid);
			tpPageService.insertSelective(tPageEdit);
			upresult = upLoadFile(param, uuid + ".html");// 解析json的param
			if (upresult == true) {
				logger.info("保存文件：" + uuid + ".html  ===到远程服务器成功");
			}
			tPage.setPageCode("xx");
			tPage.setType("1");
			tPage.setIsHome("0");
			tPage.setStatus("1");// 解析传过来的json的status
			tPage.setShopId("1");// 根据用户id找到微店id
			tPage.setCreateTime(new Timestamp(System.currentTimeMillis()));
			tPage.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			tPage.setSeqNo(1);
			tWPageService.insertSelective(tPage);
		}else{
			tPage.setPageLink("http://10.6.100.100/wshop/page/" + tPageEdit.getId() + ".html");
			upresult = upLoadFile(param, tPageEdit.getId() + ".html");
			tPage.setStatus("1");
			tPage.setSid(tWPageService.selectTPage(tPage).get(0).getSid());
			tWPageService.updateWpage(tPage);
			tpPageService.update(tPageEdit);
		}
		if (upresult == true) {
			return "success";
		} else {
			return "false";
		}
	}
	@ResponseBody
	@RequestMapping(value = "/updateDraft", method = RequestMethod.POST)
	public String updateDraft(Model model, HttpServletRequest request) throws Exception {
		String param = null;
		String uuid=UUIDUtils.generateUUID();
		String userId =null;
		
		param = getData(request);
		String data = param.replace("\\", "");
		data = data.replace("\"{", "{");
		data = data.replace("}\"", "}");
		TPageEdit tPageEdit = com.alibaba.fastjson.JSON.parseObject(data, TPageEdit.class);
		
		if(tpPageService.selectById(tPageEdit.getId()).size()==0){
			tPageEdit.setId(uuid);
			tpPageService.insertSelective(tPageEdit);
		}else{
			tpPageService.update(tPageEdit);
		}
		
		TPage tp = com.alibaba.fastjson.JSON.parseObject(tPageEdit.getParam(), TPage.class);
		tp.setPageLink("http://10.6.100.100/wshop/page/"+tPageEdit.getId()+".html");
		List<TPage> tps=tWPageService.selectTPage(tp);
		UserFormMap userFormMap = (UserFormMap) Common.findUserSession(request);
		userId = String.valueOf(userFormMap.get("id"));
		if(tps.size()==0){
			tp.setCreateUser(userId);
			tp.setPageLink("http://10.6.100.100/wshop/page/" + uuid + ".html");
			tp.setPageCode("xx");
			tp.setType("1");
			tp.setIsHome("0");
			tp.setStatus("0");
			tp.setShopId("1");// 根据用户id找到微店id
			tp.setCreateTime(new Timestamp(System.currentTimeMillis()));
			tp.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			tp.setSeqNo(1);
			tWPageService.insertSelective(tp);
		}else{
			tp.setStatus("0");
			tp.setSid(tps.get(0).getSid());
			tp.setCreateUser(userId);
			tWPageService.updateWpage(tp);
		}
		//删除远程服务器静态页
		boolean deresult = deleteFile( tPageEdit.getId() + ".html"); 
		if (deresult == true) {
			logger.info("删除文件：" + tPageEdit.getId() + ".html");
			boolean upresult = upLoadFile(param, tPageEdit.getId() + ".html"); 
			if(upresult==true){
				logger.info("保存文件：" + tPageEdit.getId() + ".html  ===到远程服务器成功");
				return "success";
			}else{
				return "error";
			}
		}else{
			return "error";
		}
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