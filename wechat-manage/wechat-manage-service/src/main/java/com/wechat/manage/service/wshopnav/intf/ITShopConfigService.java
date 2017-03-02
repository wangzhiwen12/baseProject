package com.wechat.manage.service.wshopnav.intf;


import com.wechat.manage.pojo.wshopnav.entity.TShopConfig;

import java.util.List;

public interface ITShopConfigService {

	public String insertSelective(List<TShopConfig> navList) throws Exception;

	public List<TShopConfig> shopConfigByShopId(String shopId);
}
