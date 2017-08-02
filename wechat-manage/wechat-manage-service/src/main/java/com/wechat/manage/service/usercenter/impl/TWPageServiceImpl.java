package com.wechat.manage.service.usercenter.impl;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wechat.manage.mapper.wechat.WPageMapper;
import com.wechat.manage.pojo.usercenter.entity.TPage;
import com.wechat.manage.service.usercenter.intf.TWPageService;
/**
 * 微页面
 * @author Administrator
 * @date 2017年1月17日 下午5:02:24
 */
@Service
public class TWPageServiceImpl implements TWPageService {
	private Logger logger = Logger.getLogger(TWPageServiceImpl.class);
	@Autowired
	private WPageMapper wPageMapper;
	
	/**
	 * 分页查询微页面首页数据
	 */
	@Override
	public List<TPage> selectWPageInfo(String userId, String page, String pageSize) {
		try {
			List<TPage> list = wPageMapper.selectWPageInfo(userId, Integer.valueOf(page), Integer.valueOf(pageSize));//获取内容
			logger.info("请求微页面返回结果"+list);
			return list;
		} catch (Exception e) {
			logger.info("请求微页面失败,参数id:"+userId+",页数:"+page+",每页数量"+pageSize);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Long selectWPageInfoCount(String userId) {
		Integer count = wPageMapper.selectWPageInfoCount(userId);
		return Long.valueOf(count);
	}
	
	/**
	 * 删除页面
	 */
	@Override
	public void deleteWPage(String sid, String createUser) {
		try {
			wPageMapper.delWPage(Integer.valueOf(sid), Integer.valueOf(createUser));
			logger.info("删除微页面成功,sid:"+sid+",用户名id:"+createUser);
		} catch (Exception e) {
			logger.info("删除微页面失败,sid:"+sid+",用户名id:"+createUser);
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateWpage(TPage tpage) {
		try {
			tpage.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			wPageMapper.updateWpage(tpage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 设为主页
	 */
	@Override
	public void updateHomePage(TPage tpage) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		TPage page = wPageMapper.selectHomePage(Integer.valueOf(tpage.getCreateUser()));//获取当前用户的主页
		if(page != null){ //当所有微页面都为非主页时，不用走updateWpage
			TPage page1 = new TPage();
			page1.setCreateTime(timestamp);
			page1.setIsHome("0");
			page1.setCreateUser(tpage.getCreateUser());
			page1.setSid(page.getSid());
			wPageMapper.updateWpage(page1);//将当前主页改为非主页
		}
		tpage.setStatus("1");
		tpage.setUpdateTime(timestamp);
		tpage.setIsHome("1");
		wPageMapper.updateWpage(tpage);//将选中的页面，设为主页
	}
	
	/**
	 * 获取微页面内容(草稿页面)
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<TPage> selectWPageInfo2(String userId, String page, String pageSize) {
		try {
			List<TPage> list = wPageMapper.selectWPageInfo2(userId, Integer.valueOf(page), Integer.valueOf(pageSize));//获取内容
			logger.info("请求微页面返回结果"+list);
			return list;
		} catch (Exception e) {
			logger.info("请求微页面失败,参数id:"+userId+",页数:"+page+",每页数量"+pageSize);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取条数(草稿页面)
	 * @param userId
	 * @return
	 */
	public Long selectWPageInfoCount2(String userId) {
		Integer count = wPageMapper.selectWPageInfoCount2(userId);
		return Long.valueOf(count);
	}
	

	@Override
	public void insertSelective(TPage tPage) {
		try {
			Timestamp updateTime = new Timestamp(System.currentTimeMillis());
			tPage.setUpdateTime(updateTime);
			wPageMapper.insertSelective(tPage);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<TPage> selectTPage(TPage tPage) {
		return wPageMapper.selectTPage(tPage);
	}
	
	/**
	 * 复制页面
	 */
	@Override
	public void inestCopyFile(TPage tPage, String newName) {
		try {
			TPage page = wPageMapper.selectTPage(tPage).get(0);
			String pageLink = page.getPageLink();
			int last = pageLink.lastIndexOf("/");
			pageLink = pageLink.substring(0, last+1)+newName;
			logger.info("复制页面信息:"+page.toString());
			page.setSid(null);
			page.setPageLink(pageLink);
			page.setCreateTime(new Timestamp(System.currentTimeMillis()));
			page.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			if(page.getIsHome().equals("1") || page.getIsHome()=="1"){
				page.setIsHome("0");
			}
			logger.info("复制新页面信息:"+page.toString());
			wPageMapper.insertSelective(page);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
	}


}
