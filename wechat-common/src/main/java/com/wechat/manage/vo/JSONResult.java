package com.wechat.manage.vo;

/**
 * JSONResult
 *
 * @author kongqf
 * @create 2016-12-24
 */
public class JSONResult {

    private String message;
    private String result;
    private String success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}