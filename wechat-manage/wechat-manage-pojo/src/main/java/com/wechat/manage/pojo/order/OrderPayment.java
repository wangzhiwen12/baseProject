package com.wechat.manage.pojo.order;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONField;

public class OrderPayment implements Serializable{

	private static final long serialVersionUID = 9016466058118775065L;
	
	private BigDecimal cashBalance;//结余

	/**
	 * 一级支付方式
	 */
	private String paymentClass;
	

	
	/**
	 * 支付金额
	 */
	private String amount;
	/**
	 * 实际支付金额
	 */
	private String acturalAmount;
	/**
	 * 支付账户
	 */
	private String account;
	/**
	 * 支付时间
	 */
//	private String dateTime;
	/**
	 * 支付完成时间
	 */
//	private String payTime;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 支付类型  二级支付方式
	 */
	private String paymentType;
	/**
	 * 汇率
	 */
	private String rate;

	private String userId;
	/**
	 * 优惠券类型
	 */
	private String couponType;
	/**
	 * 券批次
	 */
	private String couponBatch;
	/**
	 * 券模板名称
	 */
	private String couponName;
	/**
	 * 活动号
	 */
	private String activityNo;
	/**
	 * 收券规则
	 */
	private String couponRule;
	/**
	 * 收券规则描述
	 */
	private String couponRuleName;
	
	/**
	 * 是否需要分摊
	 */
//	private Boolean needSplit;
	
	/**
	 * 是否按占比分摊
	 */
//	private Boolean splitByRatio;


}
