package com.wechat.manage.mapper.wshopnav;


import com.wechat.manage.pojo.wshopnav.entity.TNavRel;

import java.util.List;
import java.util.Map;

public interface TNavRelMapper {
    int deleteByPrimaryKey(Long sid);

    int deleteByShopId(Long shopId);

    int insert(TNavRel record);

    int insertSelective(TNavRel record);

    TNavRel selectByPrimaryKey(Long sid);

    List<Map<String, Object>> navRelByShopId(Long sid);

    int updateByPrimaryKeySelective(TNavRel record);

    int updateByPrimaryKey(TNavRel record);
}