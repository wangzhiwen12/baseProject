package com.wechat.manage.mapper.wechat;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wechat.manage.pojo.usercenter.entity.TPage;
/**
 * 微页面
 * @author Administrator
 * @date 2017年1月17日 下午5:03:00
 */
public interface WPageMapper {
	
	/**
	 * 分页
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<TPage> selectWPageInfo(@Param("userId")String userId,@Param("page")Integer page,@Param("pageSize")Integer pageSize);
	
	/**
	 * 分页(草稿页面)
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<TPage> selectWPageInfo2(@Param("userId")String userId,@Param("page")Integer page,@Param("pageSize")Integer pageSize);
	
	/**
	 * count
	 * @param userId
	 * @return
	 */
	public Integer selectWPageInfoCount(@Param("userId")String userId);
	
	/**
	 * count(草稿页面)
	 * @param userId
	 * @return
	 */
	public Integer selectWPageInfoCount2(@Param("userId")String userId);
	
	/**
	 * 删除微页面
	 * @param sid
	 * @param createUser
	 */
	public void delWPage(@Param("sid")Integer sid,@Param("createUser")Integer createUser);
	
	/**
	 * 更新
	 * @param tPage
	 */
	public void updateWpage(TPage tPage);
	
	/**

	 * 查询当前用户的主页
	 * @param createUser
	 * @return
	 */
	public TPage selectHomePage(@Param("createUser")Integer createUser);

	 /**
	 * 插入
	 * @param wpInfo
	 */
	public void insertSelective(TPage tPage);
	
	/**
	 * 根据制定条件查询微页面
	 * @param tPage
	 * @return
	 */
	public List<TPage> selectTPage(TPage tPage);
	

}
