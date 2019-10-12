package com.monitor.wechat.param;

import com.monitor.util.RandomUtil;
import com.monitor.util.SignUtil;
import com.monitor.config.WechatProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Optional;

/**
 * 订单查询请求参数
 * created by fuyd on 2019-06-19
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderQueryParam {

    /**
     * 应用APPID
     */
    @NotBlank
    private String appid;

    /**
     * 商户号
     */
    @NotBlank
    private String mch_id;

    /**
     * 微信订单号
     * 微信的订单号，优先使用
     */
    private String transaction_id;

    /**
     * 商户订单号
     */
    private String out_trade_no;

    /**
     * 随机字符串
     */
    @NotBlank
    private String nonce_str;

    /**
     * 签名
     */
    @NotBlank
    private String sign;

    private String secret_key;

    private OrderQueryParam() {
    }

    /**
     * 订单查询
     *
     * @param outTradeNo 商户订单号
     * @param properties 配置文件信息
     */
    public OrderQueryParam(String outTradeNo, @NotNull WechatProperties properties) {
        this.out_trade_no = outTradeNo;
        this.appid = properties.getAppid();
        this.mch_id = properties.getMchId();
        this.out_trade_no = outTradeNo;
        this.nonce_str = RandomUtil.generate();
        this.secret_key = properties.getSecretKey();
    }

    /**
     * 签名
     *
     * @param orderQueryParam this
     */
    public void setSign(OrderQueryParam orderQueryParam) {
        String key = this.secret_key;
        this.secret_key = null;
        Optional<String> generate = SignUtil.generate(orderQueryParam, key);
        if (!generate.isPresent()) {
            throw new RuntimeException("签名失败");
        }
        this.sign = generate.get();
    }

    /**
     * 微信订单号与商户订单号2选1
     *
     * @param transactionId 微信订单号
     */
    public void setTransactionId(String transactionId) {
        if (null != this.out_trade_no) {
            this.out_trade_no = null;
        }
        this.transaction_id = transactionId;
    }

    @Override
    public String toString() {
        return "OrderQueryParam{" +
                "appid='" + appid + '\'' +
                ", mch_id='" + mch_id + '\'' +
                ", transaction_id='" + transaction_id + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                ", nonce_str='" + nonce_str + '\'' +
                ", sign='" + sign + '\'' +
                ", secret_key='" + secret_key + '\'' +
                '}';
    }
}
