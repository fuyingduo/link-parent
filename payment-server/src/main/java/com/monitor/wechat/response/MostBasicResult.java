package com.monitor.wechat.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * created by fuyd on 2019-06-20
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MostBasicResult {

    /**
     * 返回状态码
     * SUCCESS/FAIL
     */
    private String return_code;

    /**
     * 返回信息
     */
    private String return_msg;

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    @Override
    public String toString() {
        return "MostBasicResult{" +
                "return_code='" + return_code + '\'' +
                ", return_msg='" + return_msg + '\'' +
                '}';
    }
}
