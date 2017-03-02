package com.wechat.manage.service.wshopnav.impl;


import com.wechat.manage.mapper.wshopnav.TNavRelMapper;
import com.wechat.manage.pojo.wshopnav.entity.TNavRel;
import com.wechat.manage.service.wshopnav.intf.ITNavRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TNavRelServiceImpl implements ITNavRelService {

	@Autowired
	TNavRelMapper relMapper;

	@Override
	@Transactional
	public String insertSelective(List<TNavRel> navList) throws Exception {
		// TODO Auto-generated method stub
		Long shopId = navList.get(0).getShopId();
		relMapper.deleteByShopId(shopId);
		for (TNavRel tNavRel : navList) {
			tNavRel.setCreateTime(new Date());
			int i = relMapper.insertSelective(tNavRel);
			if (i != 1) {
				throw new RuntimeException("操作数据库失败！");
			}
		}
		return "success";
	}

	public List<Map<String, Object>> navRelByShopId(String shopId) {
		return relMapper.navRelByShopId(Long.parseLong(shopId));
	}
}
