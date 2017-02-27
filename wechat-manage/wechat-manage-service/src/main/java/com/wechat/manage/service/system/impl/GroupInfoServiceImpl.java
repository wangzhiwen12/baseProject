package com.wechat.manage.service.system.impl;

import com.wechat.manage.pojo.system.vo.ReturnDto;
import com.wechat.manage.pojo.system.entity.OrganizationInfo;
import com.wechat.manage.pojo.system.entity.GroupFormMap;
import com.wechat.manage.pojo.system.entity.CouponTemplate;
import com.wechat.manage.mapper.system.OrganizationInfoMapper;
import com.wechat.manage.utils.StringUtils;
import com.wechat.manage.vo.DataTableResult;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wechat.manage.service.system.intf.GroupInfoService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XS on 2016/12/28.
 */
@Service
public class GroupInfoServiceImpl implements GroupInfoService {
    private static Logger logger = Logger.getLogger(GroupInfoServiceImpl.class);


    @Autowired
     private OrganizationInfoMapper organizationInfoMapper;

    public boolean modify(OrganizationInfo organizationInfo) {

       int i= organizationInfoMapper.updateByPrimaryKeySelective(organizationInfo);
        if(i>0){
            return  true;
        }else {
            return  false;
        }
    }

    public List<OrganizationInfo> getOrganizationInfos(Map<String, Object> paramMap) {
        return organizationInfoMapper.selectListByParam(paramMap);
    }

    public DataTableResult findByPage(Map<String, Object> paramMap) throws Exception {
        DataTableResult<CouponTemplate> page = new DataTableResult<CouponTemplate>();
        DataTableResult<GroupFormMap> dataTableResult = new DataTableResult<GroupFormMap>();


        return dataTableResult;
    }

    public boolean isExist(String organizationName, String organizationCode) {
        Map<String, Object> paramMap=new HashMap<String, Object>();
        if (StringUtils.isNotEmpty(organizationName)){
            paramMap.put("organizationName",organizationName);
        } if (StringUtils.isNotEmpty(organizationCode)){
            paramMap.put("organizationCode",organizationCode);
        }
        List<OrganizationInfo> lst=organizationInfoMapper.selectListByParam(paramMap);
        if (lst!=null || lst.size()>0){
            return false;
        }else {
            return  true;
        }
    }

    public ReturnDto save(String organizationName, String organizationCode, String chkObjs, String userName) {
        ReturnDto rd=new ReturnDto();
        boolean boname= isExist(organizationName,null);
        if(boname){
            rd.setCode("1");
            rd.setDesc("集团名称已存在");
            return rd;
        }
        boolean bocode= isExist(organizationCode,null);
        if(bocode){
            rd.setCode("1");
            rd.setDesc("集团编码已存在");
            return rd;
        }
        OrganizationInfo organizationInfo=new OrganizationInfo();
        organizationInfo.setAreaCode("0");
        organizationInfo.setCreateName(userName);
        organizationInfo.setCreateTime(new Date());
        organizationInfo.setOrganizationName(organizationName);
        organizationInfo.setOrganizationCode(organizationCode);
        organizationInfo.setOrganizationStatus(Integer.valueOf(chkObjs));
        organizationInfo.setOrganizationType(0);
        organizationInfo.setGroupSid(Long.valueOf(0));
        organizationInfo.setParentSid("0");
        organizationInfo.setShippingPoint("0");
        organizationInfo.setStoreType(0);
        organizationInfo.setUpdateName(userName);
        organizationInfo.setUpdateTime(new Date());
        Integer i= organizationInfoMapper.insert(organizationInfo);
        if (i>0){
            rd.setCode("0");
            rd.setDesc("添加成功");
        }
        return rd;
    }
}

