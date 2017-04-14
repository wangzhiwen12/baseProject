package com.wechat.manage.service.wshopnav.intf;

import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2017/4/13.
 */
public interface ITProGroupService {
    public List<String> getProListByGroupId(Map<String, Object> paramMap);
}
