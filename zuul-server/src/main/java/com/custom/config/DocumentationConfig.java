package com.custom.config;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.Arrays;
import java.util.List;

/**
 * created by fuyd on 2019-07-02
 */
@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {

    private static final String DEFAULT_VERSION = "2.0";

    @Override
    public List<SwaggerResource> get() {
        return Arrays.asList(
                swaggerResource("定时MODEL", "/schedule/v2/api-docs"),
                swaggerResource("用户MODEL", "/user/v2/api-docs"),
                swaggerResource("产品MODEL", "/product/v2/api-docs")
        );
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(DocumentationConfig.DEFAULT_VERSION);
        return swaggerResource;
    }
}
