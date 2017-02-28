package com.wechat.manage.pojo.wechat.vo;

/**
 * wechatMemberCard
 *
 * @author kongqf
 * @create 2016-12-21
 */
public class WechatMemberCard {

    /**
     * 会员卡编号
     */
    private String membership_number;
    /**
     * 领取会员卡用户获得的code
     */
    private String code;
    private String card_id;
    private String background_pic_url;
    private String activate_begin_time;
    private String activate_end_time;
    private int init_bonus;
    private String init_bonus_record;
    private int init_balance;
    private String init_custom_field_value1;
    private String init_custom_field_value2;
    private String init_custom_field_value3;

    public String getMembership_number() {
        return membership_number;
    }

    public void setMembership_number(String membership_number) {
        this.membership_number = membership_number;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getBackground_pic_url() {
        return background_pic_url;
    }

    public void setBackground_pic_url(String background_pic_url) {
        this.background_pic_url = background_pic_url;
    }

    public String getActivate_begin_time() {
        return activate_begin_time;
    }

    public void setActivate_begin_time(String activate_begin_time) {
        this.activate_begin_time = activate_begin_time;
    }

    public String getActivate_end_time() {
        return activate_end_time;
    }

    public void setActivate_end_time(String activate_end_time) {
        this.activate_end_time = activate_end_time;
    }

    public int getInit_bonus() {
        return init_bonus;
    }

    public void setInit_bonus(int init_bonus) {
        this.init_bonus = init_bonus;
    }

    public String getInit_bonus_record() {
        return init_bonus_record;
    }

    public void setInit_bonus_record(String init_bonus_record) {
        this.init_bonus_record = init_bonus_record;
    }

    public int getInit_balance() {
        return init_balance;
    }

    public void setInit_balance(int init_balance) {
        this.init_balance = init_balance;
    }

    public String getInit_custom_field_value1() {
        return init_custom_field_value1;
    }

    public void setInit_custom_field_value1(String init_custom_field_value1) {
        this.init_custom_field_value1 = init_custom_field_value1;
    }

    public String getInit_custom_field_value2() {
        return init_custom_field_value2;
    }

    public void setInit_custom_field_value2(String init_custom_field_value2) {
        this.init_custom_field_value2 = init_custom_field_value2;
    }

    public String getInit_custom_field_value3() {
        return init_custom_field_value3;
    }

    public void setInit_custom_field_value3(String init_custom_field_value3) {
        this.init_custom_field_value3 = init_custom_field_value3;
    }

    @Override
    public String toString() {
        return "wechatMemberCard{" +
                "membership_number='" + membership_number + '\'' +
                ", code='" + code + '\'' +
                ", card_id='" + card_id + '\'' +
                ", background_pic_url='" + background_pic_url + '\'' +
                ", activate_begin_time='" + activate_begin_time + '\'' +
                ", activate_end_time='" + activate_end_time + '\'' +
                ", init_bonus=" + init_bonus +
                ", init_bonus_record='" + init_bonus_record + '\'' +
                ", init_balance=" + init_balance +
                ", init_custom_field_value1='" + init_custom_field_value1 + '\'' +
                ", init_custom_field_value2='" + init_custom_field_value2 + '\'' +
                ", init_custom_field_value3='" + init_custom_field_value3 + '\'' +
                '}';
    }
}
