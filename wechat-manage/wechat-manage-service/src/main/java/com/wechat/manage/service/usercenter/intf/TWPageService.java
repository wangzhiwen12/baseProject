package com.wechat.manage.service.usercenter.intf;

import java.util.List;

import com.wechat.manage.pojo.usercenter.entity.TPage;
/**
 * 微页面
 * @author Administrator
 * @date 2017年1月17日 下午5:02:43
 */
public interface TWPageService {
	
	/**
	 * 获取微页面内容
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<TPage> selectWPageInfo(String userId,String page,String pageSize);
	
	/**
	 * 获取微页面内容(草稿页面)
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<TPage> selectWPageInfo2(String userId,String page,String pageSize);
	
	/**
	 * 获取条数
	 * @param userId
	 * @return
	 */
	public Long selectWPageInfoCount(String userId);
	
	/**
	 * 获取条数(草稿页面)
	 * @param userId
	 * @return
	 */
	public Long selectWPageInfoCount2(String userId);
	
	/**
	 * 删除页面
	 * @param sid
	 * @param createUser
	 */
	public void deleteWPage(String sid,String createUser);
	
	/**
	 * 更新 修改
	 * @param tpage
	 */
	public void updateWpage(TPage tpage);

	
	/**
	 * 设为主页
	 * @param tpage
	 */
	public void updateHomePage(TPage tpage);
	
	

	/**
	 * 插入
	 * @param tPage
	 */

	void insertSelective(TPage tPage);
	
	/**
	 * 根据制定条件查询微页面
	 * @param tPage
	 * @return
	 */
	public List<TPage> selectTPage(TPage tPage);
	
	/**
	 * 复制微页面(数据库)
	 * @param tPage
	 * @param newName
	 */
	public void inestCopyFile(TPage tPage,String newName);

}
