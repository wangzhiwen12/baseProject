package com.wechat.manage.service.wechat.intf;


import com.wechat.manage.pojo.wechat.vo.MemberPointInfoReturnDto;

import java.util.List;
import java.util.Map;

/**
 * Created by wangxuan on 2016-11-25 0025.
 */
public interface MemberPointInfoService {
    List<MemberPointInfoReturnDto> findMemberPointDetailByPara(Map<String, Object> paraMap);
}
