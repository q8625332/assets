package com.ljq.assets.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljq.assets.entity.RoleTable;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author ljq
 * @since 2019-02-18
 */
public interface IRoleTableService extends IService<RoleTable> {

	int addRole(RoleTable roleTable);

	int deleteRole(List<Integer> idsList);

	IPage<RoleTable> getRoleTableAllAndPage(Integer start, Integer count);

	int upDataRole(RoleTable roleTable);
	
}
