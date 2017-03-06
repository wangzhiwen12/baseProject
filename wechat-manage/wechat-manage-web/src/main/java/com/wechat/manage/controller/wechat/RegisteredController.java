package com.wechat.manage.controller.wechat;

import com.wechat.manage.controller.index.BaseController;
import com.wechat.manage.pojo.system.vo.UserBaseInfoDto;
import com.wechat.manage.pojo.wechat.entity.PageRegistered;
import com.wechat.manage.service.wechat.intf.RegisteredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XS on 2017/1/18.
 */
@Controller
@RequestMapping("registered")
public class RegisteredController extends BaseController
 {
	@Autowired
	private RegisteredService registeredService;
	/**
	 * 添加
	 * @param ncity
	 * @param nbirthday
	 * @param nname
	 * @param ngender
	 * @param dphoneno
	 * @param dtitle
	 * @param dimg
	 * @param ddescription
	 * @param dagreement
	 * @return
	 */
    @ResponseBody
    @RequestMapping(value = "/phoneRegistered", method = RequestMethod.POST)
    public Map<String, Object> phoneRegistered(String ncity, String nbirthday, String nname, String ngender, String dphoneno, String dtitle, String dimg, String ddescription , String dagreement) {
        Map<String, Object> map = new HashMap<String, Object>();
        PageRegistered page =null;
        Integer result=0;
         UserBaseInfoDto curUserInfo = getCurUserInfo();
         String shopNo = curUserInfo.getStoreCode();
         PageRegistered register = new PageRegistered();
         register.setShopNo(shopNo);
		List<PageRegistered> selectPageRegisteredList = registeredService.selectPageRegisteredList(register);
		if(selectPageRegisteredList!= null && selectPageRegisteredList.size()>0){
			 page = new PageRegistered();
	         page.setShopNo(shopNo);
	         page.setCity(ncity);
	         page.setBirthday(nbirthday);
	         page.setTitle(dtitle);
	         page.setBackImage(dimg);
	         page.setGender(Integer.valueOf(ngender));
	         page.setPageDescription(ddescription);
	         page.setPhoneNo(dphoneno);
             page.setName(nname);
	         page.setUserAgreement(dagreement);
	         result = registeredService.updatePageRegistered(page);
		}else{
			 page = new PageRegistered();
	         page.setShopNo(curUserInfo.getStoreCode());
	         page.setCity(ncity);
	         page.setBirthday(nbirthday);
	         page.setTitle(dtitle);
	         page.setBackImage(dimg);
	         page.setGender(Integer.valueOf(ngender));
	         page.setPageDescription(ddescription);
	         page.setPhoneNo(dphoneno);
	         page.setUserAgreement(dagreement);
	         result = registeredService.savePageRegister(page);
		}
         
         System.out.println(result);
         if(result == 1){
        	 map.put("success", true);
         }else{
        	 map.put("success", false);
         }
        return map;

    }
    /**
     * 查询byshopno
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectPhoneRegistered", method = RequestMethod.POST)
    public Map<String, Object> selectPhoneRegistered(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
         UserBaseInfoDto curUserInfo = getCurUserInfo();
         PageRegistered page = new PageRegistered();
         page.setShopNo(curUserInfo.getStoreCode());
         List<PageRegistered> selectPageRegisteredList = registeredService.selectPageRegisteredList(page);
         //System.out.println(result);
         map.put("list", selectPageRegisteredList);
        return map;
    }
    /**
     * 修改byshopno
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updatePhoneRegistered", method = RequestMethod.POST)
    public Map<String, Object> updatePhoneRegistered(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
         UserBaseInfoDto curUserInfo = getCurUserInfo();
         PageRegistered page = new PageRegistered();
         page.setShopNo(curUserInfo.getStoreCode());
        Integer result = registeredService.updatePageRegistered(page);
        System.out.println(result);
        if(result == 1){
       	 map.put("success", true);
        }else{
       	 map.put("success", false);
        }
        return map;
    }
    /**
     * 删除byshopno
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deletePhoneRegistered", method = RequestMethod.POST)
    public Map<String, Object> deletePhoneRegistered(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
         UserBaseInfoDto curUserInfo = getCurUserInfo();
         PageRegistered page = new PageRegistered();
         page.setShopNo(curUserInfo.getStoreCode());
        Integer result = registeredService.deletePageRegistered(page);
        System.out.println(result);
        if(result == 1){
       	 map.put("success", true);
        }else{
       	 map.put("success", false);
        }
        return map;
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/getCity", method = RequestMethod.POST)
    public Map<String, Object> getCity(HttpServletRequest request) {
    	Map<String, Object> map=new HashMap<String, Object>();
    	map.put("1", "北京市");
    	map.put("2", "上海市");
    	map.put("3", "天津市");
    	map.put("1", "重庆市");
    	map.put("1", "洛阳市");
    	return map;
    }
}
