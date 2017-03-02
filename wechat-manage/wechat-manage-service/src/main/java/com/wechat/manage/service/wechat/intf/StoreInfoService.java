package com.wechat.manage.service.wechat.intf;


import com.wechat.manage.pojo.system.entity.StoreInfo;
import com.wechat.manage.pojo.system.vo.ReturnDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wangxuan on 2016-12-01 0001.
 */
public interface StoreInfoService {
    @Transactional
    ReturnDto addStore(StoreInfo storeInfo) throws Exception;

    @Transactional
    ReturnDto editStore(StoreInfo storeInfo) throws Exception;

    @Transactional
    ReturnDto batchDelStore(List<String> storeCodeList);

    public String getLogoUrl(String storeCode);
}
