package com.wechat.manage.service.wechat.intf;


import com.wechat.manage.pojo.wechat.entity.WxVIPCard;
import com.wechat.manage.pojo.wechat.vo.WxVIPCardDto;

import java.util.Map;

public interface WxVIPCardService {

	/**
	 * 根据门店号查询品牌会员卡内容
	 * @return
	 */
	WxVIPCard findWxVIPCardByStoreCode(String storeCode);

	/**
	 * 保存品牌会员卡
	 * @param vipCardDto
	 * @return
	 */
	Map<String, Object> saveWxVIPCard(WxVIPCardDto vipCardDto);

}
