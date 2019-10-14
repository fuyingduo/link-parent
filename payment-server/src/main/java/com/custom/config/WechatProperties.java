package com.custom.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * created by fuyd on 2019-06-19
 */
@Configuration
@ConfigurationProperties(prefix = "wechat")
public class WechatProperties {

    /**
     * appId
     */
    private String appid;

    /**
     * 商户id
     */
    private String mchId;

    /**
     * 回调地址
     */
    private String notify_url;

    /**
     * 终端IP
     */
    private String spbillCreateIp;

    /**
     * 密钥key
     */
    private String secretKey;

    /**
     * 支付方式
     * 默认 no_credit--指定不能使用信用卡支付
     */
    private String limitPay;

    /**
     * 开票
     * 默认 Y 开票
     */
    private String receipt;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getLimitPay() {
        return limitPay;
    }

    public void setLimitPay(String limitPay) {
        this.limitPay = limitPay;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public String toString() {
        return "WechatProperties{" +
                "appid='" + appid + '\'' +
                ", mchId='" + mchId + '\'' +
                ", notify_url='" + notify_url + '\'' +
                ", limitPay='" + limitPay + '\'' +
                ", receipt='" + receipt + '\'' +
                ", spbillCreateIp='" + spbillCreateIp + '\'' +
                ", secretKey='" + secretKey + '\'' +
                '}';
    }
}
