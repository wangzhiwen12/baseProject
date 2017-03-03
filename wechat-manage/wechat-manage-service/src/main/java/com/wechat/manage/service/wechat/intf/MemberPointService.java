package com.wechat.manage.service.wechat.intf;


import com.wechat.manage.pojo.wechat.vo.MemberPointReturnDto;

import java.util.Map;

/**
 * Created by wangxuan on 2016-11-28 0028.
 */
public interface MemberPointService {
    MemberPointReturnDto findMemberPointByPara(Map<String, Object> paraMap);
}
