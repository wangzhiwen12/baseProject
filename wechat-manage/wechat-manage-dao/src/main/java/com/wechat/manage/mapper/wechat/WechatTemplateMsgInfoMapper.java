package com.wechat.manage.mapper.wechat;

import com.wechat.manage.pojo.wechat.entity.WechatTemplateMsgInfo;

/**
 * 微信模版消息mapper
 * @author Admin
 *
 */
public interface WechatTemplateMsgInfoMapper {

	/**
	 * 根据memberCode查询模版消息信息
	 * @param memberCode
	 * @return
	 */
	WechatTemplateMsgInfo selectTemplateMsgInfoBy(String memberCode);

}
