package com.wechat.manage.pojo.wechat.vo;

/**
 * Created by dell on 2017/8/7.
 */
public class MemberInfoVo {
    private String cardType;
    private String memberCode;
    private String mobile;
    private String storeCode;
    private String openId;
    private String appId;
    private String qrcode;
    private String cardNo;
    private Integer custType;

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Integer getCustType() {
        return custType;
    }

    public void setCustType(Integer custType) {
        this.custType = custType;
    }

    @Override
    public String toString() {
        return "memberInfoVo{" +
                "cardType='" + cardType + '\'' +
                ", memberCode='" + memberCode + '\'' +
                ", mobile='" + mobile + '\'' +
                ", storeCode='" + storeCode + '\'' +
                ", openId='" + openId + '\'' +
                ", appId='" + appId + '\'' +
                ", qrcode='" + qrcode + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", custType='" + custType + '\'' +
                '}';
    }
}
