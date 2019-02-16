package com.ljq.assets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @email 867170960@qq.com
 * @author:刘俊秦
 * @date: 2018/10/25 0025
 * @time: 上午 10:38
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {

        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("资产管理项目")
                .description("资产管理项目的接口文档，符合RESTful API。")
                .version("1.0")
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ljq.assets.controller")) //以扫描包的方式
                .paths(PathSelectors.any())
                .build();
    }
}
