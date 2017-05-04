package com.wechat.manage.pojo.cart;

/**
 * 
 * @author yfc
 *
 */
public class PromotionCalcResponse extends CommonResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private PromotionCalcResult data;


	public PromotionCalcResult getData() {
		return data;
	}


	public void setData(PromotionCalcResult data) {
		this.data = data;
	}

}
