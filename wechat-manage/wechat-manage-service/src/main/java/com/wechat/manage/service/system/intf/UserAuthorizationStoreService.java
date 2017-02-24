package com.wechat.manage.service.system.intf;


import com.wechat.manage.pojo.system.entity.UserAuthorizationStore;
import com.wechat.manage.pojo.system.vo.UserAuthorizationStoreDto;

import java.util.List;
import java.util.Map;

/**
 * Created by wangxuan on 2016-12-01 0001.
 */
public interface UserAuthorizationStoreService {

    List<UserAuthorizationStoreDto> selectListByUserId(Map<String, Object> paramMap) throws Exception;

    /**
     * 查询授权门店
     *
     * @param paramMap
     * @return
     * @throws Exception
     */
    List<UserAuthorizationStore> getselectListByUserId(Map<String, Object> paramMap) throws Exception;

    void addUserAuthorizationStore(List<UserAuthorizationStoreDto> dtoList) throws Exception;
}
