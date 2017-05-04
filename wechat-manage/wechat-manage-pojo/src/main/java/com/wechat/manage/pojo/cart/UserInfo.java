/*   
 * @(#)SalesCenterDao.java       2015-1-8 
 *   
 * 王府井集团拥有完全的版权   
 * 使用者必须经过许可   
 */  
package com.wechat.manage.pojo.cart;
/**     
 *  用户信息
 *     
 * @author  xuc33 
 * @since   
 */
public class UserInfo {

	private String UID;
	
	private String token;
	
	private boolean isLogin;
	
	private String ckey ;
	
	private String nativeString;
	
	private String nickName;

	public String getUID() {
		return UID;
	}

	public void setUID(String uID) {
		UID = uID;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	public String getCkey() {
		return ckey;
	}

	public void setCkey(String ckey) {
		this.ckey = ckey;
	}

	public String getNativeString() {
		return nativeString;
	}

	public void setNativeString(String nativeString) {
		this.nativeString = nativeString;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	
}
