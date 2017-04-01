package com.wechat.manage.pojo.product.vo;

/**
 * Created by kongqf on 2017/3/30.
 */
public class ProductInfoDto {
    /**
     * 商品名称
     */
    public String spuName;
    /**
     * 商品编码
     */
    public String spuCode;

    /**
     * 商品名称
     */
    public String skuName;
    /**
     * 商品编码
     */
    public String skuCode;

    /**
     * 商品类型
     */
    public String productType;

    /**
     * 商品简介
     */
    public String spuShortDesc;

    /**
     * 门店编码
     */
    public String storeName;

    /**
     * 门店编码
     */
    public String storeCode;

    /**
     * 颜色码
     */
    public String colorCode;

    /**
     * 款号
     */
    public String modelCode;

    /**
     * 上架状态 0 未上架  1 已上架  2 已下架
     */
    public String isSelling;

    /**
     * 条形码
     */
    public String barCode;

    /**
     * 吊牌价
     */
    public String originalPrice;

    /**
     * 售价
     */
    public String salePrice;

    /**
     * sku图片地址
     */
    public String skuPicUrl;

    /**
     * sku售价
     */
    public String promotionPrice;

    /**
     * 库存
     */
    public String stockNum;

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    public String getSpuCode() {
        return spuCode;
    }

    public void setSpuCode(String spuCode) {
        this.spuCode = spuCode;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getSpuShortDesc() {
        return spuShortDesc;
    }

    public void setSpuShortDesc(String spuShortDesc) {
        this.spuShortDesc = spuShortDesc;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getIsSelling() {
        return isSelling;
    }

    public void setIsSelling(String isSelling) {
        this.isSelling = isSelling;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getSkuPicUrl() {
        return skuPicUrl;
    }

    public void setSkuPicUrl(String skuPicUrl) {
        this.skuPicUrl = skuPicUrl;
    }

    public String getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(String promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public String getStockNum() {
        return stockNum;
    }

    public void setStockNum(String stockNum) {
        this.stockNum = stockNum;
    }
}
