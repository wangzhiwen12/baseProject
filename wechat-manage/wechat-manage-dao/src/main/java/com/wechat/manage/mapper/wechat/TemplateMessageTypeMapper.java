package com.wechat.manage.mapper.wechat;

import java.util.List;

import com.wechat.manage.pojo.wechat.entity.TemplateMessageType;

/**
 * 微信模板类型
 * @author Administrator
 * @date 2016年12月29日 上午10:54:18
 */
public interface TemplateMessageTypeMapper {
	
	public List<TemplateMessageType> selectWechatTemplateType();
}
