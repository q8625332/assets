package com.ljq.assets.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljq.assets.entity.RolesAndUsers;
import com.ljq.assets.mapper.RolesAndUsersMapper;
import com.ljq.assets.service.IRolesAndUsersService;
import com.ljq.assets.util.RuntimeError;

/**
 * <p>
 * 角色与用户的关系表 服务实现类
 * </p>
 *
 * @author ljq
 * @since 2019-02-18
 */
@Service
public class RolesAndUsersServiceImpl extends ServiceImpl<RolesAndUsersMapper, RolesAndUsers>
		implements IRolesAndUsersService {

	@Resource
	private RolesAndUsersMapper rolesAndUsersMapper;

	@Override
	public List<RolesAndUsers> getRolesAndNavigationNavIdAll(String userId) {
		try {
			return rolesAndUsersMapper.selectList(new QueryWrapper<RolesAndUsers>().eq("userId", userId));
		} catch (Exception e) {
			throw new RuntimeError("根据条件userId查询出全部角色与用户的数据异常", e);
		}
	}

	@Override
	public List<RolesAndUsers> getRolesAndUsersByrolIdAnduserIdAll(RolesAndUsers rolesAndUsers) {
		try {
			List<RolesAndUsers> res = rolesAndUsersMapper.selectList(new QueryWrapper<RolesAndUsers>()
					.eq("rolId", rolesAndUsers.getRolId()).eq("userId", rolesAndUsers.getUserId()));
			return res;
		} catch (Exception e) {
			throw new RuntimeError("根据条件rolId和userid查询出全部异常", e);
		}
	}

	@Override
	public int addRolesAndUsers(RolesAndUsers rolesAndUsers) {
		try {
			return rolesAndUsersMapper.insert(rolesAndUsers);
		} catch (Exception e) {
			throw new RuntimeError("添加用户与角色数据异常", e);
		}
	}

	@Override
	public int deleteRolesAndUsers(List<Integer> idList) {
		try {
			return rolesAndUsersMapper.deleteBatchIds(idList);
		} catch (Exception e) {
			throw new RuntimeError("删除用户与角色数据异常", e);
		}
	}

	@Override
	public IPage<RolesAndUsers> queryAllUsersInRoles(RolesAndUsers rolesAndUsers, Integer start, Integer count) {
		try {
			return rolesAndUsersMapper.selectPage(new Page<RolesAndUsers>(start, count),
					new QueryWrapper<RolesAndUsers>().eq("rolId", rolesAndUsers.getRolId()));
		} catch (Exception e) {
			throw new RuntimeError("查询角色里的用户异常", e);
		}
	}

	@Override
	public int deleteByRolIdAll(int rolId) {
		try {
			return rolesAndUsersMapper.delete(new QueryWrapper<RolesAndUsers>().eq("rolId", rolId));
		} catch (Exception e) {
			throw new RuntimeError("根据角色id删除角色里的全部用户异常", e);
		}
	}

}
