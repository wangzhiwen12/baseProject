package com.wechat.manage.mapper.wechat;

import com.wechat.manage.pojo.wechat.entity.WxpageBound;

import java.util.Map;

public interface WxpageBoundMapper {
	int deleteByPrimaryKey(Long sid);

	int insertSelective(WxpageBound record);

	WxpageBound selectByPrimaryKey(Long sid);

	WxpageBound getInfoByStroeCodeAndType(Map<String, Object> paramMap);

	int updateByPrimaryKeySelective(WxpageBound record);
}