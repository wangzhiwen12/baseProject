package com.wechat.manage.mapper.system;

import com.wechat.manage.pojo.system.AppAccountInfo;
import com.wechat.manage.pojo.system.WechatAppDto;

import java.util.List;
import java.util.Map;

public interface AppAccountInfoMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(AppAccountInfo record);

    int insertSelective(AppAccountInfo record);

    AppAccountInfo selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(AppAccountInfo record);

    int updateByStoreCode(AppAccountInfo record);

    int updateByStorecodeSelective(AppAccountInfo record);

    int updateByPrimaryKey(AppAccountInfo record);

    List<AppAccountInfo> selectListByParam(Map<String, Object> paramMap);

    List<WechatAppDto> selectAppInfoListByParam(WechatAppDto dto);
}