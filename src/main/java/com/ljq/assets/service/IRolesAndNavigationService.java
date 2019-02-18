package com.ljq.assets.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljq.assets.entity.RolesAndNavigation;

/**
 * <p>
 * 角色与模块表 服务类
 * </p>
 *
 * @author ljq
 * @since 2019-02-18
 */
public interface IRolesAndNavigationService extends IService<RolesAndNavigation> {

	List<RolesAndNavigation> getNavAllByRolId(Integer rolId);

	int deleteByNavIdAndRolIdAll(Integer rolId,Integer navId);

	int addRolesAndNavigation(RolesAndNavigation rolesAndNavigation);

	List<RolesAndNavigation> getIdBynavId(RolesAndNavigation rolesAndNavigation);

	List<RolesAndNavigation> queryAllNavigationInRoles(RolesAndNavigation rolesAndNavigation);

	List<RolesAndNavigation> iDQueriesBasedOnRolesAndGroups(RolesAndNavigation rolesAndNavigation);

	int deleteByRolIdAll(int rolId);
	
}
