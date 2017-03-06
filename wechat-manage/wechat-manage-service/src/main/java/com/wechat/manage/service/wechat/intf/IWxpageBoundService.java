package com.wechat.manage.service.wechat.intf;

import com.wechat.manage.pojo.wechat.vo.BoundCarePara;
import com.wechat.manage.pojo.wechat.entity.WxpageBound;

import java.util.Map;

public interface IWxpageBoundService {
	public String insertSelective(WxpageBound entity);

	public WxpageBound getInfoByStroeCode(Map<String, Object> paramMap);

	public String updateByPrimaryKeySelective(WxpageBound entity);

	public String boundCard(BoundCarePara para);
}
