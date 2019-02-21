package com.ljq.assets.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljq.assets.entity.StatusHeading;
import com.ljq.assets.service.IStatusHeadingService;
import com.ljq.assets.util.ServiceResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 状态标题 前端控制器
 * </p>
 *
 * @author ljq
 * @since 2019-02-20
 */
@RestController
@RequestMapping("/statusHeadingController")
@Api(value = "状态标题 前端控制器", tags = "状态标题", description = "状态标题组")
@SuppressWarnings({ "rawtypes" })
public class StatusHeadingController {

	@Resource
	private IStatusHeadingService iStatusHeadingService;

	@ApiOperation(value = "获取全部状态标题", notes = "不用传参")
	@PostMapping("/getStatusHeadingAll")
	public ServiceResult getStatusHeadingAll() {
		List<StatusHeading> res = iStatusHeadingService.list(new QueryWrapper<StatusHeading>());
		if (!res.isEmpty()) {
			ServiceResult serviceResult = ServiceResult.success(res);
			return serviceResult;
		} else {
			ServiceResult serviceResult = ServiceResult.failure("获取全部状态标题为空");
			return serviceResult;
		}
	}

}
