package com.ynding.cloud.book.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
//                .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
                .apis(RequestHandlerSelectors.basePackage("com.ynding.cloud"))
                .paths(PathSelectors.any())
                .build()
//                .securitySchemes(securitySchemes())
//                .securityContexts(securityContexts())
                ;
    }

    private List<ApiKey> securitySchemes() {
        List<ApiKey> r = new ArrayList<>();
        r.add(new ApiKey("Authorization", "Authorization", "header"));
        return r;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> r = new ArrayList<>();
        r.add(SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build());
        return r;
    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> r = new ArrayList<>();
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        r.add(new SecurityReference("Authorization", authorizationScopes));
        return r;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("springboot 接口文档")
                .termsOfServiceUrl("termsOfServiceUrl")
                .license("http://springfox.github.io/springfox/docs/current/")
                .licenseUrl("http://springfox.github.io/springfox/docs/current/")
                .version("1.0").build();
    }
}
