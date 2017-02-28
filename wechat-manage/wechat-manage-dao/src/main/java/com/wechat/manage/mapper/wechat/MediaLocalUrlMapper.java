package com.wechat.manage.mapper.wechat;


import com.wechat.manage.pojo.wechat.entity.MediaLocalUrl;

import java.util.List;
import java.util.Map;

public interface MediaLocalUrlMapper {
	int deleteByPrimaryKey(Long sid);

	int insert(MediaLocalUrl record);

	int insertSelective(MediaLocalUrl record);

	MediaLocalUrl selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(MediaLocalUrl record);

	int updateByPrimaryKey(MediaLocalUrl record);

	List<MediaLocalUrl> selectListByParam(MediaLocalUrl record);

	List<MediaLocalUrl> selectListByParam(Map<String, Object> paramMap);
}