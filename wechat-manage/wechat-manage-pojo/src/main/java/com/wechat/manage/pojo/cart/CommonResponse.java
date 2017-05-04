package com.wechat.manage.pojo.cart;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 公共返回
 * @author
 *
 */
public class CommonResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 耗时
	 */
	private String[] elapsed;
	
	/**
	 * 返回码
	 */
	@JSONField(name="returncode")
	private String returnCode;
	
	/**
	 * 失败信息
	 */
	private String errorResult;
	
	/**
	 * 是否成功
	 */
	private boolean success = true;
	

	public String[] getElapsed() {
		return elapsed;
	}

	public void setElapsed(String[] elapsed) {
		this.elapsed = elapsed;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getErrorResult() {
		return errorResult;
	}

	public void setErrorResult(String errorResult) {
		this.errorResult = errorResult;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
