package com.wechat.manage.mapper.wechat;

import com.wechat.manage.pojo.wechat.entity.PrivilegeInfo;

public interface PrivilegeInfoMapper {

	/**
	 * 插入特权信息
	 * @param privilegeInfo
	 */
	public void insertPrivilegeInfo(PrivilegeInfo privilegeInfo);
	
	/**
	 * 根据门店号查询特权信息
	 * @param storeCode
	 * @return
	 */
	public PrivilegeInfo selectPrivilegeInfoByStoreCode(String storeCode);
	
	/**
	 * 修改特权信息内容和开关
	 * @param privilegeInfo
	 */
	public void updatePrivilegeInfo(PrivilegeInfo privilegeInfo);
}
