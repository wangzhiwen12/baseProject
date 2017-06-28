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


	public String getIntegral() {
		return integral;
	}

	public void setIntegral(String integral) {
		this.integral = integral;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getRequiredDeliveryDate() {
		return requiredDeliveryDate;
	}

	public void setRequiredDeliveryDate(String requiredDeliveryDate) {
		this.requiredDeliveryDate = requiredDeliveryDate;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getDeliveryMode() {
		return deliveryMode;
	}

	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	public BigDecimal getSalesAmount() {
		return salesAmount;
	}

	public void setSalesAmount(BigDecimal salesAmount) {
		this.salesAmount = salesAmount;
	}

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public BigDecimal getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}

	public BigDecimal getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(BigDecimal couponAmount) {
		this.couponAmount = couponAmount;
	}

	public Integer getIsCod() {
		return isCod;
	}

	public void setIsCod(Integer isCod) {
		this.isCod = isCod;
	}

	public BigDecimal getPromotionAmount() {
		return promotionAmount;
	}

	public void setPromotionAmount(BigDecimal promotionAmount) {
		this.promotionAmount = promotionAmount;
	}

	public BigDecimal getAccountBalanceAmount() {
		return accountBalanceAmount;
	}

	public void setAccountBalanceAmount(BigDecimal accountBalanceAmount) {
		this.accountBalanceAmount = accountBalanceAmount;
	}

	public BigDecimal getSendCost() {
		return sendCost;
	}

	public void setSendCost(BigDecimal sendCost) {
		this.sendCost = sendCost;
	}

	public BigDecimal getNeedSendCost() {
		return needSendCost;
	}

	public void setNeedSendCost(BigDecimal needSendCost) {
		this.needSendCost = needSendCost;
	}

	public BigDecimal getCashIncome() {
		return cashIncome;
	}

	public void setCashIncome(BigDecimal cashIncome) {
		this.cashIncome = cashIncome;
	}

	public String getCustomerComments() {
		return customerComments;
	}

	public void setCustomerComments(String customerComments) {
		this.customerComments = customerComments;
	}

	public String getNeedInvoice() {
		return needInvoice;
	}

	public void setNeedInvoice(String needInvoice) {
		this.needInvoice = needInvoice;
	}

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	public String getInvoiceDetail() {
		return invoiceDetail;
	}

	public void setInvoiceDetail(String invoiceDetail) {
		this.invoiceDetail = invoiceDetail;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getPaymentClass() {
		return paymentClass;
	}

	public void setPaymentClass(String paymentClass) {
		this.paymentClass = paymentClass;
	}

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

	public List<OrderPayment> getPaymentItems() {
		return paymentItems;
	}

	public void setPaymentItems(List<OrderPayment> paymentItems) {
		this.paymentItems = paymentItems;
	}

	public String getReceptName() {
		return receptName;
	}

	public void setReceptName(String receptName) {
		this.receptName = receptName;
	}

	public String getReceptProvNo() {
		return receptProvNo;
	}

	public void setReceptProvNo(String receptProvNo) {
		this.receptProvNo = receptProvNo;
	}

	public String getReceptProvName() {
		return receptProvName;
	}

	public void setReceptProvName(String receptProvName) {
		this.receptProvName = receptProvName;
	}

	public String getReceptCityNo() {
		return receptCityNo;
	}

	public void setReceptCityNo(String receptCityNo) {
		this.receptCityNo = receptCityNo;
	}

	public String getReceptCityName() {
		return receptCityName;
	}

	public void setReceptCityName(String receptCityName) {
		this.receptCityName = receptCityName;
	}

	public String getReceptDistrictNo() {
		return receptDistrictNo;
	}

	public void setReceptDistrictNo(String receptDistrictNo) {
		this.receptDistrictNo = receptDistrictNo;
	}

	public String getReceptDistrictName() {
		return receptDistrictName;
	}

	public void setReceptDistrictName(String receptDistrictName) {
		this.receptDistrictName = receptDistrictName;
	}

	public String getReceptAddress() {
		return receptAddress;
	}

	public void setReceptAddress(String receptAddress) {
		this.receptAddress = receptAddress;
	}

	public String getReceptCityCode() {
		return receptCityCode;
	}

	public void setReceptCityCode(String receptCityCode) {
		this.receptCityCode = receptCityCode;
	}

	public String getReceptPhone() {
		return receptPhone;
	}

	public void setReceptPhone(String receptPhone) {
		this.receptPhone = receptPhone;
	}

	public String getFromSystem() {
		return fromSystem;
	}

	public void setFromSystem(String fromSystem) {
		this.fromSystem = fromSystem;
	}

	public String getCalcBillId() {
		return calcBillId;
	}

	public void setCalcBillId(String calcBillId) {
		this.calcBillId = calcBillId;
	}
}
