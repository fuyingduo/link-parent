package com.custom.util;


import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;

/**
 * created by fuyd on 2019-06-18
 */
public class RandomUtil {

    private static final int MAX_LENGTH = 32;
    private static final int MIN_LENGTH = 0;
    private static final String THE_LETTER = "yzx";

    /**
     * 微信支付API接口协议中包含字段nonce_str，主要保证签名不可预测
     * @return
     */
    public static String generate() {
        Random random = new Random();
        long val = random.nextLong();
        String res = DigestUtils.md5Hex(val + THE_LETTER).toUpperCase();
        if (MAX_LENGTH < res.length()) return res.substring(MIN_LENGTH, MAX_LENGTH);
        else return res;
    }
}
