package com.custom.wechat.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * created by fuyd on 2019-06-20
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CallbackResults {

    /**
     * 返回状态
     */
    private String return_code;

    /**
     * 返回内容
     */
    private String return_msg;

    public CallbackResults() {
    }

    public CallbackResults(String return_code, String return_msg) {
        this.return_code = return_code;
        this.return_msg = return_msg;
    }
}
