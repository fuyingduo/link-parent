package com.monitor.util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * JSON 工具
 * created by fuyd on 2019-07-19
 */
public class JsonUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    /**
     * JSON 解析
     *
     * @param body 待解析对象
     * @param cls  待解析类型
     * @param <T>  实体类型
     */
    public static <T> Optional<T> parse(byte[] body, Class<T> cls) {
        return parse(new String(body), cls);
    }

    /**
     * JSON 解析
     *
     * @param str 待解析对象
     * @param cls 待解析类型
     * @param <T> 实体类型
     */
    public static <T> Optional<T> parse(String str, Class<T> cls) {
        try {
            return Optional.ofNullable(JSON.parseObject(str, cls));
        } catch (Exception e) {
            LOGGER.error("[JSON工具] 解析失败 e:{}", e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * 实体转换JSON字符串
     *
     * @param t   实体
     * @param <T> 实体类型
     */
    public static <T> Optional<String> toString(T t) {
        try {
            return Optional.of(JSON.toJSONString(t));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
