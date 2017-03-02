package com.wechat.manage.mapper.wechat;


import com.wechat.manage.pojo.wechat.entity.WxPageHome;

public interface WxPageHomeMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(WxPageHome record);

    int insertSelective(WxPageHome record);

    WxPageHome selectByPrimaryKey(Long sid);

    WxPageHome selectPageHomeByPara(WxPageHome record);

    WxPageHome selectPageHomeByStoreCode(WxPageHome record);

    int updateByPrimaryKeySelective(WxPageHome record);

    int updateByPrimaryKey(WxPageHome record);


}