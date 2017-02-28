package com.wechat.manage.service.wechat.impl;


import com.wechat.manage.mapper.system.AppAccountInfoMapper;
import com.wechat.manage.pojo.system.entity.AppAccountInfo;
import com.wechat.manage.pojo.system.vo.StoreInfoDto;
import com.wechat.manage.pojo.system.vo.WechatAppDto;
import com.wechat.manage.service.wechat.intf.IAppAccountInfoService;
import com.wechat.manage.utils.JsonUtil;
import com.wechat.manage.utils.RedisUtil;
import com.wechat.manage.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kongqf on 16-11-23.
 */
@Service
public class AppAccountInfoServiceImpl implements IAppAccountInfoService {
    private Logger logger = Logger.getLogger(AppAccountInfoServiceImpl.class);

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private AppAccountInfoMapper appAccountInfoMapper;

    /**
     * 查询门店与APP关系列表
     *
     * @param paramMap
     * @return
     */
    public List<AppAccountInfo> queryAppAccount(Map<String, Object> paramMap) {
        List<AppAccountInfo> appAccountInfoList = appAccountInfoMapper.selectListByParam(paramMap);
        return appAccountInfoList;
    }
    public List<WechatAppDto> queryAppAccountByStoreCode(WechatAppDto dto) {
        List<WechatAppDto> list = appAccountInfoMapper.selectAppInfoListByParam(dto);
        return list;
    }


    /**
     * 根据门店标识获取公主号信息
     *
     * @param appid
     * @return StoreInfoDto
     * @Methods Name getStoreInfo
     * @Create In 2016年9月26日 By kongqf
     */
    public StoreInfoDto getStoreInfo(String appid) {
        return getStoreInfo(appid, null);
    }

    public StoreInfoDto getStoreInfo(String appid, String storeCode) {
        String storeInfoStr = "0000";
        StoreInfoDto storeInfoDto = new StoreInfoDto();
        if (StringUtils.isNotEmpty(appid)) {
            storeInfoStr = redisUtil.getKey("storeInfoDto" + appid, "0000");
        }
        if ("0000".equals(storeInfoStr)) {
            WechatAppDto dto =new WechatAppDto();
            Map<String, Object> map = new HashMap<String, Object>();
            if (StringUtils.isNotEmpty(appid)) {
                dto.setAppid(appid);
            }
            if (StringUtils.isNotEmpty(storeCode)) {
               dto.setStorecode(storeCode);
            }

            List<WechatAppDto> appAccountInfos = queryAppAccountByStoreCode(dto);

            if (appAccountInfos != null && appAccountInfos.size() > 0) {
                WechatAppDto model = appAccountInfos.get(0);
                storeInfoDto.setStoreCode(model.getStorecode());
                storeInfoDto.setStoreName(model.getStorename());
                storeInfoDto.setAppId(model.getAppid());
                storeInfoDto.setSecret(model.getAppsecret());
                storeInfoDto.setCardId(model.getCardId());
                storeInfoDto.setCardUrl(model.getCardUrl());
                storeInfoDto.setLogoPic(model.getLogoPic());
                storeInfoDto.setLogoWxPic(model.getLogoWxPic());

                redisUtil.setIsOK("storeInfoDto" + model.getAppid(), JsonUtil.getJSONString(storeInfoDto));
            }

        } else {
            storeInfoDto = JsonUtil.getDTO(storeInfoStr, StoreInfoDto.class);
        }
        return storeInfoDto;
    }

    /**
     * 更新微信卡信息
     *
     * @param dto
     * @return
     */
    public boolean UpdateAppAccountInfoByStoreCode(AppAccountInfo dto) {
        boolean flag = false;
        int count = appAccountInfoMapper.updateByStoreCode(dto);
        if (count != 1) {
            flag = true;
        }

        return flag;
    }
}
