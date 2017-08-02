package com.wechat.manage.service.usercenter.intf;

import com.wechat.manage.pojo.usercenter.entity.TPage;

public interface IUserCenterService {
	/**
	 * 保存我的会员页面
	 * @param page		页面实体
	 * @param html		页面html代码
	 * @return			是否成功
	 */
	public boolean saveUserCenterPage(TPage page,String html,String data, String storeCode, String path);

	/**
	 * 修改会员页面数据内容
	 * @param data
	 * @param storeCode
	 */
	public void updateUserCenter(String data, String storeCode);

	/**
	 * 查询会员主页数据内容
	 * @param storeCode
	 * @return
	 */
	public String getTitleData(String storeCode);
}
