package com.ljq.assets.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljq.assets.entity.RoleTable;
import com.ljq.assets.service.IRoleTableService;
import com.ljq.assets.service.IRolesAndNavigationService;
import com.ljq.assets.service.IRolesAndUsersService;
import com.ljq.assets.util.ServiceResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author ljq
 * @since 2019-02-18
 */
@RestController
@RequestMapping("/roleTableController")
@Api(value = "角色表 前端控制器", tags = "角色表", description = "角色表组")
public class RoleTableController {

	@Resource
	private IRoleTableService iRoleTableService;

	@Resource
	private IRolesAndNavigationService iRolesAndNavigationService;

	@Resource
	private IRolesAndUsersService iRolesAndUsersService;

	@ApiOperation(value = "添加角色", notes = "除了ID，都要传")
	@PostMapping("/addRole")
	public ServiceResult addRole(RoleTable roleTable) {
		try {
			int res = iRoleTableService.addRole(roleTable);
			if (res > 0) {
				ServiceResult serviceResult = ServiceResult.success("添加角色成功");
				return serviceResult;
			} else {
				ServiceResult serviceResult = ServiceResult.failure("添加角色失败");
				return serviceResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
			ServiceResult serviceResult = ServiceResult.failure("添加角色失败");
			return serviceResult;
		}
	}

	@ApiOperation(value = "删除角色", notes = "传入id字符串,以逗号隔开")
	@PostMapping("/deleteRole")
	public ServiceResult deleteRole(String ids) {
		try {
			String[] idsArray = ids.split(",");
			List<Integer> idsList = new ArrayList<>();
			for (int i = 0; i < idsArray.length; i++) {
				int id = Integer.parseInt(idsArray[i]);
				idsList.add(id);
				iRolesAndNavigationService.deleteByRolIdAll(id);
				iRolesAndUsersService.deleteByRolIdAll(id);
			}
			int res = iRoleTableService.deleteRole(idsList);
			if (res > 0) {
				ServiceResult serviceResult = ServiceResult.success("删除角色成功");
				return serviceResult;
			} else {
				ServiceResult serviceResult = ServiceResult.failure("删除角色失败");
				return serviceResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
			ServiceResult serviceResult = ServiceResult.failure("删除角色失败");
			return serviceResult;
		}
	}

	@ApiOperation(value = "分页获取全部角色", notes = "传入：start(当前页或者初始页)，count（当前所显示的行数）")
	@PostMapping("/getRoleTableAllAndPage")
	public ServiceResult getRoleTableAllAndPage(@RequestParam("start") int start, @RequestParam("count") int count) {
		try {
			IPage<RoleTable> res = iRoleTableService.getRoleTableAllAndPage(start, count);
			if (res.getRecords().size() > 0) {
				ServiceResult serviceResult = ServiceResult.success(res);
				return serviceResult;
			} else {
				ServiceResult serviceResult = ServiceResult.failure("分页获取全部角色为空");
				return serviceResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
			ServiceResult serviceResult = ServiceResult.failure("分页获取全部角色失败");
			return serviceResult;
		}
	}

	@ApiOperation(value = "根据ID修改角色", notes = "传入ID，角色名称")
	@PostMapping("/upDataRole")
	public ServiceResult upDataRole(RoleTable roleTable) {
		try {
			int res = iRoleTableService.upDataRole(roleTable);
			if (res > 0) {
				ServiceResult serviceResult = ServiceResult.success("修改角色成功");
				return serviceResult;
			} else {
				ServiceResult serviceResult = ServiceResult.failure("修改角色失败");
				return serviceResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
			ServiceResult serviceResult = ServiceResult.failure("修改角色失败");
			return serviceResult;
		}
	}

}
