package com.wechat.manage.service.wechat.impl;

import com.wechat.manage.pojo.wechat.entity.PageRegistered;
import com.wechat.manage.mapper.wechat.PageRegisteredMapper;
import com.wechat.manage.service.wechat.intf.RegisteredService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by XS on 2017/1/18.
 */
@Service
public class RegisteredServiceImpl implements RegisteredService {
	private static final Logger logger = LoggerFactory
			.getLogger(RegisteredServiceImpl.class);
	@Autowired
	private PageRegisteredMapper pageRegisteredMapper;
	
	public Integer savePageRegister(PageRegistered register) {
		Integer result = pageRegisteredMapper.insert(register);
		if(result != 1){
			logger.info("保存失败!");
		}
		return result;
	}

	public Integer updatePageRegistered(PageRegistered register) {
		if(StringUtils.isEmpty(register.getShopNo()) && StringUtils.isEmpty(String.valueOf(register.getId()))){
			return null;
		}
		Integer result = pageRegisteredMapper.updateByShopNoOrId(register);
		return result;
	}

	public Integer deletePageRegistered(PageRegistered register) {
		if(StringUtils.isEmpty(register.getShopNo()) && StringUtils.isEmpty(String.valueOf(register.getId()))){
			return null;
		}
		Integer result = pageRegisteredMapper.deleteByShopNoOrId(register);
		return result;
	}

	public List<PageRegistered> selectPageRegisteredList(PageRegistered register) {
		if(StringUtils.isEmpty(register.getShopNo()) && StringUtils.isEmpty(String.valueOf(register.getId()))){
			return null;
		}
		List<PageRegistered> selectPageRegisteredList = pageRegisteredMapper.selectPageRegisteredList(register);
		if(selectPageRegisteredList == null || selectPageRegisteredList.size()>0){
			logger.info("查询结果集为空!");
		}
		return selectPageRegisteredList;
	}

}
