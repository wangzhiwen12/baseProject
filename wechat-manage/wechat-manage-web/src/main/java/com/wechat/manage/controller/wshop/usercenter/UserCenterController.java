package com.wechat.manage.controller.wshop.usercenter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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

import com.alibaba.fastjson.JSONObject;
import com.wechat.manage.controller.index.BaseController;
import com.wechat.manage.pojo.system.vo.UserBaseInfoDto;
import com.wechat.manage.pojo.usercenter.entity.TPage;
import com.wechat.manage.service.usercenter.intf.IUserCenterService;
import com.wechat.manage.service.usercenter.intf.TWPageService;
import com.wechat.manage.utils.ComErrorCodeConstants;
import com.wechat.manage.utils.Common;
import com.wechat.manage.utils.HttpUtil;
import com.wechat.manage.utils.ResultUtil;

/**
 * 跳转到会员主页
 * @author Admin
 *
 */
@Controller
@RequestMapping("/wShop")
public class UserCenterController extends BaseController{
	Logger logger = Logger.getLogger(UserCenterController.class);
	@Autowired
	private IUserCenterService userCenterService;
	@Autowired
	private TWPageService pageService;
	@RequestMapping("/userCenter")
	public String toUserCenter(Model model) {
		//model.addAttribute("res", findByRes());
		UserBaseInfoDto curUser = getCurUserInfo();
		//读取已配置的页面html返回到前台
		TPage page = new TPage();
		page.setShopId("1");
		page.setType("3");
		List<TPage> list = pageService.selectTPage(page);
		String html="";
		if(!list.isEmpty()){
			String link = list.get(0).getPageLink();
			try {
				html = HttpUtil.sendGet(link, null);
			} catch (Exception e) {
				logger.error(e.toString(),e);
			}
		}
		
		String title = userCenterService.getTitleData(curUser.getStoreCode());
		model.addAttribute("html", html);
		model.addAttribute("title", title);
        return Common.BACKGROUND_PATH + "/wshop/usercenter/index";
	}
	
	
	@RequestMapping(value = {"/saveUserCenterPage"}, method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> saveUserCenterPage(HttpServletRequest request, HttpServletResponse response) {
		String html = "";
		String shopId = "";
		String pageName = "";
		String data = "";
        try {
            JSONObject jsonData = HttpUtil.HttpGetRequest(request);
            html =jsonData.getString("html"); 
            shopId = jsonData.getString("shopId");
            pageName = jsonData.getString("pageName");
            data = jsonData.getString("title");
        } catch (IOException ex) {
        	logger.error(ex.toString(),ex);
        }
        try{
        	UserBaseInfoDto curUser = getCurUserInfo();
            boolean flag = false;
            if (curUser != null && StringUtils.isNotEmpty(html)) {
            	TPage page = new TPage();
            	page.setShopId(shopId);
            	page.setPageName(pageName);
            	page.setCreateUser(curUser.getUserId());
            	flag = userCenterService.saveUserCenterPage(page, html, data, curUser.getStoreCode());
            }
            if (!flag) {
                return ResultUtil.creComErrorResult(ComErrorCodeConstants.ErrorCode.PAGE_ADD_FAILED_ERROR.getErrorCode(),
                        ComErrorCodeConstants.ErrorCode.PAGE_ADD_FAILED_ERROR.getMemo());
            }
        }catch(Exception e){
        	logger.error(e.toString(),e);
        	return ResultUtil.creComErrorResult(ComErrorCodeConstants.ErrorCode.PAGE_ADD_FAILED_ERROR.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.PAGE_ADD_FAILED_ERROR.getMemo());
        }
        

        return ResultUtil.creComSucResult("");
    }
	
	@RequestMapping("/preview")
	public String toPreview(Model model) {
		TPage page = new TPage();
		page.setShopId("1");
		page.setType("3");
		List<TPage> list = pageService.selectTPage(page);
		String html="";
		String link = list.get(0).getPageLink();
		try {
			html = HttpUtil.sendGet(link, null);
		} catch (Exception e) {
			logger.error(e.toString(),e);
		}
		model.addAttribute("html", html);
		return Common.BACKGROUND_PATH + "/wshop/usercenter/preview";
	}
}
