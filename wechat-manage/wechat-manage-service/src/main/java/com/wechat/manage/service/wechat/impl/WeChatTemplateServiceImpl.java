package com.wechat.manage.service.wechat.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.wechat.manage.mapper.wechat.TemplateMessageTypeMapper;
import com.wechat.manage.mapper.wechat.TemplateStoreRelMapper;
import com.wechat.manage.pojo.wechat.entity.Template;
import com.wechat.manage.pojo.wechat.entity.TemplateMessageType;
import com.wechat.manage.pojo.wechat.entity.TemplateStoreRel;
import com.wechat.manage.service.wechat.intf.IWeChatTemplateService;
/**
 * 微信模板管理相关service实现接口
 * @author Administrator
 * @date 2016年12月29日 上午11:47:17
 */
@Service
public class WeChatTemplateServiceImpl implements IWeChatTemplateService {
	private static Logger logger = Logger.getLogger(WeChatTemplateServiceImpl.class);
	
	@Autowired
	private TemplateStoreRelMapper templateStoreRelMapper;
	@Autowired
	private TemplateMessageTypeMapper templateMessageTypeMapper;
	
	/**
	 * 根据门店号查询对应的模板类型
	 */
	public JSONObject selectWechatInfoType(String store) {
		JSONObject jsonObject = new JSONObject();
		try {

			List<TemplateMessageType> type = templateMessageTypeMapper.selectWechatTemplateType();//模板类型
			List<TemplateStoreRel> list = templateStoreRelMapper.selectWechatInfoType();//模板消息内容
			List<TemplateStoreRel> storeRelList = templateStoreRelMapper.selectStoreRel(store);//门店对应信息
			List<Template> templateList = new ArrayList<Template>();
			for(TemplateMessageType msg:type){
				Template template = new Template();
				template.setSid(msg.getSid());
				template.setTypeName(msg.getTypeName());
				List<TemplateStoreRel> redList = new ArrayList<TemplateStoreRel>();
				for(TemplateStoreRel rel : list){
					String typeId = String.valueOf(rel.getMessageTypeId());
					if(typeId.equals(msg.getSid())){
						
						for(TemplateStoreRel relList:storeRelList){
							if(relList.getMessageInfoSid().equals(rel.getInfoSid())){
								rel.setMessageInfoSid(relList.getMessageInfoSid());
								rel.setStoreSid(relList.getStoreSid());
								rel.setOpenStatus(relList.getOpenStatus());
								rel.setStoreTemplateId(relList.getStoreTemplateId());
							}
						}

						redList.add(rel);
					}
				}
				template.setTemplate(redList);
				templateList.add(template);
			}
			jsonObject.put("list", templateList);
			jsonObject.put("success", "true");
			return jsonObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("门店编码:"+store);
			logger.error(e.toString(),e);
			jsonObject.put("success", "false");
			return jsonObject;
		}
	}
	
	/**
	 * 修改门店模板状态
	 */
	public void updateTemplateState(String status, String store, String messageInfoSid) {
		try {
			SimpleDateFormat time = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Timestamp updateTime = Timestamp.valueOf(time.format(new Date()));
			templateStoreRelMapper.updateTemplateState(status, store, messageInfoSid,updateTime);
			logger.info("修改门店微信消息模板状态成功!门店编码:"+store+",修改状态:"+status+",关联微信模板ID:"+messageInfoSid);
		} catch (Exception e) {
			logger.error("修改门店微信消息模板状态失败!门店编码:"+store+",修改状态:"+status+",关联微信模板ID:"+messageInfoSid);
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 修改门店微信消息模板id
	 */
	public void updateStoreTemplateId(String storeTemplateId, String store, String infoSid) {
		try {
			TemplateStoreRel rel = templateStoreRelMapper.selectStoreTemplate(store, infoSid);
			if(rel !=null ){  //判断当前表中时候有门店对应的微信模板信息，如果存在更改，如果不存在插如新的记录
				if(storeTemplateId.length() !=0 || !"".equals(storeTemplateId)){
					templateStoreRelMapper.updateStoreTemplateId(storeTemplateId, store, infoSid);
				}else{
					templateStoreRelMapper.updateStoreTemplateIda(store, infoSid);
				}
				logger.info("修改门店微信消息模板id成功!门店编码:"+store+",修改门店微信模板id:"+storeTemplateId+",信息表sid:"+infoSid);
			}else{
				TemplateStoreRel stortRel = new TemplateStoreRel();
				stortRel.setStoreSid(store);
				stortRel.setMessageInfoSid(infoSid);
				stortRel.setOpenStatus("0");
				stortRel.setStoreTemplateId(storeTemplateId);
				templateStoreRelMapper.insertStoreTemplate(stortRel);
				logger.info("插入门店微信消息模板id成功!门店编码:"+store+",修改门店微信模板id:"+storeTemplateId+",信息表sid:"+infoSid);
			}
		} catch (Exception e) {
			logger.info("修改门店微信消息模板id失败!门店编码:"+store+",修改门店微信模板id:"+storeTemplateId+",信息表sid:"+infoSid);
			e.printStackTrace();
		}
	}

}
