/*   
 * @(#)SalesCenterDao.java       2014-11-6 
 *   
 * 王府井集团拥有完全的版权   
 * 使用者必须经过许可   
 */  
package com.wechat.manage.pojo.cart;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**     
 *  订单详情 
 *     
 * @author
 * @since   JDK1.7
 */
public class BillDetail implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6175660877822962165L;

	//订单号
	@JSONField(name="billno")
	private String billNo;
	
	//销售渠道
	private String channel;
	
	//销售门店
	private String market;
	//终端号
	@JSONField(name="term_no")
	private String	termNo;
	
	//销售日期
	@JSONField(name="sale_date")
	private String	saleDate   ;
	
	//终端流水号
	@JSONField(name="term_invoiceno")
	private String	termInvoiceNo;
	
	//会员账号
	@JSONField(name="consumers_id")
	private String	consumerId  ;
	
	/**
	 * 终端收银员	
	 */
	@JSONField(name="term_operator")
	private String  termOperator;
	/**
	 * 对应返券活动id
	 */
	@JSONField(name="gain_event_id ")
	private String  gainEventId ;
	public String getGainEventId() {
		return gainEventId;
	}

	public void setGainEventId(String gainEventId) {
		this.gainEventId = gainEventId;
	}

	/**
	 * 单据类型
	 * 1-销售 2-红冲 4-退货 5-退货红冲	
	 */
	@JSONField(name="invoice_type")
	private String invoiceType;
	/**
	 * 授权卡号
	 * 有授权卡号不计算促销
	 */
	@JSONField(name="grant_cardno")
	private String grantCardno;
	/**
	 * 会员卡号
	 * 终端调用会员认证服务后得到
	 */
	@JSONField(name="consumers_cardno")
	private String consumersCardno;
	/**
	 * 应收运费
	 */
	private Double freight;
	
	/**
	 * 实际运费	
	 */
	@JSONField(name="freight_fact")
	private Double freightFact;
	/**
	 * 应收金额	
	 * 页面中的总计		商品成交金额(sell_details的成交价之和)+实际运费
	 */
	@JSONField(name="ought_pay")
	private Double oughtPay;
	
	//订单用券余额后的支付金额
	@JSONField(name="remain_pay")
	private double remainPay;
	
	//四舍五入损益
	@JSONField(name="sswr_overage")
	private double sswrOverage;
	/**
	 * 实付金额	
	 */
	@JSONField(name="fact_pay")
	private Double factPay;
	
	/**
	 * 找零金额
	 * fact_pay - ought_pay = change_pay
	 */
	@JSONField(name="change_pay")
	private Double changePay;
	/**
	 * 收货省份	
	 */
	@JSONField(name="receive_province")
	private String receiveProvince;
	/**
	 * 收货城市	
	 */
	@JSONField(name="receive_city")
	private String receiveCity;
	/**
	 * 收货地址	
	 */
	@JSONField(name="receive_address")
	private String receiveAddress;
	/**
	 * 收货人员	
	 */
	@JSONField(name="receive_person")
	private String receivePerson;
	/**
	 * 配送方式
	 */
	@JSONField(name="receive_dist")
	private String 	receiveDist;	
	
	//商品明细
	@JSONField(name="sell_details")
	private List<SellDetail> sellDetails ;
	
	//付款明细
	@JSONField(name="sell_payments")
    private List<SellPayment> sellPayments;
	
	//货到付款标志 Y N
	@JSONField(name="codpay")
	private String codpay;
	
	//订单发货状态
	@JSONField(name="order_status")
	private String orderStatus;

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getTermNo() {
		return termNo;
	}

	public void setTermNo(String termNo) {
		this.termNo = termNo;
	}

	public String getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}

	public String getTermInvoiceNo() {
		return termInvoiceNo;
	}

	public void setTermInvoiceNo(String termInvoiceNo) {
		this.termInvoiceNo = termInvoiceNo;
	}

	public String getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}

	public String getTermOperator() {
		return termOperator;
	}

	public void setTermOperator(String termOperator) {
		this.termOperator = termOperator;
	}



	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getGrantCardno() {
		return grantCardno;
	}

	public void setGrantCardno(String grantCardno) {
		this.grantCardno = grantCardno;
	}

	public String getConsumersCardno() {
		return consumersCardno;
	}

	public void setConsumersCardno(String consumersCardno) {
		this.consumersCardno = consumersCardno;
	}

	public Double getFreight() {
		return freight;
	}

	public void setFreight(Double freight) {
		this.freight = freight;
	}

	public Double getFreightFact() {
		return freightFact;
	}

	public void setFreightFact(Double freightFact) {
		this.freightFact = freightFact;
	}

	public Double getOughtPay() {
		return oughtPay;
	}

	public void setOughtPay(Double oughtPay) {
		this.oughtPay = oughtPay;
	}

	public Double getFactPay() {
		return factPay;
	}

	public void setFactPay(Double factPay) {
		this.factPay = factPay;
	}

	public Double getChangePay() {
		return changePay;
	}

	public void setChangePay(Double changePay) {
		this.changePay = changePay;
	}

	public String getReceiveProvince() {
		return receiveProvince;
	}

	public void setReceiveProvince(String receiveProvince) {
		this.receiveProvince = receiveProvince;
	}

	public String getReceiveCity() {
		return receiveCity;
	}

	public void setReceiveCity(String receiveCity) {
		this.receiveCity = receiveCity;
	}

	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public String getReceivePerson() {
		return receivePerson;
	}

	public void setReceivePerson(String receivePerson) {
		this.receivePerson = receivePerson;
	}

	public String getReceiveDist() {
		return receiveDist;
	}

	public void setReceiveDist(String receiveDist) {
		this.receiveDist = receiveDist;
	}

	public List<SellDetail> getSellDetails() {
		return sellDetails;
	}

	public void setSellDetails(List<SellDetail> sellDetails) {
		this.sellDetails = sellDetails;
	}

	public List<SellPayment> getSellPayments() {
		return sellPayments;
	}

	public void setSellPayments(List<SellPayment> sellPayments) {
		this.sellPayments = sellPayments;
	}

	public double getSswrOverage() {
		return sswrOverage;
	}

	public void setSswrOverage(double sswrOverage) {
		this.sswrOverage = sswrOverage;
	}

	public double getRemainPay() {
		return remainPay;
	}

	public void setRemainPay(double remainPay) {
		this.remainPay = remainPay;
	}

	public String getCodpay() {
		return codpay;
	}

	public void setCodpay(String codpay) {
		this.codpay = codpay;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}



	
}
