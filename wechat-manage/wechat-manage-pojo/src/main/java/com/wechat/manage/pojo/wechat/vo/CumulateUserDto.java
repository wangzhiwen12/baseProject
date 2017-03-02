package com.wechat.manage.pojo.wechat.vo;

public class CumulateUserDto {

    private String ref_date;

    private Integer cumulate_user;

    public String getRef_date() {
        return ref_date;
    }

    public void setRef_date(String ref_date) {
        this.ref_date = ref_date;
    }

    public Integer getCumulate_user() {
        return cumulate_user;
    }

    public void setCumulate_user(Integer cumulate_user) {
        this.cumulate_user = cumulate_user;
    }

    @Override
    public String toString() {
        return "CumulateUserDto{" +
                "ref_date='" + ref_date + '\'' +
                ", cumulate_user='" + cumulate_user + '\'' +
                '}';
    }
}
