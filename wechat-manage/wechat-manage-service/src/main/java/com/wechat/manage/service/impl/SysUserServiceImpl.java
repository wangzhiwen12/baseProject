package com.wechat.manage.service.impl;

import com.wechat.manage.mapper.system.SysUserMapper;
import com.wechat.manage.pojo.system.SysUser;
import com.wechat.manage.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * SysUserServiceImpl
 *
 * @author kongqf
 * @create 2016-12-24
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getById(Long id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }
}
