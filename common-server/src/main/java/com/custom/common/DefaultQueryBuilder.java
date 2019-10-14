package com.custom.common;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * created by fuyd on 2019-07-08
 */
public class DefaultQueryBuilder implements QueryBuilder {

    // 模糊查询
    private List<String> containings;

    // 以...开始
    private List<String> startings;

    // 以...结束
    private List<String> endings;

    // 包含
    private List<String> contains;

    @Override
    public QueryBuilder containing(String... containing) {
        if (null == this.containings) {
            this.containings = new ArrayList<>();
        }
        this.containings.addAll(Arrays.asList(containing));
        return this;
    }

    @Override
    public QueryBuilder starting(String... starting) {
        if (null == this.startings) {
            this.startings = new ArrayList<>();
        }
        this.startings.addAll(Arrays.asList(starting));
        return this;
    }

    @Override
    public QueryBuilder ending(String... ending) {
        if (null == this.endings) {
            this.endings = new ArrayList<>();
        }
        this.endings.addAll(Arrays.asList(ending));
        return this;
    }

    @Override
    public QueryBuilder contains(String... contains) {
        if (null == this.endings) {
            this.contains = new ArrayList<>();
        }
        this.contains.addAll(Arrays.asList(contains));
        return this;
    }

    @Override
    public <T> Example<T> build(T t) {
        final ExampleMatcher[] matching = {ExampleMatcher.matching()};
        if (null != this.containings) {
            this.containings.forEach(key ->
                    matching[0] = matching[0].withMatcher(key, ExampleMatcher.GenericPropertyMatchers.contains()));
        }
        if (null != this.startings) {
            this.startings.forEach(key ->
                    matching[0] = matching[0].withMatcher(key, ExampleMatcher.GenericPropertyMatchers.startsWith()));
        }
        if (null != this.endings) {
            this.endings.forEach(key ->
                    matching[0] = matching[0].withMatcher(key, ExampleMatcher.GenericPropertyMatchers.endsWith()));
        }
        if (null != this.contains) {
            this.contains.forEach(key ->
                    matching[0] = matching[0].withMatcher(key, ExampleMatcher.GenericPropertyMatchers.contains()));
        }
        return Example.of(t, matching[0]);
    }
}
