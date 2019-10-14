package com.custom.constant;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * created by fuyd on 2019-06-16
 */
public class URIIgnoreConstant {

    public static Boolean hasIgnore(String path) {
        return IGNORES.stream().anyMatch(ignore -> Pattern.compile(getRegPath(ignore)).matcher(path).matches());
    }

    /**
     * 过滤器忽略URI
     */
    private static final List<String> IGNORES = Arrays.asList
            (
                    "/user/user/login",
                    "/user/sms/*",
                    "**/v2/api-docs",
                    "/product/**"
            );

    private static String getRegPath(String path) {
        char[] chars = path.toCharArray();
        int len = chars.length;
        StringBuilder sb = new StringBuilder();
        boolean preX = false;
        for (int i = 0; i < len; i++) {
            if (chars[i] == '*') {
                if (preX) {
                    sb.append(".*");
                    preX = false;
                } else if (i + 1 == len) {
                    sb.append("[^/]*");
                } else {
                    preX = true;
                }
            } else {
                if (preX) {
                    sb.append("[^/]*");
                    preX = false;
                }
                if (chars[i] == '?') {
                    sb.append('.');
                } else {
                    sb.append(chars[i]);
                }
            }
        }
        return sb.toString();
    }
}
