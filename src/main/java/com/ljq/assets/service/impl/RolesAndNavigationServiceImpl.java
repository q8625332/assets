package com.ljq.assets.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljq.assets.entity.RolesAndNavigation;
import com.ljq.assets.mapper.RolesAndNavigationMapper;
import com.ljq.assets.service.IRolesAndNavigationService;
import com.ljq.assets.util.RuntimeError;

/**
 * <p>
 * 角色与模块表 服务实现类
 * </p>
 *
 * @author ljq
 * @since 2019-02-18
 */
@Service
public class RolesAndNavigationServiceImpl extends ServiceImpl<RolesAndNavigationMapper, RolesAndNavigation> implements IRolesAndNavigationService {

	@Resource
	private RolesAndNavigationMapper rolesAndNavigationMapper;

	@Override
	public List<RolesAndNavigation> getNavAllByRolId(Integer rolId) {
		try {
			return rolesAndNavigationMapper.selectList(new QueryWrapper<RolesAndNavigation>().eq("rolId", rolId));
		} catch (Exception e) {
			throw new RuntimeError("根据角色ID查询角色与模块表数据异常", e);
		}
	}

	@Override
	public int deleteByNavIdAndRolIdAll(Integer rolId, Integer navId) {
		try {
			return rolesAndNavigationMapper
					.delete(new QueryWrapper<RolesAndNavigation>().eq("rolId", rolId).eq("navId", navId));
		} catch (Exception e) {
			throw new RuntimeError("根据角色id和操作组ID删除全部异常", e);
		}
	}

	@Override
	public int addRolesAndNavigation(RolesAndNavigation rolesAndNavigation) {
		try {
			return rolesAndNavigationMapper.insert(rolesAndNavigation);
		} catch (Exception e) {
			throw new RuntimeError("添加角色里的模块异常", e);
		}
	}

	@Override
	public List<RolesAndNavigation> getIdBynavId(RolesAndNavigation rolesAndNavigation) {
		try {
			return rolesAndNavigationMapper
					.selectList(new QueryWrapper<RolesAndNavigation>().eq("navId", rolesAndNavigation.getNavId()));
		} catch (Exception e) {
			throw new RuntimeError("根据操作组id获取全部的操作异常", e);
		}
	}

	@Override
	public List<RolesAndNavigation> queryAllNavigationInRoles(RolesAndNavigation rolesAndNavigation) {
		try {
			return rolesAndNavigationMapper
					.selectList(new QueryWrapper<RolesAndNavigation>().eq("rolId", rolesAndNavigation.getRolId()));
		} catch (Exception e) {
			throw new RuntimeError("查询角色里的全部导航异常", e);
		}
	}

	@Override
	public List<RolesAndNavigation> iDQueriesBasedOnRolesAndGroups(RolesAndNavigation rolesAndNavigation) {
		try {
			return rolesAndNavigationMapper.selectList(new QueryWrapper<RolesAndNavigation>()
					.eq("rolId", rolesAndNavigation.getRolId()).eq("navId", rolesAndNavigation.getNavId()));
		} catch (Exception e) {
			throw new RuntimeError("根据角色和组的ID查询已有操作异常", e);
		}
	}

	@Override
	public int deleteByRolIdAll(int rolId) {
		try {
			return rolesAndNavigationMapper.delete(new QueryWrapper<RolesAndNavigation>().eq("rolId", rolId));
		} catch (Exception e) {
			throw new RuntimeError("根据角色ID删除全部操作异常", e);
		}
	}
	
}
