package com.wechat.manage.mapper.wshopnav;


import com.wechat.manage.pojo.wshopnav.entity.TNav;
import com.wechat.manage.pojo.wshopnav.vo.TNavDto;

import java.util.List;

public interface TNavMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(TNav record);

    int insertSelective(TNav record);

    List<TNavDto> selectByShopId(Long ShopId);

    TNav selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(TNav record);

    int updateByPrimaryKey(TNav record);

    int deleteByShopId(String shopId);
}