package com.ljq.assets.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/indexController")
@Api(value = "启动测试", tags = "启动测试", description = "启动测试组")
public class indexController {

	@ApiOperation(value = "欢迎", notes = "欢迎接口")
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome";
	}

	@ApiOperation(value = "测试Excel上传", notes = "测试Excel上传页面接口")
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}

}
