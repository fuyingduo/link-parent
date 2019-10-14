package com.custom.wechat.param;

import com.custom.util.RandomUtil;
import com.custom.util.SignUtil;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Optional;

/**
 * created by fuyd on 2019-06-24
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class SandboxParam {

    private String mch_id;
    private String nonce_str;
    private String sign;

    private SandboxParam() {
    }

    public SandboxParam(String mch_id) {
        this.mch_id = mch_id;
        this.nonce_str = RandomUtil.generate();
    }

    public void setSign(SandboxParam sandboxParam, String secretKey) {
        Optional<String> opl = SignUtil.generate(sandboxParam, secretKey);
        opl.ifPresent(s -> this.sign = s);
    }

    @Override
    public String toString() {
        return "SandboxParam{" +
                "mch_id='" + mch_id + '\'' +
                ", nonce_str='" + nonce_str + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
