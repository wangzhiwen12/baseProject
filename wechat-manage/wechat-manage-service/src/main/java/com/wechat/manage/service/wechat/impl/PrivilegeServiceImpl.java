package com.wechat.manage.service.wechat.impl;

import java.sql.Timestamp;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wechat.manage.mapper.wechat.PrivilegeInfoMapper;
import com.wechat.manage.pojo.wechat.entity.PrivilegeInfo;
import com.wechat.manage.service.wechat.intf.PrivilegeService;
import com.wechat.manage.utils.ResultUtil;

/**
 * 特权service实现类
 * @author Admin
 *
 */
@Service
public class PrivilegeServiceImpl implements PrivilegeService {
	private static final Logger log = Logger.getLogger(PrivilegeServiceImpl.class);
	@Autowired
	PrivilegeInfoMapper privilegeInfoMapper;
	/**
	 * 发布特权操作
	 */
	public Map<String, Object> alertPrivilege(PrivilegeInfo privilegeInfo) {
		log.info(privilegeInfo.getStoreCode() + "门店特权内容发布内容为：" + privilegeInfo.getPrivilegeContent());
		//根据门店号查询特权信息
		PrivilegeInfo privilege = privilegeInfoMapper.selectPrivilegeInfoByStoreCode(privilegeInfo.getStoreCode());
		//判断是否为第一次发布
		privilegeInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		if (privilege == null) { //是第一次
			privilegeInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
			try {
				privilegeInfoMapper.insertPrivilegeInfo(privilegeInfo);
				return ResultUtil.creComSucResult("发布成功");
			} catch (Exception e) {
				log.error(e.toString(), e);
				log.info("特权发布失败，失败门店号为:" + privilegeInfo.getStoreCode());
				return ResultUtil.creComErrorResult("1001", "发布失败");
			}
		}
		
		//不是第一次就修改特权内容
		try {
		privilegeInfoMapper.updatePrivilegeInfo(privilegeInfo);
		return ResultUtil.creComSucResult("发布成功");
		} catch (Exception e) {
			log.error(e.toString(), e);
			log.info("特权发布失败，失败门店号为:" + privilegeInfo.getStoreCode());
			return ResultUtil.creComErrorResult("1001", "发布失败");
		}
	}
	
	/**
	 * 根据门店号查询特权内容
	 */
	public PrivilegeInfo findPrivilegeInfo(String storeCode) {
		return privilegeInfoMapper.selectPrivilegeInfoByStoreCode(storeCode);
	}

}
