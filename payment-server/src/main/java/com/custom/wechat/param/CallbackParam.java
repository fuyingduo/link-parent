package com.custom.wechat.param;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 支付结果回调
 * created by fuyd on 2019-06-19
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CallbackParam {

    /**
     * 返回状态码
     */
    private String return_code;

    /**
     * 返回信息
     */
    private String return_msg;

    /**
     * 应用ID
     */
    private String appid;

    /**
     * 商户号
     */
    private String mch_id;

    /**
     * 设备号
     */
    private String device_info;

    /**
     * 随机字符串
     */
    private String nonce_str;

    /**
     * 签名
     */
    private String sign;

    /**
     * 业务结果
     */
    private String result_code;

    /**
     * 错误代码
     */
    private String err_code;

    /**
     * 错误代码描述
     */
    private String err_code_des;

    /**
     * 用户标识
     */
    private String openid;

    /**
     * 是否关注公众账号
     */
    private String is_subscribe;

    /**
     * 交易类型
     */
    private String trade_type;

    /**
     * 付款银行
     */
    private String bank_type;

    /**
     * 总金额
     */
    private Integer total_fee;

    /**
     * 货币种类
     */
    private String fee_type;

    /**
     * 现金支付金额
     */
    private Integer cash_fee;

    /**
     * 现金支付货币类型
     */
    private String cash_fee_type;

    /**
     * 代金券金额
     */
    private Integer coupon_fee;

    /**
     * 代金券使用数量
     */
    private Integer coupon_count;

    /**
     * 代金券ID
     */
    private String coupon_id_$n;

    /**
     * 单个代金券支付金额
     */
    private Integer coupon_fee_$n;

    /**
     * 微信支付订单号
     */
    private String transaction_id;

    /**
     * 商户订单号
     */
    private String out_trade_no;

    /**
     * 商家数据包
     */
    private String attach;

    /**
     * 支付完成时间
     */
    private String time_end;

    public String getReturn_code() {
        return return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public String getSign() {
        return sign;
    }

    public String getResult_code() {
        return result_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public String getOpenid() {
        return openid;
    }

    public String getIs_subscribe() {
        return is_subscribe;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public String getBank_type() {
        return bank_type;
    }

    public Integer getTotal_fee() {
        return total_fee;
    }

    public String getFee_type() {
        return fee_type;
    }

    public Integer getCash_fee() {
        return cash_fee;
    }

    public String getCash_fee_type() {
        return cash_fee_type;
    }

    public Integer getCoupon_fee() {
        return coupon_fee;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public String getAttach() {
        return attach;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "CallbackParam{" +
                "return_code='" + return_code + '\'' +
                ", return_msg='" + return_msg + '\'' +
                ", appid='" + appid + '\'' +
                ", mch_id='" + mch_id + '\'' +
                ", device_info='" + device_info + '\'' +
                ", nonce_str='" + nonce_str + '\'' +
                ", sign='" + sign + '\'' +
                ", result_code='" + result_code + '\'' +
                ", err_code='" + err_code + '\'' +
                ", err_code_des='" + err_code_des + '\'' +
                ", openid='" + openid + '\'' +
                ", is_subscribe='" + is_subscribe + '\'' +
                ", trade_type='" + trade_type + '\'' +
                ", bank_type='" + bank_type + '\'' +
                ", total_fee=" + total_fee +
                ", fee_type='" + fee_type + '\'' +
                ", cash_fee=" + cash_fee +
                ", cash_fee_type='" + cash_fee_type + '\'' +
                ", coupon_fee=" + coupon_fee +
                ", coupon_count=" + coupon_count +
                ", coupon_id_$n='" + coupon_id_$n + '\'' +
                ", coupon_fee_$n=" + coupon_fee_$n +
                ", transaction_id='" + transaction_id + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                ", attach='" + attach + '\'' +
                ", time_end='" + time_end + '\'' +
                '}';
    }
}
