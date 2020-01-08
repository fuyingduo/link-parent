package com.custom.wechat.param;

import com.custom.util.RandomUtil;
import com.custom.util.SignUtil;
import com.custom.config.WechatProperties;
import com.custom.constant.WechatRequestURLConstans;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Optional;

/**
 * 统一下单 请求参数
 * created by fuyd on 2019-06-18
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class UnifiedOrderParam {

    /**
     * 应用ID
     */
    @NotBlank
    private String appid;

    /**
     * 商户号
     */
    @NotBlank
    private String mch_id;

    /**
     * 设备号
     * 终端设备号(门店号或收银设备ID)，默认请传"WEB"
     */
    private String device_info;

    /**
     * 随机字符串
     * 随机字符串，不长于32位。推荐随机数生成算法
     */
    @NotBlank
    private String nonce_str;

    /**
     * 签名
     * 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
     */
    @NotBlank
//    @XmlTransient
    private String sign;

    /**
     * 签名类型
     */
    private String sign_type;

    /**
     * 商品描述
     */
    @NotBlank
    private String body;

    /**
     * 商品详情
     */
//    @XmlElementWrapper(name="Details")
//    @XmlElement(name = "Detail")
    private String detail;

    /**
     * 附加数据
     */
    private String attach;

    /**
     * 商户订单号
     * 商户系统内部订单号，要求32个字符内
     */
    @NotBlank
    private String out_trade_no;

    /**
     * 货币类型
     * 符合ISO 4217标准的三位字母代码，默认人民币：CNY
     */
    private String fee_type;

    /**
     * 总金额
     * 订单总金额，单位为分
     */
    @NotNull
    private Integer total_fee;

    /**
     * 终端IP
     */
    @NotBlank
    private String spbill_create_ip;

    /**
     * 交易起始时间
     * 订单生成时间，格式为yyyyMMddHHmmss
     */
    private String time_start;

    /**
     * 交易结束时间
     * 订单失效时间，格式为yyyyMMddHHmmss
     */
    private String time_expire;

    /**
     * 订单优惠标记
     */
    private String goods_tag;

    /**
     * 通知地址
     * 接收微信支付异步通知回调地址，
     * 通知url必须为直接可访问的url，不能携带参数。
     */
    @NotBlank
    private String notify_url;

    /**
     * 支付类型
     */
    @NotBlank
    private String trade_type;

    /**
     * 支付方式
     * no_credit--指定不能使用信用卡支付
     */
    private String limit_pay;

    /**
     * 开发票入口开放标识
     * Y，传入Y时，支付成功消息和支付详情页将出现开票入口。
     * 需要在微信支付商户平台或微信公众平台开通电子发票功能，传此字段才可生效
     */
    private String receipt;

    /**
     * 密钥key
     */
    private String secret_key;

    /**
     * 无参构造器 javabean转xml
     */
    public UnifiedOrderParam() {
    }

    /**
     * 必填数据
     *
     * @param body
     * @param outTradeNo
     * @param totalFee
     * @param properties
     */
    public UnifiedOrderParam(@NotBlank String body,
                             @NotBlank String outTradeNo, @NotNull Integer totalFee, @NotNull WechatProperties properties) {
        this.appid = properties.getAppid();
        this.mch_id = properties.getMchId();
        this.nonce_str = RandomUtil.generate();
        this.body = body;
        this.out_trade_no = outTradeNo;
        this.total_fee = totalFee;
        this.spbill_create_ip = properties.getSpbillCreateIp();
        this.notify_url = WechatRequestURLConstans.NOTICE_OF_PAYMENT_RESULT;
        this.trade_type = "APP";
        this.limit_pay = properties.getLimitPay();
        this.receipt = properties.getReceipt();
        this.secret_key = properties.getSecretKey();
    }

    public void setDeviceInfo(String deviceInfo) {
        this.device_info = deviceInfo;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public void setTimeStart(String timeStart) {
        this.time_start = timeStart;
    }


    public void setTimeExpire(String timeExpire) {
        this.time_expire = timeExpire;
    }

    /**
     * 商品详情
     *
     * @param detail
     */
    public void setDetail(String detail) {
    }

    /**
     * 货币类型 默认：人民币
     *
     * @param feeType
     */
    public void setFeeType(String feeType) {
    }

    /**
     * 订单优惠标记
     *
     * @param goodsTag
     */
    public void setGoodsTag(String goodsTag) {
    }

    /**
     * 支付类型 默认：APP
     *
     * @param tradeType
     */
    public void setTradeType(String tradeType) {
    }

    /**
     * 签名
     */
    public void setSign(UnifiedOrderParam unifiedOrderParam) {
        String key = secret_key;
        this.secret_key = null;
        Optional<String> generate = SignUtil.generate(unifiedOrderParam, key);
        if (!generate.isPresent()) {
            throw new RuntimeException("签名失败");
        }
        this.sign = generate.get();
    }

    @Override
    public String toString() {
        return "UnifiedOrderParam{" +
                "appid='" + appid + '\'' +
                ", mch_id='" + mch_id + '\'' +
                ", device_info='" + device_info + '\'' +
                ", nonce_str='" + nonce_str + '\'' +
                ", sign='" + sign + '\'' +
                ", sign_type='" + sign_type + '\'' +
                ", body='" + body + '\'' +
                ", detail='" + detail + '\'' +
                ", attach='" + attach + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                ", fee_type='" + fee_type + '\'' +
                ", total_fee=" + total_fee +
                ", spbill_create_ip='" + spbill_create_ip + '\'' +
                ", time_start='" + time_start + '\'' +
                ", time_expire='" + time_expire + '\'' +
                ", goods_tag='" + goods_tag + '\'' +
                ", notify_url='" + notify_url + '\'' +
                ", trade_type='" + trade_type + '\'' +
                ", limit_pay='" + limit_pay + '\'' +
                ", receipt='" + receipt + '\'' +
                ", secret_key='" + secret_key + '\'' +
                '}';
    }
}
