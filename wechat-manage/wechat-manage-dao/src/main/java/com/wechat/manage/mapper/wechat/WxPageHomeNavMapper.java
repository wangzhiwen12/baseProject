package com.wechat.manage.mapper.wechat;

import com.wechat.manage.pojo.wechat.entity.WxPageHomeNav;

import java.util.List;

public interface WxPageHomeNavMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(WxPageHomeNav record);

    int insertSelective(WxPageHomeNav record);

    WxPageHomeNav selectByPrimaryKey(Long sid);

    List<WxPageHomeNav> selectPageHomeNavByPara(WxPageHomeNav record);

    int updateByPrimaryKeySelective(WxPageHomeNav record);

    int updateByPrimaryKey(WxPageHomeNav record);

    int updateByStoreCode(WxPageHomeNav record);
}