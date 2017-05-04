package com.wechat.manage.pojo.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
* @ClassName: Order
* @Description: 订单信息
* @author
* @date 2017年5月4日15:10:05
* @version : 1.0
 */
public class Order  implements Serializable{

	/**
	 * 积分
	 */
	private String integral;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7956766627343369642L;
	
	/**
	 * 联系号码
	 */
	private String contactNumber;
	
	/**
	 * 客户要求送货时间
	 */
	private String requiredDeliveryDate;
	/**
	 * 用户ID
	 */
	private String accountNo;
	
	/**
	 * 	订单渠道 	WCS确定固定值
	 */
	private String orderSource;
	/**
	 * 订单类型
	 *PT: 普通订单
	 *TG: 团购订单
	 *DK: 代客下单
	 */
	private String orderType;
	/**
	 * 配送方式
	 */
	private String  deliveryMode;
	/**
	 * 订单总额 （除去各种优惠促销的原始订单金额）
	 */
	private BigDecimal salesAmount;
	/**
	 * 	订单支付金额 （用户实际需要支付的钱）
	 */
	private BigDecimal paymentAmount;
	
	private BigDecimal cashAmount;
	/**
	 * 	优惠券金额（使用的优惠券的总金额）
	 */
	private BigDecimal couponAmount;
	/**
	 * 是否货到付款
	 */
	private Integer isCod;
	/**
	 * 优惠金额 （各种促销的优惠金额）
	 */
	private BigDecimal promotionAmount;
	/**
	 * 	使用的余额
	 */
	private BigDecimal accountBalanceAmount;
	/**
	 * 	运费
	 */
	private BigDecimal sendCost;
	
	private BigDecimal needSendCost;
	/**
	 * 	收银损益  
	 */
	private BigDecimal cashIncome;
	
	/**
	 * 	客户备注
	 */
	private String customerComments;
	/**
	 * 	是否要发票
	 */
	private  String needInvoice;
	/**
	 * 	发票抬头
	 * 	PERSON：个人
		COMPANY：公司
	 */
	private String invoiceTitle;
	/**
	 * 发票明细
	 */
	private String invoiceDetail;
	/**
	 * 会员id
	 */
	private String memberNo;

	private String paymentClass;
	/**
	 * 订单行项目
	 */
	private List<OrderItem> orderItemList;
	/**
	 * 订单支付方式
	 */
	private List<OrderPayment> paymentItems;
	
	
	/**
	 * 收件人
	 */
	private String receptName;
	/**
	 * 省
	 */
	private String receptProvNo;
	/**
	 * 省名称
	 */
	private String receptProvName;
	/**
	 * 市
	 */
	private String receptCityNo;
	/**
	 * 市名称
	 */
	private String receptCityName;
	/**
	 * 区
	 */
	private String receptDistrictNo;
	/**
	 * 区名称
	 */
	private String receptDistrictName;
	/**
	 * 详细地址
	 */
	private String receptAddress;
	/**
	 * 
	 * @Title: getReceiver
	 * @Description: 邮编
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	private String receptCityCode;
	
	/**
	 * 电话号码
	 */
	private String receptPhone;
	
	private String fromSystem;
	
	/**
	 * 订单唯一标识
	 */
	private String calcBillId;
	

	
}
