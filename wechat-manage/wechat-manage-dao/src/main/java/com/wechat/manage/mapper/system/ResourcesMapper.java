package com.wechat.manage.mapper.system;

import com.wechat.manage.mapper.base.BaseMapper;
import com.wechat.manage.pojo.system.entity.ResFormMap;

import java.util.List;

public interface ResourcesMapper extends BaseMapper {
	public List<ResFormMap> findChildlists(ResFormMap map);

	public List<ResFormMap> findRes(ResFormMap map);

	public void updateSortOrder(List<ResFormMap> map);
	
	public List<ResFormMap> findUserResourcess(String userId);


	
}
