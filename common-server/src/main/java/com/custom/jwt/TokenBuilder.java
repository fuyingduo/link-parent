package com.custom.jwt;

/**
 * created by fuyd on 2019-07-04
 */
public interface TokenBuilder {

    TokenBuilder setId(Integer id);

    TokenBuilder setUserName(String userName);

    TokenBuilder setType(Integer type);

    TokenBuilder setVisitJurisdiction(Integer visitJurisdiction);

    String build();
}
