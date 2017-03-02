package com.wechat.manage.mapper.wshopnav;


import com.wechat.manage.pojo.wshopnav.entity.TShopConfig;

import java.util.List;

public interface TShopConfigMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(TShopConfig record);

    int insertSelective(TShopConfig record);

    TShopConfig selectByPrimaryKey(Long sid);

    List<TShopConfig> selectByParam(TShopConfig record);

    int updateByPrimaryKeySelective(TShopConfig record);

    int updateByPrimaryKey(TShopConfig record);

    List<TShopConfig> shopConfigByShopId(String shopId);
}