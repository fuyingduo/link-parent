package com.monitor.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * created by fuyd on 2019-06-18
 */
public class SignUtil {

    private static final String JOINING_TOGETHER_SYMBOL = "&";

    /**
     * 微信签名
     * ◆ 参数名ASCII码从小到大排序（字典序）；
     * ◆ 如果参数的值为空不参与签名；
     * ◆ 参数名区分大小写；
     * ◆ 验证调用返回或微信主动通知签名时，传送的sign参数不参与签名，将生成的签名与该sign值作校验。
     * ◆ 微信接口可能增加字段，验证签名时必须支持增加的扩展字段
     *
     * @param data      传输数据
     * @param secretKey 密钥key
     * @param <T>       数据类型
     * @return 签名后字符串
     */
    public static <T> Optional<String> generate(T data, String secretKey) {
        if (null == data) {
            return Optional.empty();
        }
        // 将集合M内非空参数值的参数按照参数名ASCII码从小到大排序（字典序),拼接成字符串
        Field[] declaredFields = data.getClass().getDeclaredFields();
        String joiningstrs = Arrays.stream(declaredFields).map(field -> {
            field.setAccessible(true);
            try {
                Object value = field.get(data);
                if (null == value || "".equals(value)) {
                    return null;
                }
                String name = field.getName();
                if ("packages".equals(name)) {
                    name = "package";
                }
                return String.format("%s=%s", name, value);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).filter(Objects::nonNull).sorted().collect(Collectors.joining(JOINING_TOGETHER_SYMBOL));

        System.out.println(joiningstrs);
        //拼接API密钥：
        String sigmTemp = String.format("%s&key=%s", joiningstrs, secretKey);
        System.out.println(sigmTemp);
        // MD5 加密
        String sign = EncryptUtil.md5Encrypt(sigmTemp).toUpperCase();
        return Optional.of(sign);
    }
}
