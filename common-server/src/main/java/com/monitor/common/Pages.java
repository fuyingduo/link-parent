package com.monitor.common;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


/**
 * 分页
 * created by fuyd on 2019-07-04
 */
public class Pages {

    private static final int DEFAULT_SIZE = 10;
    private static final String[] DEFAULT_FIELD = {"id"};

    public static Pageable byDesc(int page, String... properties) {
        if (null == properties || 0 == properties.length) {
            properties = DEFAULT_FIELD;
        }
        return PageRequest.of(page - 1, DEFAULT_SIZE, Sort.by(Sort.Direction.DESC, properties));
    }

    public static Pageable byDesc(int page, int size, String... properties) {
        if (null == properties || 0 == properties.length) {
            properties = DEFAULT_FIELD;
        }
        return PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, properties));
    }

    public static Pageable byAsc(int page, String... properties) {
        if (null == properties || 0 == properties.length) {
            properties = DEFAULT_FIELD;
        }
        return PageRequest.of(page - 1, DEFAULT_SIZE, Sort.by(Sort.Direction.ASC, properties));
    }

    public static Pageable byAsc(int page, int size, String... properties) {
        if (null == properties || 0 == properties.length) {
            properties = DEFAULT_FIELD;
        }
        return PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, properties));
    }
}
