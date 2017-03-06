package com.wechat.manage.service.util;

import java.util.UUID;

/**
 * 生成uuid工具类
 * @author ghost
 *
 */
public class UUIDUtils {
	
	public static String generateUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
