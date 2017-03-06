package com.wechat.manage.mapper.wechat;

import java.util.Map;

import com.wechat.manage.pojo.wechat.entity.WechatCardInfo;
import com.wechat.manage.pojo.wechat.vo.WechatCardInfoDto;

public interface WechatCardInfoMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(WechatCardInfo record);

    int insertSelective(WechatCardInfoDto record);

    WechatCardInfo selectByPrimaryKey(Long sid);

    WechatCardInfo selectCardInfoByStoreCode(Map<String, Object> mapPara);

    int updateByPrimaryKeySelective(WechatCardInfoDto record);

    int updateByPrimaryKey(WechatCardInfo record);
}