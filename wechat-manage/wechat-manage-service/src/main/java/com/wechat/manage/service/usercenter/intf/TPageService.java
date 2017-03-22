package com.wechat.manage.service.usercenter.intf;

import java.util.List;

import com.wechat.manage.pojo.wshopnav.entity.TPageEdit;

public interface TPageService {
	/**
	 * 插入
	 * @param tPage
	 */
	public void insertSelective( TPageEdit tPage);
	
	public List<TPageEdit> selectById(String id);
	
	public void update(TPageEdit pageEdit);
	
	public void deleteWPage(String id);
}