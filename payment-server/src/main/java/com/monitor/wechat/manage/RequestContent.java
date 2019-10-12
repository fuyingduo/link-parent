package com.monitor.wechat.manage;

/**
 * 请求内容
 * created by fuyd on 2019-06-20
 */
public class RequestContent {

    /**
     * 请求URL
     */
    private String requestUrl;

    /**
     * 请求内容
     */
    private String body;

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "RequestContent{" +
                "requestUrl='" + requestUrl + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
