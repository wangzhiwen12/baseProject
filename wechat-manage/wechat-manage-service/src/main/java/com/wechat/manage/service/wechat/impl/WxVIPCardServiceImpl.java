package com.wechat.manage.service.wechat.impl;

import com.wechat.manage.mapper.wechat.WxVIPCardMapper;
import com.wechat.manage.pojo.wechat.entity.WxVIPCard;
import com.wechat.manage.pojo.wechat.vo.WxVIPCardDto;
import com.wechat.manage.service.wechat.intf.WxVIPCardService;
import com.wechat.manage.utils.ResultUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Map;

@Service
public class WxVIPCardServiceImpl implements WxVIPCardService {
	private static final Logger log = Logger.getLogger(WxVIPCardServiceImpl.class);

	@Autowired
	private WxVIPCardMapper wxVIPCardMapper;

	@Override
	public WxVIPCard findWxVIPCardByStoreCode(String storeCode) {
		return wxVIPCardMapper.selectWxVipCardByStoreCode(storeCode);
	}

	@Override
	public Map<String, Object> saveWxVIPCard(WxVIPCardDto vipCardDto) {
		WxVIPCard vipCard = wxVIPCardMapper.selectWxVipCardByStoreCode(vipCardDto.getStoreCode());
		if (vipCard != null) {
			vipCard.setHeadName(vipCardDto.getHeadName());
			vipCard.setHeadPictureUrl(vipCardDto.getHeadPictureUrl());
			vipCard.setWxHeadPictureUrl(vipCardDto.getWxHeadPictureUrl());
			vipCard.setBrandCodeStatus(vipCardDto.getBrandCodeStatus());
			vipCard.setOnlineCodeStatus(vipCardDto.getOnlineCodeStatus());
			vipCard.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			try {
				wxVIPCardMapper.updateWxCipCard(vipCard);
				return ResultUtil.creComSucResult("发布成功");
			} catch (Exception e) {
				log.error(e.toString(), e);
				log.info("品牌会员卡发布失败，失败门店号为:" + vipCardDto.getStoreCode());
				return ResultUtil.creComErrorResult("1001", "发布失败");
			}
		} 
		
		vipCard = new WxVIPCard();
		vipCard.setHeadName(vipCardDto.getHeadName());
		vipCard.setHeadPictureUrl(vipCardDto.getHeadPictureUrl());
		vipCard.setWxHeadPictureUrl(vipCardDto.getWxHeadPictureUrl());
		vipCard.setBrandCodeStatus(vipCardDto.getBrandCodeStatus());
		vipCard.setOnlineCodeStatus(vipCardDto.getOnlineCodeStatus());
		vipCard.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		vipCard.setCreateTime(new Timestamp(System.currentTimeMillis()));
		vipCard.setStoreCode(vipCardDto.getStoreCode());
		
			try {
				wxVIPCardMapper.insertWxVIPCard(vipCard);
				return ResultUtil.creComSucResult("发布成功");
			} catch (Exception e) {
				log.error(e.toString(), e);
				log.info("品牌会员卡发布失败，失败门店号为:" + vipCardDto.getStoreCode());
				return ResultUtil.creComErrorResult("1001", "发布失败");
			}
	}
}
