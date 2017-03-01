package com.wechat.manage.mapper.wechat;

import com.wechat.manage.pojo.wechat.entity.CouponRule;

import java.util.List;

public interface CouponRuleMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(CouponRule record);

    int insertSelective(CouponRule record);

    CouponRule selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(CouponRule record);

    int updateByPrimaryKey(CouponRule record);

    List<CouponRule> selectCouponRuleInfo(CouponRule record);
}