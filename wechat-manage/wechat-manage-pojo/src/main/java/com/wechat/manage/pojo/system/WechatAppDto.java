package com.wechat.manage.pojo.system;

/**
 * 公众号管理
 *
 * @author kongqf
 * @create 2016-12-02
 */
public class WechatAppDto extends PageBase {
    private Long sid;

    private String appid;

    private String appsecret;

    private String storecode;

    private String storename;

    private Integer delFlag;

    private String developerId;
    private String logoPic;
    private String logoWxPic;
    private String cardId;
    private String cardUrl;

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getStorecode() {
        return storecode;
    }

    public void setStorecode(String storecode) {
        this.storecode = storecode;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(String developerId) {
        this.developerId = developerId;
    }

    public String getLogoPic() {
        return logoPic;
    }

    public void setLogoPic(String logoPic) {
        this.logoPic = logoPic;
    }

    public String getLogoWxPic() {
        return logoWxPic;
    }

    public void setLogoWxPic(String logoWxPic) {
        this.logoWxPic = logoWxPic;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardUrl() {
        return cardUrl;
    }

    public void setCardUrl(String cardUrl) {
        this.cardUrl = cardUrl;
    }

    @Override
    public String toString() {
        return "WechatAppDto{" +
                "sid=" + sid +
                ", appid='" + appid + '\'' +
                ", appsecret='" + appsecret + '\'' +
                ", storecode='" + storecode + '\'' +
                ", storename='" + storename + '\'' +
                ", delFlag=" + delFlag +
                ", developerId='" + developerId + '\'' +
                ", logoPic='" + logoPic + '\'' +
                ", logoWxPic='" + logoWxPic + '\'' +
                ", cardId='" + cardId + '\'' +
                ", cardUrl='" + cardUrl + '\'' +
                '}';
    }
}
