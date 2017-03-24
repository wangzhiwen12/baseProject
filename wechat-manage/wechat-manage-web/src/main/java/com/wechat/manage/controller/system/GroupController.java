package com.wechat.manage.controller.system;

import com.wechat.manage.vo.DataTableResult;
import com.wechat.manage.vo.DataTableParams;
import com.wechat.manage.controller.index.BaseController;
import com.wechat.manage.pojo.system.entity.GroupFormMap;
import com.wechat.manage.utils.Common;
import com.wechat.manage.utils.StringUtils;
import com.wechat.manage.mapper.system.OrganizationInfoMapper;

import com.wechat.manage.pojo.system.entity.OrganizationInfo;
import com.wechat.manage.pojo.system.vo.ReturnDto;
import com.wechat.manage.service.system.intf.GroupInfoService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


/**
 * Created by XS on 2016/12/28.
 */

@Controller
@RequestMapping("/group/")
public class GroupController extends BaseController {
    private static Logger logger = Logger.getLogger(GroupController.class);

    @Autowired
    private GroupInfoService roupInfoService;
    @Autowired
    private OrganizationInfoMapper organizationInfoMapper;

//    @Autowired
//    private WechatUtil wechatUtil;


    /**
     * 跳转列表页
     * @param model
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("list")
    public String list(Model model, Integer id) throws Exception {
        model.addAttribute("res", findByRes());
        model.addAttribute("menuId", id);
        return Common.BACKGROUND_PATH + "/system/group/list";
    }


    /**
     * 跳转添加页
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("addUI")
    public String addUI(Model model) throws Exception {
        return Common.BACKGROUND_PATH + "/system/group/add";
    }

    /**
     * 跳转添加页
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("editUI")
    public String editUI(Model model) throws Exception {
        String id = getPara("id");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sid", id);
        List<OrganizationInfo> lst = roupInfoService.getOrganizationInfos(paramMap);
        if (lst.size() > 0) {
            model.addAttribute("list", lst.get(0));
        }
        return Common.BACKGROUND_PATH + "/system/group/edit";
    }


    /**
     * 修改方法
     * @param organizationName
     * @param sid
     * @param chkObjs
     * @return
     * @throws Exception
     */
    @RequestMapping("edit")
    @ResponseBody
    public ReturnDto edit(String organizationName, String sid, String chkObjs) throws Exception {
        ReturnDto rd = new ReturnDto();
        OrganizationInfo organizationInfo = new OrganizationInfo();
        organizationInfo.setOrganizationName(organizationName);
        organizationInfo.setOrganizationStatus(Integer.valueOf(chkObjs));
        organizationInfo.setSid(Long.valueOf(sid));
        organizationInfo.setUpdateName(getCurUserInfo().getUserName());
        organizationInfo.setUpdateTime(new Date());
        boolean bo = roupInfoService.modify(organizationInfo);
        if (bo) {
            rd.setCode("0");
        } else {
            rd.setCode("1");
            rd.setDesc("修改失败！");
        }
        return rd;
    }

    @RequestMapping("isExist")
    @ResponseBody
    public boolean isExist(String organizationName, String organizationCode) {
        return roupInfoService.isExist(organizationName, organizationCode);
    }


    /**
     * 保存方法
     * @param organizationName
     * @param organizationCode
     * @param chkObjs
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public ReturnDto save(String organizationName, String organizationCode, String chkObjs) {
        return roupInfoService.save(organizationName, organizationCode, chkObjs, getCurUserInfo().getUserName());
    }


    /**
     * 列表查询
     * @param dataTableParams
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("findByPage2")
    public DataTableResult findByPage(DataTableParams dataTableParams) throws Exception {
        GroupFormMap groupFormMap = getFormMap(GroupFormMap.class);
        String pageNow = ((dataTableParams.getiDisplayStart() + 1) % dataTableParams.getiDisplayLength() > 0 ? (dataTableParams.getiDisplayStart() + 1) / dataTableParams.getiDisplayLength() + 1 : (dataTableParams.getiDisplayStart() + 1) / dataTableParams.getiDisplayLength()) + "";
        groupFormMap = toFormMap(groupFormMap, pageNow, dataTableParams.getiDisplayLength() + "", groupFormMap.getStr("orderby"));
        pageView.setRecords(organizationInfoMapper.findUserPage(groupFormMap));//不调用默认分页,调用自已的mapper中findUserPage
        DataTableResult<GroupFormMap> dataTableResult = new DataTableResult<GroupFormMap>();
        dataTableResult.setsEcho(dataTableParams.getsEcho());
        dataTableResult.setAaData(organizationInfoMapper.findUserPage(groupFormMap));
        dataTableResult.setiTotalDisplayRecords(pageView.getRowCount());
        dataTableResult.setiTotalRecords(pageView.getRowCount());
        return dataTableResult;
    }


    /**
     * 从查询列表或根据编码查询列表
     *
     * @param paramMaps
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"getGroupList"}, method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ReturnDto getGroupList(Map<String, Object> paramMaps) throws Exception {
        ReturnDto rd = new ReturnDto();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<OrganizationInfo> lst = new ArrayList<OrganizationInfo>();
        paramMap.put("organizationStatus", 0);
        if (StringUtils.isNotEmpty(String.valueOf(paramMaps.get("organizationCode")))) {
            paramMap.put("organizationCode", String.valueOf(paramMaps.get("organizationCode")));
        }
        if (StringUtils.isNotEmpty(String.valueOf(paramMaps.get("organizationType")))) {
            paramMap.put("organizationType", Integer.valueOf(String.valueOf(paramMaps.get("organizationType"))));
        }
        try {
            lst = roupInfoService.getOrganizationInfos(paramMap);
            if (lst != null && lst.size() > 0) {
                rd.setCode("0");
                rd.setDesc("查询成功！");
                rd.setObj(lst);
            } else {
                rd.setCode("0");
                rd.setDesc("没有查到数据！");
                rd.setObj(lst);
            }
        } catch (Exception e) {
        	System.out.println(e.getMessage());
            e.printStackTrace();
            rd.setCode("1");
            rd.setDesc(e.getMessage());
        }
        return rd;
    }


    @RequestMapping(value = {"getTo"}, method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String getTo(){
        String appid="wx871d0104ae72e615";
        String secret="00e66c2772af76181745b6f5d92b5801";
//         wechatUtil.getAccessToken(appid,secret);
        return "";
    }

}
