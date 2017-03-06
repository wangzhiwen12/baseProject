package com.wechat.manage.mapper.wechat;

import com.wechat.manage.pojo.wechat.entity.WxVIPCard;

public interface WxVIPCardMapper {

	public void insertWxVIPCard(WxVIPCard wxVIPCard);
	
	public void updateWxVipCard(WxVIPCard wxVIPCard);
	
	public WxVIPCard selectWxVipCardByStoreCode(String storeCode);
}
