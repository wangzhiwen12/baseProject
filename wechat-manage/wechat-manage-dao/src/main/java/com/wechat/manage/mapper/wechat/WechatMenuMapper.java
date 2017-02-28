package com.wechat.manage.mapper.wechat;


import com.wechat.manage.pojo.wechat.entity.WechatMenu;

import java.util.List;

public interface WechatMenuMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(WechatMenu record);

    int insertSelective(WechatMenu record);

    WechatMenu selectByPrimaryKey(Long sid);

    List<WechatMenu> selectByParam(WechatMenu record);

    int updateByPrimaryKeySelective(WechatMenu record);

    int updateByPrimaryKey(WechatMenu record);
}