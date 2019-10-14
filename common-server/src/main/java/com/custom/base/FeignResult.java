package com.custom.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import org.springframework.util.ObjectUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

/**
 * created by fuyd on 2019-06-14
 */
public class FeignResult extends LinkedHashMap<String, Object> {

    private static final long serialVersionUID = -7127660207970304851L;

    private static final String CODE = "code";
    private static final String DATA = "data";
    private static final int SUCCESS = 0;

    /**
     * Constructor
     */
    private FeignResult() {
    }

    /**
     * success Constructor
     */
    private FeignResult(Object data) {
        this.put(CODE, SUCCESS);
        this.put(DATA, data);
    }

    /**
     * error Constructor
     */
    private FeignResult(int code, Object data) {
        this.put(CODE, code);
        this.put(DATA, data);
    }

    public static FeignResult ok(Object data) {
        return new FeignResult(data);
    }

    public static FeignResult err(int code, Object data) {
        return new FeignResult(code, data);
    }

    public int code() {
        return Integer.valueOf(this.get(CODE).toString());
    }

    public Object data() {
        return this.get(DATA);
    }

    /**
     * 获取结果集
     *
     * @param cls 类型
     * @param <T> 返回类型
     */
    public <T> Optional<T> get(Class<T> cls) {
        return this.alone(this, cls);
    }

    /**
     * 获取数组结果集
     *
     * @param cls 数组类型
     * @param <T> 返回类型
     */
    public <T> List<T> gets(Class<T> cls) {
        return this.more(this, cls);
    }


    private <T> Optional<T> alone(FeignResult result, Class<T> cls) {
        if (null == result) {
            return Optional.empty();
        }
        Object data = result.data();
        if (ObjectUtils.isEmpty(data)) {
            return Optional.empty();
        }
        return Optional.ofNullable(JSON.parseObject(String.valueOf(data), cls));

    }

    private <T> List<T> more(FeignResult result, Class<T> cls) {
        if (null == result) {
            return Lists.newArrayList();
        }
        Object data = result.data();
        if (ObjectUtils.isEmpty(data)) {
            return Lists.newArrayList();
        }
        return JSONArray.parseArray(String.valueOf(data), cls);
    }
}
