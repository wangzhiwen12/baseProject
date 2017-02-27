package com.wechat.manage.mapper.system;

import com.wechat.manage.pojo.system.entity.GroupFormMap;
import com.wechat.manage.pojo.system.entity.OrganizationInfo;
import com.wechat.manage.mapper.base.BaseMapper;

import java.util.List;
import java.util.Map;

public interface OrganizationInfoMapper extends BaseMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(OrganizationInfo record);

    int insertSelective(OrganizationInfo record);

    OrganizationInfo selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(OrganizationInfo record);

    int updateByPrimaryKey(OrganizationInfo record);

    List<OrganizationInfo> selectListByParam(Map<String, Object> paramMap);


    public List<GroupFormMap> findUserPage(GroupFormMap groupFormMap);
}