package com.wechat.manage.service.wshopnav.intf;


import com.wechat.manage.pojo.wshopnav.vo.TNavDto;

import java.util.List;

public interface ITNavService {
	public String insertSelective(List<TNavDto> navList) throws Exception;

	public List<TNavDto> selectByShopId(String shopId);
}
