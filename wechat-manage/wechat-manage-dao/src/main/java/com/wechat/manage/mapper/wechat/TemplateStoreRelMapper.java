package com.wechat.manage.mapper.wechat;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wechat.manage.pojo.wechat.entity.TemplateStoreRel;

/**
 * 微信门店消息模板相关
 * @author Administrator
 * @date 2016年12月29日 上午10:34:39
 */
public interface TemplateStoreRelMapper {
	
	/**
	 * 模板类型内容
	 * @return
	 */
	public List<TemplateStoreRel> selectWechatInfoType();
	
	/**
	 * 根据门店号和模版编码查询模版信息
	 * @param storeId		门店号
	 * @param templateCode	自定义模版编码
	 * @return
	 */
	public TemplateStoreRel selectTemplateStoreRelByStoreId(@Param("storeId") String storeId, @Param("templateCode") String templateCode);
	
	/**
	 * 模板类型（作废）
	 * @param store
	 * @return
	 */
	public List<TemplateStoreRel> selectWechatTemplateType(@Param("store") String store);
	
	/**
	 * 修改门店模板状态
	 * @param status
	 * @param store
	 * @param templateNo
	 */
	public void updateTemplateState(@Param("status") String status, @Param("store") String store,@Param("messageInfoSid") String messageInfoSid,@Param("updateTime") Timestamp updateTime);
	
	/**
	 * 修改门店微信消息模板id
	 * @param storeTemplateId
	 * @param store
	 * @param templateNo
	 */
	public void updateStoreTemplateId(@Param("storeTemplateId") String storeTemplateId, @Param("store") String store,@Param("templateNo") String templateNo);
	
	/**
	 * 查询门店表中是否有对应的门店信息
	 * @param store   门店编码
	 * @param messageInfoSid   微信消息表主键  门店表message_info_sid字段
	 * @return
	 */
	public TemplateStoreRel selectStoreTemplate(@Param("store")String store,@Param("messageInfoSid") String messageInfoSid);
	
	/**
	 * 在门店表中插入数据
	 * @param templateStoreRel
	 */
	public void insertStoreTemplate(TemplateStoreRel templateStoreRel);
	
	/**
	 * 查询门店对应的信息
	 * @param store 门店号
	 * @return
	 */
	public List<TemplateStoreRel> selectStoreRel(String store);
	
	/**
	 * 当修改的模板id值为空，开关状态改为0
	 * @param store
	 * @param templateNo
	 */
	public void updateStoreTemplateIda(@Param("store") String store,@Param("templateNo") String templateNo);
}
