package com.wechat.manage.service.wshopnav.impl;


import com.wechat.manage.mapper.wshopnav.TNavMapper;
import com.wechat.manage.pojo.wshopnav.entity.TNav;
import com.wechat.manage.pojo.wshopnav.vo.TNavDto;
import com.wechat.manage.pojo.wshopnav.vo.TNavDtoList;
import com.wechat.manage.service.wshopnav.intf.ITNavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TNavServiceImpl implements ITNavService {

	@Autowired
	TNavMapper navMapper;

	@Transactional
	public String insertSelective(List<TNavDto> navList) throws Exception {
		if (navList != null && navList.size() > 0) {
			navMapper.deleteByShopId(navList.get(0).getShopSid());
		}
		for (TNavDto tNavDto : navList) {
			int i = 0;
			TNav entity = new TNav();
			entity.setParentSid(0L);
			entity.setLink(tNavDto.getLinkUrl());
			entity.setName(tNavDto.getTitle());
			entity.setShopId(Long.parseLong(tNavDto.getShopSid()));
			i = navMapper.insertSelective(entity);
			if (i == 1) {
				List<TNavDtoList> navList_2 = tNavDto.getSecond();
				for (TNavDtoList tNavDto2 : navList_2) {
					int i2 = 0;
					TNav entity_2 = new TNav();
					entity_2.setLink(tNavDto2.getLinkUrl());
					entity_2.setParentSid(entity.getSid());
					entity_2.setName(tNavDto2.getTitle());
					entity_2.setShopId(Long.parseLong(tNavDto2.getShopSid()));
					i2 = navMapper.insertSelective(entity_2);
					if (i2 != 1) {
						throw new RuntimeException("操作数据库失败！");
					}
				}
			} else {
				throw new RuntimeException("操作数据库失败！");
			}
		}
		return "success";
	}

	public List<TNavDto> selectByShopId(String shopId) {
		List<TNavDto> selectByShopId = navMapper.selectByShopId(Long.parseLong(shopId));
		return selectByShopId;
	}

}
