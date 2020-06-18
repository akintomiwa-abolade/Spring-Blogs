package com.jboss.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket swaggerConfiguration(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/v1/blog/*"))
                .apis(RequestHandlerSelectors.basePackage("com.jboss"))
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails(){
        return new ApiInfo(
                "Simple Blogging System",
                "Sample API for Blogs",
                "1.0",
                "Free to Use",
                new springfox.documentation.service.Contact("ABOLADE, Akintomiwa","https://twitter.com/devJboss","abolade.akintomiwa@gmail.com"),
                "API License",
                "https://akintomiwa-abolade.com",
                Collections.emptyList());
    }
}
