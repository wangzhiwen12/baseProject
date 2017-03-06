package com.wechat.manage.service.wechat.intf;

import com.wechat.manage.pojo.wechat.entity.PageRegistered;

import java.util.List;

/**
 * Created by XS on 2017/1/18.
 */
public interface RegisteredService {
	/**
	 * 保存PageRegistered
	 * @param register
	 * @return
	 */
	Integer savePageRegister(PageRegistered register);
	/**
	 * 修改PageRegistered
	 * @param register
	 * @return
	 */
	Integer updatePageRegistered(PageRegistered register);
	/**
	 * 删除PageRegistered
	 * @param register
	 * @return
	 */
	Integer deletePageRegistered(PageRegistered register);
	/**
	 * 查询PageRegistered
	 * @param register
	 * @return
	 */
	List<PageRegistered> selectPageRegisteredList(PageRegistered register);
}
