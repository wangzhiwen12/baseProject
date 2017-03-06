package com.wechat.manage.pojo.wechat.vo;

/**
 * @author kongqf
 * @create 2017-01-03
 */
public class WxHomeDto {

    private String sid;
    private String storeCode;

    private String storeName;

    private String WxHeadHtml;

    private String WxHomeHtml;

    private String WxHomeHtmlM;

    private String LogoFullUrl;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getWxHeadHtml() {
        return WxHeadHtml;
    }

    public void setWxHeadHtml(String wxHeadHtml) {
        WxHeadHtml = wxHeadHtml;
    }

    public String getWxHomeHtml() {
        return WxHomeHtml;
    }

    public void setWxHomeHtml(String wxHomeHtml) {
        WxHomeHtml = wxHomeHtml;
    }

    public String getWxHomeHtmlM() {
        return WxHomeHtmlM;
    }

    public void setWxHomeHtmlM(String wxHomeHtmlM) {
        WxHomeHtmlM = wxHomeHtmlM;
    }

    public String getLogoFullUrl() {
        return LogoFullUrl;
    }

    public void setLogoFullUrl(String logoFullUrl) {
        LogoFullUrl = logoFullUrl;
    }
}
