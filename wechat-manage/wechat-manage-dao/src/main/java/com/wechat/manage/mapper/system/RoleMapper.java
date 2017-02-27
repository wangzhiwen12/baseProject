package com.wechat.manage.mapper.system;

import com.wechat.manage.pojo.system.entity.RoleFormMap;
import com.wechat.manage.mapper.base.BaseMapper;

import java.util.List;

public interface RoleMapper extends BaseMapper{
	public List<RoleFormMap> seletUserRole(RoleFormMap map);
	
	public void deleteById(RoleFormMap map);
}
