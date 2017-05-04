/*   
 * @(#)SalesCenterDao.java       2014-11-7 
 *   
 * 王府井集团拥有完全的版权   
 * 使用者必须经过许可   
 */  
package com.wechat.manage.pojo.cart;

import java.io.Serializable;
import java.util.List;

/**     
 *  门店展示模型  
 *     
 * @author  xuc33 
 * @since   JDK1.7
 */
public class Market implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5896682357229478264L;

	//门店ID
	private String market ;
	
	//门店名称
	private String marketDesc ;
	
	//活动
	private List<Promotion> promotions ;
	
	//商品
	private List<Product> products;

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getMarketDesc() {
		return marketDesc;
	}

	public void setMarketDesc(String marketDesc) {
		this.marketDesc = marketDesc;
	}


	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
