package com.mit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
//swagger 사용법

//http://localhost:9999/mit/swagger-ui.html#/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("mitboard")
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.mit.controller"))
				.paths(PathSelectors.ant("/api/**"))
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("MIT API")
				.description("MIT API Reference for Developers")
				.termsOfServiceUrl("https://redbinalgorithm.tistory.com/category/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8/SNS")
				.license("MIT License")
				.licenseUrl("wjdals0471@naver.com").version("1.0").build();
	}

}
