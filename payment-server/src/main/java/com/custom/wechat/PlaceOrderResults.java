package com.custom.wechat;

/**
 * created by fuyd on 2019-06-20
 */
public class PlaceOrderResults {

    /**
     * 预支付交易会话ID
     */
    private String prepay_id;

    /**
     * 支付信息签名
     */
    private String sign;

    /**
     * 商户订单号
     */
    private String outTradeNo;

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    @Override
    public String toString() {
        return "PlaceOrderResults{" +
                "prepay_id='" + prepay_id + '\'' +
                ", sign='" + sign + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                '}';
    }
}