package com.zp.parking.entity;




public class AIParams {

    //发起方商编
    private String parentMerchantNo;
    //商户编号
    private String merchantNo;
    // 支付金额，单位（元）
    private Double total;
    // 商品名称
    private String name;
    //appId
    private String appId;
    //openid
    private String userId;
    /*分账选项
    DELAY_SETTLE:需要分账
    REAL_TIME:不需要分账
     */
    private String fundProcessType;

    public String getParentMerchantNo() {
        return parentMerchantNo;
    }

    public void setParentMerchantNo(String parentMerchantNo) {
        this.parentMerchantNo = parentMerchantNo;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFundProcessType() {
        return fundProcessType;
    }

    public void setFundProcessType(String fundProcessType) {
        this.fundProcessType = fundProcessType;
    }
}
