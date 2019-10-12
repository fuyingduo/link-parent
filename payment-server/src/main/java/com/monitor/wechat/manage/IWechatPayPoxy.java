package com.monitor.wechat.manage;

/**
 * created by fuyd on 2019-06-20
 */
public interface IWechatPayPoxy {

    /**
     * 统一下单
     */
    WechatPayManagement buildPlaceTheOrder();

    /**
     * 订单查询
     */
    WechatPayManagement buildOrderQuery();
}
