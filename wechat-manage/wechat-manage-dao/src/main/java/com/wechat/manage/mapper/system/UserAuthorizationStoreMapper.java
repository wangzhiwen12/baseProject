package com.wechat.manage.mapper.system;

import com.wechat.manage.pojo.system.entity.UserAuthorizationStore;

import java.util.List;
import java.util.Map;

public interface UserAuthorizationStoreMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(UserAuthorizationStore record);

    int insertSelective(UserAuthorizationStore record);

    UserAuthorizationStore selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(UserAuthorizationStore record);

    int updateByPrimaryKey(UserAuthorizationStore record);

    List<UserAuthorizationStore> selectListByParam(Map<String, Object> paramMap);

    int updateByUserIdAndStoreCode(UserAuthorizationStore record);
}