package com.wechat.manage.service.usercenter.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wechat.manage.mapper.wshopnav.TPageEditMapper;
import com.wechat.manage.pojo.wshopnav.entity.TPageEdit;
import com.wechat.manage.service.usercenter.intf.TPageService;

@Service
public class TPageServiceImpl implements TPageService {
	@Autowired
	private TPageEditMapper tPageEditMapper;

	@Override
	public void insertSelective(TPageEdit tPageEdit) {
		tPageEditMapper.insertTpageEdit(tPageEdit);
	}

	@Override
	public List<TPageEdit> selectById(String id) {
		
		return  tPageEditMapper.selectById(id);
	}

	@Override
	public void update(TPageEdit pageEdit) {
		tPageEditMapper.update(pageEdit);
	}

	@Override
	public void deleteWPage(String id) {
		tPageEditMapper.deleteWpage(id);
		
	}

	
}
