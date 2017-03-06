package com.wechat.manage.mapper.wechat;

import com.wechat.manage.pojo.wechat.entity.PageManage;

public interface PageManageMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(PageManage record);

    int insertSelective(PageManage record);

    PageManage selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(PageManage record);

    int updateByPrimaryKey(PageManage record);

    PageManage selectPageInfoByParam(PageManage record);
}