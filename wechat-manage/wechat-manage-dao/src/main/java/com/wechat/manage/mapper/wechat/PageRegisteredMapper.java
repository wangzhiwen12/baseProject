package com.wechat.manage.mapper.wechat;

import com.wechat.manage.pojo.wechat.entity.PageRegistered;

import java.util.List;

public interface PageRegisteredMapper {
    int deleteByPrimaryKey(Integer id);
    /**
     * 根据门店或id删除
     * @param record
     * @return
     */
    Integer deleteByShopNoOrId(PageRegistered record);
    
    int insert(PageRegistered record);

    int insertSelective(PageRegistered record);

    PageRegistered selectByPrimaryKey(Integer id);
    /**
     * 门店或id修改
     * @param record
     * @return
     */
    Integer updateByShopNoOrId(PageRegistered record);
    
    int updateByPrimaryKeySelective(PageRegistered record);

    int updateByPrimaryKey(PageRegistered record);
    /**
     * 门店或id查询
     * @param record
     * @return
     */
    List<PageRegistered> selectPageRegisteredList(PageRegistered record);
}