package com.wechat.manage.pojo.wechat.vo;

import java.util.Arrays;

public class MemberInfo {
    private Integer subscribe;
    private String openid;
    private String nickname;
    private Integer sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private String subscribe_time;
    private String remark;
    private Integer groupid;
    private String[] tagid_list;
    private String errcode;
    private String errmsg;
    private String unionid;

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public Integer getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getSubscribe_time() {
        return subscribe_time;
    }

    public void setSubscribe_time(String subscribe_time) {
        this.subscribe_time = subscribe_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public String[] getTagid_list() {
        return tagid_list;
    }

    public void setTagid_list(String[] tagid_list) {
        this.tagid_list = tagid_list;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    @Override
    public String toString() {
        return "MemberInfo [subscribe=" + subscribe + ", openid=" + openid + ", nickname="
                + nickname + ", sex=" + sex + ", language=" + language + ", city=" + city
                + ", province=" + province + ", country=" + country + ", headimgurl=" + headimgurl
                + ", subscribe_time=" + subscribe_time + ", remark=" + remark + ", groupid="
                + groupid + ", tagid_list=" + Arrays.toString(tagid_list) + ", errcode=" + errcode
                + ", errmsg=" + errmsg + ", unionid=" + unionid + "]";
    }

}
