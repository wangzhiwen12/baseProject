package com.wechat.manage.service.wshopnav.impl;

import com.wechat.manage.mapper.category.TProGroupMapper;
import com.wechat.manage.service.wshopnav.intf.ITProGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2017/4/13.
 */
@Service
public class TProGroupServiceImpl implements ITProGroupService {

    @Autowired
    private TProGroupMapper tProGroupMapper;

    public List<String> getProListByGroupId(Map<String, Object> paramMap){
        return tProGroupMapper.getProListByGroupId(paramMap);
    }
}
