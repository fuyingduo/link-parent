package com.custom.wechat.manage;

import com.custom.base.WechatResult;
import com.custom.config.SpringContextUtil;
import com.custom.config.WechatProperties;
import com.custom.constant.WechatPaymentStatusConstans;
import com.custom.constant.WechatRequestURLConstans;
import com.custom.util.HttpUtil;
import com.custom.util.SignUtil;
import com.custom.util.XmlUtil;
import com.custom.wechat.PlaceOrderResults;
import com.custom.wechat.param.OrderQueryParam;
import com.custom.wechat.param.UnifiedOrderParam;
import com.custom.wechat.response.OrderQueryResullts;
import com.custom.wechat.response.UnifiedOrderResullts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import java.util.Optional;

/**
 * 微信支付
 * created by fuyd on 2019-06-19
 */
public class WechatPayManagement extends WebApplicationObjectSupport {

    private static final Logger LOG = LoggerFactory.getLogger(WechatPayManagement.class);

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 商品描述
     */
    private String body;

    /**
     * 总金额 分
     */
    private Integer totalFee;

    /**
     * 配置文件信息
     */
    private WechatProperties properties;

    /**
     * 统一下单 构造器
     *
     * @param body       商品描述
     * @param outTradeNo 商户订单号
     * @param totalFee   订单总额
     */
    WechatPayManagement(String body, String outTradeNo, Integer totalFee) {
        if (StringUtils.isEmpty(body) || StringUtils.isEmpty(outTradeNo) || null == totalFee) {
            String errmsg = String.format("构造参数为空: body:%s, outTradeNo:%s, totalFee:%s", body, outTradeNo, totalFee);
            throw new NullPointerException(errmsg);
        }
        this.properties = (WechatProperties) SpringContextUtil.getBean("wechatProperties");
        this.properties = propertiesValida(properties);
        if (null == properties) {
            throw new NullPointerException("properties 文件参数为空");
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
    WechatPayManagement(String outTradeNo) {
        if (StringUtils.isEmpty(outTradeNo)) {
            String errmsg = String.format("构造参数为空: outTradeNo:%s", outTradeNo);
            throw new NullPointerException(errmsg);
        }
        this.properties = (WechatProperties) SpringContextUtil.getBean("wechatProperties");
        this.properties = propertiesValida(properties);
        if (null == properties) {
            throw new NullPointerException("properties 文件参数为空");
        }
        this.outTradeNo = outTradeNo;
    }

    public WechatResult<Object> placeOrder() {

        // 创建统一下单请求参数-签名
        UnifiedOrderParam unifiedOrderParam = new UnifiedOrderParam(this.body, this.outTradeNo, this.totalFee, this.properties);
        unifiedOrderParam.setSign(unifiedOrderParam);
        LOG.info(unifiedOrderParam.toString());
        Optional<String> opl = XmlUtil.beanToXml(unifiedOrderParam, Boolean.TRUE);
        if (!opl.isPresent()) {
            return WechatResult.err("XML转换失败");
        }

        // 请求微信下单接口
        LOG.info("[微信支付] URL: {}, Body: {}", WechatRequestURLConstans.UNIFIED_RDER_URL, opl.get());
        String body = HttpUtil.sendPost(WechatRequestURLConstans.UNIFIED_RDER_URL, opl.get());
        LOG.info("[微信支付] ResponseContent:{}", body);
        Optional<UnifiedOrderResullts> unifiedOrderResullts = XmlUtil.xmlTobean(body, UnifiedOrderResullts.class);
        if (!unifiedOrderResullts.isPresent()) {
            return WechatResult.err("XML转换失败");
        }
        UnifiedOrderResullts resullts = unifiedOrderResullts.get();
        LOG.info("[微信支付] ResponseContent parse XML JavaBean:{}", resullts.toString());

        // 业务返回状态
        if (WechatPaymentStatusConstans.FAILURE.equals(resullts.getReturn_code())) {
            return WechatResult.err(resullts.getReturn_msg());
        }

        // 交易状态
        if (WechatPaymentStatusConstans.FAILURE.equals(resullts.getResult_code())) {
            return WechatResult.err(resullts.getErr_code_des());
        }

        // 验签
        String resultSign = resullts.getSign();
        resullts.setSign(null);
        Optional<String> generateSign = SignUtil.generate(resullts, properties.getSecretKey());
        if (!resultSign.equals(generateSign.orElse(""))) {
            return WechatResult.err("验签失败");
        }

        // 返回结果-签名
        PlaceOrderResults date = new PlaceOrderResults();
        date.setPrepay_id(resullts.getPrepay_id());
        Optional<String> generate = SignUtil.generate(date, properties.getSecretKey());
        if (!generate.isPresent()) {
            return WechatResult.err("签名失败");
        }
        date.setSign(generate.get());
        date.setOutTradeNo(this.outTradeNo);
        return WechatResult.ok(date);
    }

    public WechatResult<Object> queryOrder() {

        OrderQueryParam orderQueryParam = new OrderQueryParam(this.outTradeNo, this.properties);
        orderQueryParam.setSign(orderQueryParam);
        LOG.info(orderQueryParam.toString());
        Optional<String> opl = XmlUtil.beanToXml(orderQueryParam, true);
        if (!opl.isPresent()) {
            return WechatResult.err("XML转换失败");
        }
        LOG.info("[微信支付] URL: {}, Body: {}", WechatRequestURLConstans.ORDER_QUERY_URL, opl.get());
        String body = HttpUtil.sendPost(WechatRequestURLConstans.ORDER_QUERY_URL, opl.get());
        LOG.info("[微信支付] ResponseContent:{}", body);
        Optional<OrderQueryResullts> orderQueryResullts = XmlUtil.xmlTobean(body, OrderQueryResullts.class);
        if (!orderQueryResullts.isPresent()) {
            return WechatResult.err("XML转换失败");
        }
        OrderQueryResullts resullts = orderQueryResullts.get();
        LOG.info("[微信支付] ResponseContent parse XML JavaBean:{}", resullts.toString());

        // 业务返回状态
        if (WechatPaymentStatusConstans.FAILURE.equals(resullts.getReturn_code())) {
            return WechatResult.err(resullts.getReturn_msg());
        }

        // 交易状态
        if (WechatPaymentStatusConstans.FAILURE.equals(resullts.getResult_code())) {
            return WechatResult.err(resullts.getErr_code_des());
        }

        // 验签
        String resultSign = resullts.getSign();
        resullts.setSign(null);
        Optional<String> generateSign = SignUtil.generate(resullts, properties.getSecretKey());
        if (!resultSign.equals(generateSign.orElse(""))) {
            return WechatResult.err("验签失败");
        }

        // 返回结果-签名
        LOG.info("[微信支付] content: {}", resullts.toString());
        return WechatResult.ok(resullts.getTrade_state());
    }

    /**
     * 统一下单参数校验
     *
     * @param properties xxx.yml
     */
    private WechatProperties propertiesValida(WechatProperties properties) {
        if (null == properties) {
            LOG.error("读取properties文件信息失败");
            return null;
        }
        if (StringUtils.isEmpty(properties.getAppid())) {
            LOG.error("参数:appid 为空");
            return null;
        }
        if (StringUtils.isEmpty(properties.getMchId())) {
            LOG.error("参数:mchId 为空");
            return null;
        }
        if (StringUtils.isEmpty(properties.getSpbillCreateIp())) {
            LOG.error("参数:spbillCreateIp 为空");
            return null;
        }
        if (StringUtils.isEmpty(properties.getSecretKey())) {
            LOG.error("参数:secretKey 为空");
            return null;
        }
        return properties;
    }

}