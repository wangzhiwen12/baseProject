package com.wechat.manage.service.wechat.intf;

import com.wechat.manage.pojo.wechat.entity.GlobalDic;

import java.util.List;

/**
 * @author kongqf
 * @create 2016-12-08
 */
public interface IGlobalDicService {

    public List<GlobalDic> queryDicList(GlobalDic record);
}
