package com.wechat.manage.pojo.wechat.vo;

/**
 * Created by XS on 2016/12/21.
 */
public class CardExt {
    private String nonce_str;
    private String timestamp;
    private String signature;

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
