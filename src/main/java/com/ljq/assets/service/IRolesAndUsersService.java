package com.ljq.assets.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljq.assets.entity.RolesAndUsers;

/**
 * <p>
 * 角色与用户的关系表 服务类
 * </p>
 *
 * @author ljq
 * @since 2019-02-18
 */
public interface IRolesAndUsersService extends IService<RolesAndUsers> {

	List<RolesAndUsers> getRolesAndNavigationNavIdAll(String userId);

	List<RolesAndUsers> getRolesAndUsersByrolIdAnduserIdAll(RolesAndUsers rolesAndUsers);

	int addRolesAndUsers(RolesAndUsers rolesAndUsers);

	int deleteRolesAndUsers(List<Integer> idList);

	IPage<RolesAndUsers> queryAllUsersInRoles(RolesAndUsers rolesAndUsers, Integer start, Integer count);

	int deleteByRolIdAll(int rolId);
	
}
