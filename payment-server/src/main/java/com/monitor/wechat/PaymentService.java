package com.monitor.wechat;

import com.monitor.base.WechatResult;
import com.monitor.wechat.manage.IWechatPayPoxy;
import com.monitor.wechat.manage.WechatPayManagement;
import org.springframework.stereotype.Component;

/**
 * created by fuyd on 2019-06-19
 */
@Component
public class PaymentService {

    /**
     * 统一下单
     * code 0 成功后返回{@link PlaceOrderResults}
     */
    public WechatResult paymentOrder(IWechatPayPoxy iWechatPayPoxy) {
        WechatPayManagement management = iWechatPayPoxy.buildPlaceTheOrder();
        return management.placeOrder();
    }

    /**
     * 查询订单
     * code 0 成功后返回 trade_state
     * SUCCESS—支付成功
     * REFUND—转入退款
     * NOTPAY—未支付
     * CLOSED—已关闭
     * PAYERROR--支付失败(其他原因，如银行返回失败)
     */
    public WechatResult orderQuery(IWechatPayPoxy iWechatPayPoxy) {
        WechatPayManagement management = iWechatPayPoxy.buildOrderQuery();
        return management.queryOrder();
    }
}