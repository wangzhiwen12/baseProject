package com.wechat.manage.mapper.usercenter;

import com.wechat.manage.pojo.usercenter.entity.TUserCenter;

public interface TUserCenterMapper {

	/**
	 * 根据门店号查询
	 * @param storeCode
	 * @return
	 */
	public TUserCenter selectUserCenterByStoreCode(String storeCode);
	
	/**
	 * 保存
	 * @param tUserCenter
	 */
	public void insertUserCenter(TUserCenter tUserCenter);
	
	/**
	 * 修改
	 * @param tUserCenter
	 */
	public void updateUserCenter(TUserCenter tUserCenter);
}
