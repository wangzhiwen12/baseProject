package com.wechat.manage.service.wechat.intf;


import com.wechat.manage.pojo.wechat.entity.PrivilegeInfo;

import java.util.Map;

/**
 * 特权service
 * @author Admin
 *
 */
public interface PrivilegeService {

	/**
	 * 发布特权操作
	 * @param privilegeInfo
	 * @return
	 */
	Map<String, Object> alertPrivilege(PrivilegeInfo privilegeInfo);

	/**
	 * 根据门店号查询特权内容
	 * @param storeCode
	 * @return
	 */
	PrivilegeInfo findPrivilegeInfo(String storeCode);


}
