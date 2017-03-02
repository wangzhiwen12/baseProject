package com.wechat.manage.service.wechat.impl;

import com.wechat.manage.pojo.wechat.entity.GlobalDic;
import com.wechat.manage.mapper.wechat.GlobalDicMapper;
import com.wechat.manage.service.wechat.intf.IGlobalDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * dic
 *
 * @author kongqf
 * @create 2016-12-08
 */
@Service
public class GlobalDicServiceImpl implements IGlobalDicService {

    @Autowired
    private GlobalDicMapper globalDicMapper;

    /**
     * 查询字典列表
     *
     * @param record
     * @return
     */
    public List<GlobalDic> queryDicList(GlobalDic record) {
        return globalDicMapper.selectDicListByType(record);
    }
}
