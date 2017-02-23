package com.wechat.manage.service.system.intf;

import com.wechat.manage.pojo.system.entity.SysUser;

/**
 * @author kongqf
 * @create 2016-12-24
 */
public interface SysUserService {
    SysUser getById(Long id);
}
