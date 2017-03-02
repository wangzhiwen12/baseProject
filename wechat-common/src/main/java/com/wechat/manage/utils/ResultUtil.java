package com.wechat.manage.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kongqf
 * @create 2016-12-30
 */
public class ResultUtil {
    /**
     * 返回通用的成功 结果
     *
     * @param object
     * @return
     */
    public static Map<String, Object> creComSucResult(Object object) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("success", "true");
        resultMap.put("data", object);
        return resultMap;
    }

    /**
     * 返回通用的错误类型
     *
     * @param errorCode
     * @param message
     * @return
     */
    public static Map<String, Object> creComErrorResult(String errorCode, String message) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> errMap = new HashMap<String, Object>();
        resultMap.put("success", "false");
        errMap.put("errorCode", errorCode);
        errMap.put("errorMsg", message);
        resultMap.put("data", errMap);
        return resultMap;
    }
}
