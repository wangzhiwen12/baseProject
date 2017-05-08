package com.wechat.manage.pojo.order;

import java.math.BigDecimal;

/**
 * Created by XS on 2017/5/4.
 */
public class ProOrder {
    private  String accountNo;
    private  BigDecimal cashAmount;
    private  String memberNo;
    private  String supplyProductNo;
    private  String receptAddress;
    private  Integer saleSum;
    private  String receptCityName;
    private  String receptCityNo;
    private  String receptDistrictName;
    private  String receptDistrictNo;
    private  String receptName;
    private  String receptPhone;
    private  String receptProvNo;
    private String requiredDeliveryDate;
    private BigDecimal salesAmount;
    private     BigDecimal    paymentAmount;
    private String receptProvName;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }



    public String getReceptProvName() {
        return receptProvName;
    }

    public void setReceptProvName(String receptProvName) {
        this.receptProvName = receptProvName;
    }

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public String getSupplyProductNo() {
        return supplyProductNo;
    }

    public void setSupplyProductNo(String supplyProductNo) {
        this.supplyProductNo = supplyProductNo;
    }

    public String getReceptAddress() {
        return receptAddress;
    }

    public void setReceptAddress(String receptAddress) {
        this.receptAddress = receptAddress;
    }

    public Integer getSaleSum() {
        return saleSum;
    }

    public void setSaleSum(Integer saleSum) {
        this.saleSum = saleSum;
    }

    public String getReceptCityName() {
        return receptCityName;
    }

    public void setReceptCityName(String receptCityName) {
        this.receptCityName = receptCityName;
    }

    public String getReceptCityNo() {
        return receptCityNo;
    }

    public void setReceptCityNo(String receptCityNo) {
        this.receptCityNo = receptCityNo;
    }

    public String getReceptDistrictName() {
        return receptDistrictName;
    }

    public void setReceptDistrictName(String receptDistrictName) {
        this.receptDistrictName = receptDistrictName;
    }

    public String getReceptDistrictNo() {
        return receptDistrictNo;
    }

    public void setReceptDistrictNo(String receptDistrictNo) {
        this.receptDistrictNo = receptDistrictNo;
    }

    public String getReceptName() {
        return receptName;
    }

    public void setReceptName(String receptName) {
        this.receptName = receptName;
    }

    public String getReceptPhone() {
        return receptPhone;
    }

    public void setReceptPhone(String receptPhone) {
        this.receptPhone = receptPhone;
    }

    public String getReceptProvNo() {
        return receptProvNo;
    }

    public void setReceptProvNo(String receptProvNo) {
        this.receptProvNo = receptProvNo;
    }

    public String getRequiredDeliveryDate() {
        return requiredDeliveryDate;
    }


    public BigDecimal getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(BigDecimal salesAmount) {
        this.salesAmount = salesAmount;
    }


    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
