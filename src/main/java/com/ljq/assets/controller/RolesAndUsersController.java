package com.ljq.assets.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljq.assets.entity.RolesAndUsers;
import com.ljq.assets.service.IRolesAndUsersService;
import com.ljq.assets.util.ServiceResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 角色与用户的关系表 前端控制器
 * </p>
 *
 * @author ljq
 * @since 2019-02-18
 */
@RestController
@RequestMapping("/rolesAndUsersController")
@Api(value = "角色与用户的关系表 前端控制器", tags = "角色与用户的关系表", description = "角色与用户的关系表组")
@SuppressWarnings("rawtypes")
public class RolesAndUsersController {
	@Resource
	private IRolesAndUsersService iRolesAndUsersService;

	@ApiOperation(value = "添加角色里与用户", notes = "传入：rolId角色ID，用户ID字符串userIds（多的以逗号隔开），userName字符串（多的以逗号隔开）")
	@PostMapping("/addRolesAndUsers")
	public ServiceResult addRolesAndUsers(RolesAndUsers rolesAndUsers, @RequestParam("userIds") String userIds,
			@RequestParam("userNames") String userNames) {
		try {
			String[] userIdArray = userIds.split(",");
			String[] userNameArray = userNames.split(",");
			for (int i = 0; i < userIdArray.length; i++) {
				rolesAndUsers.setUserId(userIdArray[i]);
				rolesAndUsers.setUserName(userNameArray[i]);
				List<RolesAndUsers> rolesAndUsersList = iRolesAndUsersService
						.getRolesAndUsersByrolIdAnduserIdAll(rolesAndUsers);
				if (rolesAndUsersList.size() <= 0 || rolesAndUsersList == null) {
					iRolesAndUsersService.addRolesAndUsers(rolesAndUsers);
				}
			}
			ServiceResult serviceResult = ServiceResult.success("添加角色里与用户成功");
			return serviceResult;
		} catch (Exception e) {
			e.printStackTrace();
			ServiceResult serviceResult = ServiceResult.failure("添加角色里与用户失败");
			return serviceResult;
		}
	}

	@ApiOperation(value = "删除角色里与用户", notes = "传入：ids(ID字符串)，以逗号隔开")
	@PostMapping("/deleteRolesAndUsers")
	public ServiceResult deleteRolesAndUsers(String ids) {
		try {
			String[] userIdArray = ids.split(",");
			List<Integer> idList = new ArrayList<>();
			for (int i = 0; i < userIdArray.length; i++) {
				idList.add(Integer.parseInt(userIdArray[i]));
			}
			int res = iRolesAndUsersService.deleteRolesAndUsers(idList);
			if (res > 0) {
				ServiceResult serviceResult = ServiceResult.success("删除角色里与用户成功");
				return serviceResult;
			} else {
				ServiceResult serviceResult = ServiceResult.failure("删除角色里与用户失败");
				return serviceResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
			ServiceResult serviceResult = ServiceResult.failure("删除角色里与用户失败");
			return serviceResult;
		}
	}

	@ApiOperation(value = "查询角色里的用户", notes = "传入：rolId(角色id)，start（开始页），count（总数）")
	@PostMapping("/queryAllUsersInRoles")
	public ServiceResult queryAllUsersInRoles(RolesAndUsers rolesAndUsers, @RequestParam("start") int start,
			@RequestParam("count") int count) {
		try {
			IPage<RolesAndUsers> res = iRolesAndUsersService.queryAllUsersInRoles(rolesAndUsers, start, count);
			if (res.getRecords().size() > 0) {
				ServiceResult serviceResult = ServiceResult.success(res);
				return serviceResult;
			} else {
				ServiceResult serviceResult = ServiceResult.failure("查询角色里的全部用户为空");
				return serviceResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
			ServiceResult serviceResult = ServiceResult.failure("查询角色里的全部用户失败");
			return serviceResult;
		}
	}
}
