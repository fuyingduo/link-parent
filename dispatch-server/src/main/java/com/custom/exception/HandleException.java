package com.custom.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 自定义异常处理
 * created by fuyd on 2019-10-08
 */
public class HandleException extends RuntimeException {
    private static final long serialVersionUID = 5802358404443699460L;

    // 异常状态码
    private Integer code;
    // 异常信息
    private String message;

    private static final String DEFAULT_EXCEPTION = "未定义异常";
    // 加载配置文件
    private static final String DEFAULT_PROPERTIES = "handleException.properties";
    private static final ConcurrentMap<Integer, String> ERROR_CODES = new ConcurrentHashMap<>();
    private static final Logger LOG = LoggerFactory.getLogger(HandleException.class);

    static {
        HandleException.initializeMap();
    }

    /**
     * 默认构造器
     *
     * @param code 待抛出异常状态码
     *             如果传入的状态码在<p>HandleException.ERROR_CODES/p>
     *             中不存在则返回默认异常<P>HandleException.DEFAULT_EXCEPTION</P>
     */
    public HandleException(Integer code) {
        super();
        this.code = code;
        String message = HandleException.ERROR_CODES.get(code);
        if (StringUtils.isEmpty(message)) {
            message = HandleException.DEFAULT_EXCEPTION;
        }
        this.message = message;
    }

    /**
     * 初始化MAP
     * 读取配置文件信息存入内存中，如果MAP初始化完成，则直接返回
     */
    private static void initializeMap() {
        if (!CollectionUtils.isEmpty(HandleException.ERROR_CODES.keySet())) {
            return;
        }
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        try {
            inputStream = HandleException.class.getClassLoader().getResourceAsStream(HandleException.DEFAULT_PROPERTIES);
            if (null == inputStream) {
                HandleException.LOG.error("[自定义异常处理] 读取配置文件 handleException.properties 失败...");
                return;
            }
            Properties properties = new Properties();
            inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            properties.load(inputStreamReader);
            Enumeration<?> enumeration = properties.propertyNames();
            while (enumeration.hasMoreElements()) {
                String str = String.valueOf(enumeration.nextElement());
                HandleException.ERROR_CODES.put(Integer.valueOf(str), properties.getProperty(str));
            }
        } catch (IOException e) {
            HandleException.LOG.error("[自定义异常处理] 读取配置文件 handleException.properties 异常，错误信息 :{}", e.getMessage());
        } finally {
            try {
                if (null != inputStream) {
                    inputStream.close();
                }
                if (null != inputStreamReader) {
                    inputStreamReader.close();
                }
            } catch (IOException e) {
                HandleException.LOG.error("[自定义异常处理] 关闭输入流发生异常， 错误信息 :{}", e.getMessage());
            }
        }
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
