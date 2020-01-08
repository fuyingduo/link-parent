package com.custom.jwt;

import com.custom.util.JwtUtil;
import lombok.ToString;

/**
 * 默认构建器
 * created by fuyd on 2019-07-04
 */
@ToString
public class DefaultTokenBuilder implements TokenBuilder {

    private Integer id;
    private String userName;
    private Integer type;
    private Integer visitJurisdiction;


    @Override
    public TokenBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public TokenBuilder setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    @Override
    public TokenBuilder setType(Integer type) {
        this.type = type;
        return this;
    }

    @Override
    public TokenBuilder setVisitJurisdiction(Integer visitJurisdiction) {
        this.visitJurisdiction = visitJurisdiction;
        return this;
    }

    @Override
    public String build() {
        return JwtUtil.generateToken(this);
    }

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getType() {
        return type;
    }

    public Integer getVisitJurisdiction() {
        return visitJurisdiction;
    }
}
