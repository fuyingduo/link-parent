package com.custom.util;

import com.custom.common.ICallback;
import com.custom.enums.RequestTypeEnum;
import okhttp3.*;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;
import java.util.Objects;
import java.util.Optional;

/**
 * okhttp3工具类
 * created by fuyd on 2019-07-26
 */
public class Okhttp3Util {

    /**
     * 默认调用超时
     */
    private static final int DEFAULT_CALL_TIMEOUT = 10;

    /**
     * 默认连接超时
     */
    private static final int DEFAULT_CONNECT_TIMEOUT = 10;

    /**
     * 默认读取超时
     */
    private static final int DEFAULT_READ_TIMEOUT = 10;

    /**
     * HTTP客户端
     */
    volatile private static OkHttpClient client;
    private static final Logger LOG = LoggerFactory.getLogger(Okhttp3Util.class);

    static {
        if (null == client) {
            initialize();
        }
    }

    /**
     * 初始化Okhttpclient
     */
    private static void initialize() {
        client = new OkHttpClient()
                .newBuilder()
                .callTimeout(Duration.ofSeconds(DEFAULT_CALL_TIMEOUT))
                .readTimeout(Duration.ofSeconds(DEFAULT_READ_TIMEOUT))
                .connectTimeout(Duration.ofSeconds(DEFAULT_CONNECT_TIMEOUT))
                .retryOnConnectionFailure(true)
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    if (null == request.body() || null != request.header("Content-Encoding")) {
                        return chain.proceed(request);
                    }
                    LOG.info("[okhttp3] 启用GZIP压缩...");
                    Request gzipRequest = request.newBuilder()
                            .header("Content-Encoding", "gzip")
                            .method(request.method(), gzip(request.body()))
                            .build();
                    return chain.proceed(gzipRequest);
                })
                .eventListener(new EventListener() {
                    @Override
                    public void callStart(@NotNull Call call) {
                        String method = call.request().method();
                        HttpUrl url = call.request().url();
                        System.out.println("[okhttp3] 请求监听: [请求地址:{" + url + "},请求方法:{" + method + "}]");
                        super.callStart(call);
                    }

                    @Override
                    public void callFailed(@NotNull Call call, @NotNull IOException ioe) {
                        System.out.println("[okhttp3] 失败监听: [失败原因:{" + ioe.getMessage() + "}]");
                        super.callFailed(call, ioe);
                    }
                }).build();
    }

    /**
     * 发送请求
     *
     * @param url         url
     * @param builder     参数
     * @param requestType 请求类型
     * @return optional
     */
    public static Optional<String> send(String url, FormBody.Builder builder, RequestTypeEnum requestType) {
        try {
            Response response = client.newCall(obtainRequest(url, builder, requestType)).execute();
            try (ResponseBody responseBody = response.body()) {
                if (response.isSuccessful()) {
                    return Optional.of(Objects.requireNonNull(responseBody).string());
                }
                return Optional.empty();
            }
        } catch (IOException e) {
            LOG.error("[okhttp3] 请求异常e:{}", e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * 发送请求
     *
     * @param url         url
     * @param builder     参数
     * @param requestType 请求类型
     *                    {@link RequestTypeEnum}
     * @param iCallback   回调接口实现
     */
    public static void sendAndCallback(String url, FormBody.Builder builder, RequestTypeEnum requestType, ICallback iCallback) {
        if (null == iCallback) {
            LOG.error("[okhttp3] 回调接口为空");
            return;
        }
        client.newCall(obtainRequest(url, builder, requestType)).enqueue(iCallback);
    }

    /**
     * 获取请求
     *
     * @param url         url
     * @param builder     参数
     * @param requestType 请求类型
     *                    {@link RequestTypeEnum}
     * @return Request
     */
    private static Request obtainRequest(String url, FormBody.Builder builder, RequestTypeEnum requestType) {
        if (null == builder) {
            builder = new FormBody.Builder();
        }
        return new Request.Builder()
                .url(url)
                .addHeader("Connection", "keep-alive")
                .addHeader("device", "web")
                .method(requestType.toString(), builder.build())
                .build();
    }

    /**
     * 消息gzip压缩
     *
     * @param body 消息体
     * @return RequestBody
     */
    private static RequestBody gzip(final RequestBody body) {
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return body.contentType();
            }

            @Override
            public long contentLength() {
                return -1; // We don't know the compressed length in advance!
            }

            @Override
            public void writeTo(@NotNull BufferedSink sink) throws IOException {
                BufferedSink gzipSink = Okio.buffer(new GzipSink(sink));
                body.writeTo(gzipSink);
                gzipSink.close();
            }
        };
    }
}
