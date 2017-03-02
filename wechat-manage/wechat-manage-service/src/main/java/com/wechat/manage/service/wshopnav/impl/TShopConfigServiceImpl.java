package com.wechat.manage.service.wshopnav.impl;


import com.wechat.manage.mapper.wshopnav.TShopConfigMapper;
import com.wechat.manage.pojo.wshopnav.entity.TShopConfig;
import com.wechat.manage.service.wshopnav.intf.ITShopConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TShopConfigServiceImpl implements ITShopConfigService {

	@Autowired
	TShopConfigMapper configMapper;

	@Override
	@Transactional
	public String insertSelective(List<TShopConfig> navList) throws Exception {
		// TODO Auto-generated method stub
		for (TShopConfig config : navList) {
			TShopConfig configV = new TShopConfig();
			configV.setConfigKey(config.getConfigKey());
			configV.setShopId(config.getShopId());
			List<TShopConfig> configList = configMapper.selectByParam(configV);
			if (configList != null && configList.size() > 0) {
				config.setSid(configList.get(0).getSid());
				int u = configMapper.updateByPrimaryKeySelective(config);
				if (u != 1) {
					throw new RuntimeException("操作数据库失败！");
				}
			} else {
				int i = configMapper.insertSelective(config);
				if (i != 1) {
					throw new RuntimeException("操作数据库失败！");
				}
			}
		}
		return "success";
	}

	public List<TShopConfig> shopConfigByShopId(String shopId) {
		TShopConfig record = new TShopConfig();
		record.setShopId(Long.parseLong(shopId));
		return configMapper.selectByParam(record);
	}

}
