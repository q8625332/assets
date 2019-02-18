package com.ljq.assets.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljq.assets.entity.RoleTable;
import com.ljq.assets.mapper.RoleTableMapper;
import com.ljq.assets.service.IRoleTableService;
import com.ljq.assets.util.RuntimeError;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author ljq
 * @since 2019-02-18
 */
@Service
public class RoleTableServiceImpl extends ServiceImpl<RoleTableMapper, RoleTable> implements IRoleTableService {

	@Resource
	private RoleTableMapper roleTableMapper;

	@Override
	public int addRole(RoleTable roleTable) {
		try {
			return roleTableMapper.insert(roleTable);
		} catch (Exception e) {
			throw new RuntimeError("添加角色异常", e);
		}
	}

	@Override
	public int deleteRole(List<Integer> idsList) {
		try {
			return roleTableMapper.deleteBatchIds(idsList);
		} catch (Exception e) {
			throw new RuntimeError("删除角色异常", e);
		}
	}

	@Override
	public IPage<RoleTable> getRoleTableAllAndPage(Integer start, Integer count) {
		try {
			return roleTableMapper.selectPage(new Page<RoleTable>(start, count), new QueryWrapper<RoleTable>());
		} catch (Exception e) {
			throw new RuntimeError("分页获取全部角色异常", e);
		}
	}

	@Override
	public int upDataRole(RoleTable roleTable) {
		try {
			return roleTableMapper.updateById(roleTable);
		} catch (Exception e) {
			throw new RuntimeError("修改角色异常", e);
		}
	}

}
