package com.ljq.assets.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * @email 867170960@qq.com
 * @author:刘俊秦
 * @date: 2018/10/4 0004
 * @time: 下午 8:23
 */

@Configuration
public class DruidConfig {

	// 配置Druid的监控
	@SuppressWarnings({ "rawtypes", "unchecked" })
	// 1、配置一个管理后台的Servlet
	@Bean
	public ServletRegistrationBean statViewServlet() {
		ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
		Map<String, String> initParams = new HashMap<>();

		initParams.put("loginUsername", "admin");
		initParams.put("loginPassword", "liu1101***");
		// 默认就是允许所有访问
		initParams.put("allow", "");
		initParams.put("deny", "");

		bean.setInitParameters(initParams);
		return bean;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}

}
