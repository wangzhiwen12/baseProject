package com.wechat.manage.service.system.intf;


import com.wechat.manage.pojo.system.vo.ReturnDto;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wangxuan on 2016-12-06 0006.
 */
public interface StoreSynService {
    String imageInsert(String appId, String appSecret, String path, String param);

    @Transactional
    String uploadPhotoList(String storeCode, String path, String param);

    @Transactional
    ReturnDto releaseToWechat(String storeCode) throws Exception;
}
