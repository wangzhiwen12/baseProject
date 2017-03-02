package com.wechat.manage.pojo.wechat.vo;

public class StatisticsAllUserDto {

    private String ref_date;

    private Integer user_source;

    private Integer new_user;

    private Integer cancel_user;

    private Integer jz_user;

    public Integer getJz_user() {
        return jz_user;
    }

    public void setJz_user(Integer jz_user) {
        this.jz_user = jz_user;
    }

    public Integer getCumulate_user() {
        return cumulate_user;
    }

    public void setCumulate_user(Integer cumulate_user) {
        this.cumulate_user = cumulate_user;
    }

    public Integer getNz_usre() {
        return nz_usre;
    }

    public void setNz_usre(Integer nz_usre) {
        this.nz_usre = nz_usre;
    }

    private Integer cumulate_user;

    private Integer nz_usre;

    public String getRef_date() {
        return ref_date;
    }

    public void setRef_date(String ref_date) {
        this.ref_date = ref_date;
    }

    public Integer getUser_source() {
        return user_source;
    }

    public void setUser_source(Integer user_source) {
        this.user_source = user_source;
    }

    public Integer getNew_user() {
        return new_user;
    }

    public void setNew_user(Integer new_user) {
        this.new_user = new_user;
    }

    public Integer getCancel_user() {
        return cancel_user;
    }

    public void setCancel_user(Integer cancel_user) {
        this.cancel_user = cancel_user;
    }

    @Override
    public String toString() {
        return "StatisticsAllUserDto{" +
                "ref_date=" + ref_date +
                ", user_source=" + user_source +
                ", new_user=" + new_user +
                ", cancel_user=" + cancel_user +
                ", jz_user=" + jz_user +
                ", cumulate_user=" + cumulate_user +
                ", nz_usre=" + nz_usre +
                '}';
    }
}
