package com.wechat.manage.pojo.cart;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 促销结果
 * @author yfc
 *
 */
public class PromotionCalcResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *0-已计算出促销结果
     *1-需要先按商品选择促销方式
     *2-需要先支付除外预付款"
	 */
	@JSONField(name="calc_result")
	private String calcResult;
	/**
	 * 已计算出促销结果订单的中台唯一ID
	 */
	@JSONField(name="calc_billid")
	private String calcBillid;
	/**
	 * 订单数据结构
	 */
	@JSONField(name="bill_detail")
	private BillDetail billDetail;
	/**
	 * 整单运费减免模式
	 * 0-减免多少
	 * 1-减免到"	
	 */
	@JSONField(name="freight_pop_mode")
	private String freightPopMode;
	/**
	 * 	整单运费减免金额
	 */
	@JSONField(name="freight_pop_amount")
	private double freightPopAmount;
	/**
	 * 除外付款结构	
	 */
	@JSONField(name="except_pays")
	private List<ExceptPay> exceptPays;
	/**
	 * 赠品数据结构
	 */
	private List<Gift> gifts;
	
	public String getCalcResult() {
		return calcResult;
	}
	public void setCalcResult(String calcResult) {
		this.calcResult = calcResult;
	}
	public String getCalcBillid() {
		return calcBillid;
	}
	public void setCalcBillid(String calcBillid) {
		this.calcBillid = calcBillid;
	}
	public BillDetail getBillDetail() {
		return billDetail;
	}
	public void setBillDetail(BillDetail billDetail) {
		this.billDetail = billDetail;
	}
	public String getFreightPopMode() {
		return freightPopMode;
	}
	public void setFreightPopMode(String freightPopMode) {
		this.freightPopMode = freightPopMode;
	}
	public double getFreightPopAmount() {
		return freightPopAmount;
	}
	public void setFreightPopAmount(double freightPopAmount) {
		this.freightPopAmount = freightPopAmount;
	}

	public List<ExceptPay> getExceptPays() {
		return exceptPays;
	}
	public void setExceptPays(List<ExceptPay> exceptPays) {
		this.exceptPays = exceptPays;
	}
	public List<Gift> getGifts() {
		return gifts;
	}
	public void setGifts(List<Gift> gifts) {
		this.gifts = gifts;
	}
	
	
}
