package com.wechat.manage.pojo.cart;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by XS on 2017/4/28.
 */
public class CalcResult {
    /**
     *
     */
    private static final long serialVersionUID = 4144449595934777585L;

    //promotion.calc首次计算，商品查询促销策略以后产生
    @JSONField(name="calc_billid")
    private String calcBillId;

    //用户id
    private String cid;

    //商品成交金额(sell_details的成交价之和)+实际运费+四舍五入损益
    @JSONField(name="ought_pay")
    private double oughtPay ;

    //订单用券余额后的支付金额
    @JSONField(name="remain_pay")
    private double remainPay;

    //使用券支付的金额
    private double ticketMoney;

    //使用余额支付的金额
    private double balanceMoney;

    //使用积分支付的金额
    private double pointMoney;

    //推荐支付节省的金额
    private double recommendedMoney;

    //原始商品金额
    private double productsAmount;

    //购物车商品数量
    @JSONField(name="prodNum")
    private double prodNum ;

    //购物车共节省金额
    @JSONField(name="total_discount")
    private double totalDiscount ;

    //整单运费减免模式
    @JSONField(name="freight_pop_mode")
    private String freightPopMode ;

    //整单运费减免金额
    @JSONField(name="freight_pop_amount")
    private double freightPopAmount ;

    //全场活动
    private List<Promotion> courtPromotions ;

    //门店展示信息
    private List<Market>  markets ;

    //推荐支付
    private List<RecommendedPayment> recommendedPayments;

    //应收运费
    private double freight;

    //实收运费
    private double freightFact;

    //营销原始返回结果
    PromotionCalcResponse promotionCalcResponse;

    //订单成功可返回的积分和券
    private CoupongainCalc data;

    public CoupongainCalc getData() {
        return data;
    }

    public void setData(CoupongainCalc data) {
        this.data = data;
    }

    public double getOughtPay() {
        return oughtPay;
    }

    public void setOughtPay(double oughtPay) {
        this.oughtPay = oughtPay;
    }

    public double getProdNum() {
        return prodNum;
    }

    public void setProdNum(double prodNum) {
        this.prodNum = prodNum;
    }


    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public String getFreightPopMode() {
        return freightPopMode;
    }

    public void setFreightPopMode(String freightPopMode) {
        this.freightPopMode = freightPopMode;
    }

    public double getFreightPopAmount() {
        return freightPopAmount;
    }

    public void setFreightPopAmount(double freightPopAmount) {
        this.freightPopAmount = freightPopAmount;
    }


    public List<Promotion> getCourtPromotions() {
        return courtPromotions;
    }

    public void setCourtPromotions(List<Promotion> courtPromotions) {
        this.courtPromotions = courtPromotions;
    }

    public List<Market> getMarkets() {
        return markets;
    }

    public void setMarkets(List<Market> markets) {
        this.markets = markets;
    }


    public String getCalcBillId() {
        return calcBillId;
    }

    public void setCalcBillId(String calcBillId) {
        this.calcBillId = calcBillId;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public double getTicketMoney() {
        return ticketMoney;
    }

    public void setTicketMoney(double ticketMoney) {
        this.ticketMoney = ticketMoney;
    }

    public double getBalanceMoney() {
        return balanceMoney;
    }

    public void setBalanceMoney(double balanceMoney) {
        this.balanceMoney = balanceMoney;
    }

    public double getPointMoney() {
        return pointMoney;
    }

    public void setPointMoney(double pointMoney) {
        this.pointMoney = pointMoney;
    }

    public List<RecommendedPayment> getRecommendedPayments() {
        return recommendedPayments;
    }

    public void setRecommendedPayments(List<RecommendedPayment> recommendedPayments) {
        this.recommendedPayments = recommendedPayments;
    }

    public double getFreight() {
        return freight;
    }

    public void setFreight(double freight) {
        this.freight = freight;
    }

    public double getFreightFact() {
        return freightFact;
    }

    public void setFreightFact(double freightFact) {
        this.freightFact = freightFact;
    }

    public PromotionCalcResponse getPromotionCalcResponse() {
        return promotionCalcResponse;
    }

    public void setPromotionCalcResponse(PromotionCalcResponse promotionCalcResponse) {
        this.promotionCalcResponse = promotionCalcResponse;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public double getRecommendedMoney() {
        return recommendedMoney;
    }

    public void setRecommendedMoney(double recommendedMoney) {
        this.recommendedMoney = recommendedMoney;
    }

    public double getProductsAmount() {
        return productsAmount;
    }

    public void setProductsAmount(double productsAmount) {
        this.productsAmount = productsAmount;
    }

    public double getRemainPay() {
        return remainPay;
    }

    public void setRemainPay(double remainPay) {
        this.remainPay = remainPay;
    }
}
