package com.wechat.manage.mapper.system;

import com.wechat.manage.mapper.base.BaseMapper;
import com.wechat.manage.pojo.system.UserFormMap;

import java.util.List;


public interface UserMapper extends BaseMapper {

	public List<UserFormMap> findUserPage(UserFormMap userFormMap);
	
}
