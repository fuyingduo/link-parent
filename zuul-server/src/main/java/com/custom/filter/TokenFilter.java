package com.custom.filter;

import com.alibaba.fastjson.JSONObject;
import com.custom.constant.LoginConstants;
import com.custom.constant.URIIgnoreConstant;
import com.custom.enums.StatusCodeEnum;
import com.custom.enums.WhetherEnum;
import com.custom.jwt.DefaultTokenBuilder;
import com.custom.util.JwtUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by fuyd on 2019-06-16
 */
public class TokenFilter extends ZuulFilter {

    private static final String REQUEST_USID = "loginId";
    private static final String REQUEST_USER = "loginName";
    private static final String REQUEST_TYPE = "loginType";
    private static final Logger LOG = LoggerFactory.getLogger(TokenFilter.class);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String requestURI = request.getRequestURI();
        return URIIgnoreConstant.hasIgnore(requestURI);
    }

    @Override
    public Object run() throws ZuulException {

        LOG.info("[ZUUL网关] Start ----->>> ");
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String token = request.getHeader(LoginConstants.REQUEST_HEADER_KEY);
        if (null == token || "".equals(token)) {
            reject(context);
            LOG.error("[ZUUL网关] Token验证失败! token:{}", token);
            return null;
        }
        DefaultTokenBuilder tokenInfo = JwtUtil.validateToken(token);
        if (WhetherEnum.NO.code() == tokenInfo.getVisitJurisdiction()) {
            reject(context);
            LOG.error("[ZUUL网关] 用户无登陆权限!");
            return null;
        }
        routing(context, tokenInfo);
        LOG.info("[ZUUL网关] 验证成功 转发URI:{}", request.getRequestURI());
        return null;
    }

    /**
     * 拒绝路由
     *
     * @param context 上下文信息
     */
    private void reject(RequestContext context) {
        context.setSendZuulResponse(false);
        context.setResponseStatusCode(HttpStatus.OK.value());
        JSONObject obj = new JSONObject(true);
        obj.put("code", StatusCodeEnum.NOT_LOGIN.code());
        obj.put("msg", StatusCodeEnum.NOT_LOGIN.msg());
        context.setResponseBody(obj.toJSONString());
        HttpServletResponse response = context.getResponse();
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        context.setSendZuulResponse(false);
        context.set("logic-is-success", false);
    }

    /**
     * 路由
     *
     * @param context   上下文信息
     * @param tokenInfo token
     */
    private void routing(RequestContext context, DefaultTokenBuilder tokenInfo) {
        additionalParameters(context, tokenInfo);
        context.setSendZuulResponse(true);
        context.setResponseStatusCode(HttpStatus.OK.value());
        context.set("logic-is-success", true);
    }

    /**
     * 额外参数
     *
     * @param context   上下文信息
     * @param tokenInfo token
     */
    private void additionalParameters(RequestContext context, DefaultTokenBuilder tokenInfo) {
        Map<String, List<String>> map = context.getRequestQueryParams();
        if (null == map) {
            map = new HashMap<>();
        }
        map.put(REQUEST_USID, Collections.singletonList(String.valueOf(tokenInfo.getId())));
        map.put(REQUEST_USER, Collections.singletonList(String.valueOf(tokenInfo.getUserName())));
        map.put(REQUEST_TYPE, Collections.singletonList(String.valueOf(tokenInfo.getType())));
        context.setRequestQueryParams(map);
    }
}
