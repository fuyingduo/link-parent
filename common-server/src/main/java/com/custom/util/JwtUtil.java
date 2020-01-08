package com.custom.util;

import com.custom.constant.LoginConstants;
import com.custom.jwt.DefaultTokenBuilder;
import com.custom.jwt.TokenBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
        return Jwts.builder()
                .setSubject(LoginConstants.SUBJECT).setClaims(map)
                .signWith(SignatureAlgorithm.HS512, LoginConstants.SECRTE)
                .compact();
    }

    /**
     * 解析token
     *
     * @param token token
     * @return Tokens.class
     */
    public static DefaultTokenBuilder validateToken(String token) {
        if (null == token || "".equals(token)) {
            return null;
        }
        try {
            Map<String, Object> body = Jwts.parser()
                    .setSigningKey(LoginConstants.SECRTE)
                    .parseClaimsJws(token)
                    .getBody();

            Object user = body.get(LoginConstants.USER_INFO_KEY);
            return mapper.convertValue(user, DefaultTokenBuilder.class);
        } catch (IllegalArgumentException | SignatureException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        Instant instant = Instant.now();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Shanghai"));
        String format = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(format);
    }
}
