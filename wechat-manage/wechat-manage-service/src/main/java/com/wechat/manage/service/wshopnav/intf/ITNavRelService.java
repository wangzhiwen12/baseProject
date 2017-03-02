package com.wechat.manage.service.wshopnav.intf;


import com.wechat.manage.pojo.wshopnav.entity.TNavRel;

import java.util.List;
import java.util.Map;

public interface ITNavRelService {
	public String insertSelective(List<TNavRel> navList) throws Exception;

	public List<Map<String, Object>> navRelByShopId(String shopId);
}
