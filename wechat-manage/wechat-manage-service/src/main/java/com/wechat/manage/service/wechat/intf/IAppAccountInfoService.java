package com.wechat.manage.service.wechat.intf;


import com.wechat.manage.pojo.system.entity.AppAccountInfo;
import com.wechat.manage.pojo.system.vo.StoreInfoDto;

import java.util.List;
import java.util.Map;

/**
 * Created by kongqf on 16-11-23.
 */
public interface IAppAccountInfoService {

    /**
     * 查询门店与APP关系列表
     *
     * @param paramMap
     * @return
     */
    public List<AppAccountInfo> queryAppAccount(Map<String, Object> paramMap);

    public StoreInfoDto getStoreInfo(String appid);

    public StoreInfoDto getStoreInfo(String appid, String storeCode);

    public boolean UpdateAppAccountInfoByStoreCode(AppAccountInfo dto);
}
