package com.wechat.manage.service.wechat.intf;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * 微信模板管理相关service
 * @author Administrator
 * @date 2016年12月29日 上午11:45:27
 */
public interface IWeChatTemplateService {
	
	/**
	 * 查询微信消息模板
	 * @param store
	 * @return
	 */
	public JSONObject selectWechatInfoType(String store);
	
	/**
	 * 修改门店模板状态
	 * @param status
	 * @param store
	 * @param templateNo
	 */
	public void updateTemplateState(String status,String store,String messageInfoSid);
	
	/**
	 * 修改门店微信消息模板id
	 * @param storeTemplateId
	 * @param store
	 * @param infoSid
	 */
	public void updateStoreTemplateId(String storeTemplateId,String store,String infoSid);
	
	
	
}
