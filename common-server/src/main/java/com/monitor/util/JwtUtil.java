package com.monitor.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monitor.constant.LoginConstants;
import com.monitor.jwt.DefaultTokenBuilder;
import com.monitor.jwt.TokenBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * created by fuyd on 2019-06-16
 */
public class JwtUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * 生成token
     *
     * @param builder 用户信息
     * @return java.lang.String
     */
    public static String generateToken(TokenBuilder builder) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(LoginConstants.USER_INFO_KEY, builder);
        String jwt = Jwts.builder()
                .setSubject(LoginConstants.SUBJECT).setClaims(map)
                .signWith(SignatureAlgorithm.HS512, LoginConstants.SECRTE)
                .compact();
        return LoginConstants.USER_LOGIN_TOKEN + jwt;
    }

    /**
     * 解析token
     *
     * @param token token
     * @return Tokens.class
     * @throws com.sun.xml.internal.ws.handler.HandlerException
     */
    public static DefaultTokenBuilder validateToken(String token) {
        if (null == token || "".equals(token)) {
            return null;
        }
        Map<String, Object> body = Jwts.parser()
                .setSigningKey(LoginConstants.SECRTE)
                .parseClaimsJws(token.replace(LoginConstants.USER_LOGIN_TOKEN, ""))
                .getBody();

        Object user = body.get(LoginConstants.USER_INFO_KEY);
        return mapper.convertValue(user, DefaultTokenBuilder.class);
    }
}
