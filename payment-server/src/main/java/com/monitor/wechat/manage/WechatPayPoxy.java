package com.monitor.wechat.manage;

import org.springframework.util.StringUtils;

/**
 * 统一下单代理类
 * created by fuyd on 2019-08-15
 */
public class WechatPayPoxy implements IWechatPayPoxy {

    private String body;
    private Integer totalFee;
    private String outTradeNo;

    /**
     * 统一下单
     *
     * @param body
     * @param outTradeNo
     * @param totalFee
     */
    public WechatPayPoxy(String body, String outTradeNo, Integer totalFee) {
        if (StringUtils.isEmpty(body) || StringUtils.isEmpty(outTradeNo) || null == totalFee) {
            String errmsg = String.format("构造参数为空: body:%s, outTradeNo:%s, totalFee:%s", body, outTradeNo, totalFee);
            throw new NullPointerException(errmsg);
        }
        this.body = body;
        this.totalFee = totalFee;
        this.outTradeNo = outTradeNo;
    }

    /**
     * 订单查询/取消订单 构造器
     *
     * @param outTradeNo 商户订单ID
     */
    public WechatPayPoxy(String outTradeNo) {
        if (StringUtils.isEmpty(outTradeNo)) {
            String errmsg = String.format("构造参数为空: outTradeNo:%s", outTradeNo);
            throw new NullPointerException(errmsg);
        }
        this.outTradeNo = outTradeNo;
    }

    @Override
    public WechatPayManagement buildPlaceTheOrder() {
        return new WechatPayManagement(body, outTradeNo, totalFee);
    }

    @Override
    public WechatPayManagement buildOrderQuery() {
        return new WechatPayManagement(outTradeNo);
    }
}
