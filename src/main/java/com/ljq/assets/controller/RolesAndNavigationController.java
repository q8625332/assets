package com.ljq.assets.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ljq.assets.entity.RolesAndNavigation;
import com.ljq.assets.service.IRolesAndNavigationService;
import com.ljq.assets.util.ServiceResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 角色与模块表 前端控制器
 * </p>
 *
 * @author ljq
 * @since 2019-02-18
 */
@RestController
@RequestMapping("/rolesAndNavigationController")
@Api(value = "角色与模块表 前端控制器", tags = "角色与模块表", description = "角色与模块表组")
@SuppressWarnings({ "rawtypes" })
public class RolesAndNavigationController {

	@Resource
	private IRolesAndNavigationService iRolesAndNavigationService;

	@ApiOperation(value = "添加角色里的模块", notes = "rolesAndNavigation不传navId2和ID，navId2s改传操作id的字符串集合（以逗号隔开）")
	@PostMapping("/addRolesAndNavigation")
	public ServiceResult addRolesAndNavigation(RolesAndNavigation rolesAndNavigation, String navId2s) {
		try {
			iRolesAndNavigationService.deleteByNavIdAndRolIdAll(rolesAndNavigation.getRolId(),
					rolesAndNavigation.getNavId());
			if (!navId2s.equals("")) {
				String[] navId2Array = navId2s.split(",");
				for (int i = 0; i < navId2Array.length; i++) {
					rolesAndNavigation.setNavId2(Integer.parseInt(navId2Array[i]));
					iRolesAndNavigationService.addRolesAndNavigation(rolesAndNavigation);
				}
			}
			ServiceResult serviceResult = ServiceResult.success("添加角色里的模块成功");
			return serviceResult;
		} catch (Exception e) {
			e.printStackTrace();
			ServiceResult serviceResult = ServiceResult.failure("添加角色里的模块失败");
			return serviceResult;
		}
	}

	@ApiOperation(value = "根据操作组id获取全部的操作", notes = "传入：操作组ID")
	@PostMapping("/getIdBynavId")
	public ServiceResult getIdBynavId(RolesAndNavigation rolesAndNavigation) {
		try {
			List<RolesAndNavigation> res = iRolesAndNavigationService.getIdBynavId(rolesAndNavigation);
			if (res.size() > 0) {
				ServiceResult serviceResult = ServiceResult.success(res);
				return serviceResult;
			} else {
				ServiceResult serviceResult = ServiceResult.failure("根据操作组id获取全部的操作为空");
				return serviceResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
			ServiceResult serviceResult = ServiceResult.failure("根据操作组id获取全部的操作失败");
			return serviceResult;
		}
	}

	@ApiOperation(value = "查询角色里的全部导航", notes = "传入：角色ID(rolId)")
	@PostMapping("/queryAllNavigationInRoles")
	public ServiceResult queryAllNavigationInRoles(RolesAndNavigation rolesAndNavigation) {
		try {
			List<RolesAndNavigation> res = iRolesAndNavigationService.queryAllNavigationInRoles(rolesAndNavigation);
			if (res.size() > 0) {
				ServiceResult serviceResult = ServiceResult.success(res);
				return serviceResult;
			} else {
				ServiceResult serviceResult = ServiceResult.failure("查询角色里的全部导航为空");
				return serviceResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
			ServiceResult serviceResult = ServiceResult.failure("查询角色里的全部导航失败");
			return serviceResult;
		}
	}

	@ApiOperation(value = "根据角色和组的ID查询已有操作", notes = "传入：用户ID，操作组ID")
	@PostMapping("/iDQueriesBasedOnRolesAndGroups")
	public ServiceResult iDQueriesBasedOnRolesAndGroups(RolesAndNavigation rolesAndNavigation) {
		try {
			List<RolesAndNavigation> res = iRolesAndNavigationService
					.iDQueriesBasedOnRolesAndGroups(rolesAndNavigation);
			if (res.size() > 0) {
				ServiceResult serviceResult = ServiceResult.success(res);
				return serviceResult;
			} else {
				ServiceResult serviceResult = ServiceResult.failure("根据角色和组的ID查询已有操作为空");
				return serviceResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
			ServiceResult serviceResult = ServiceResult.failure("根据角色和组的ID查询已有操作失败");
			return serviceResult;
		}
	}

}
