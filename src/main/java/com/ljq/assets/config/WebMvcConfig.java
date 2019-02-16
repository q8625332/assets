package com.ljq.assets.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.ljq.assets.interceptor.LoginInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

	@Resource
	private LoginInterceptor loginInterceptor;

	/* 添加控制器 */
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
				.excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**")
				.excludePathPatterns("/indexController/welcome").excludePathPatterns("**/druid/");

	}

	/* 配置内容裁决的一些选项 */
	@Override
	protected void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	}

	/* 视图跳转控制器 */
	@Override
	protected void addViewControllers(ViewControllerRegistry registry) {
	}

	/* 静态资源处理 */
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}

	/* 默认静态资源处理 */
	@Override
	protected void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	}

	/* 配置视图解析器 */
	@Override
	protected void configureViewResolvers(ViewResolverRegistry registry) {
	}

	/* 解决跨域问题 */
	@Override
	protected void addCorsMappings(CorsRegistry registry) {
		// 设置允许跨域的路径
		registry.addMapping("/**")
				// 设置允许跨域请求的域名
				.allowedOrigins("*")
				// 是否允许证书 不再默认开启
				.allowCredentials(true)
				// 设置允许的方法
				.allowedMethods("*")
				// 跨域允许时间
				.maxAge(3600);
	}

}
