package com.ljq.assets.service.impl;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljq.assets.entity.NavigationTable;
import com.ljq.assets.mapper.NavigationTableMapper;
import com.ljq.assets.service.INavigationTableService;
import com.ljq.assets.util.RuntimeError;

/**
 * <p>
 * 导航表 服务实现类
 * </p>
 *
 * @author ljq
 * @since 2019-02-18
 */
@Service
public class NavigationTableServiceImpl extends ServiceImpl<NavigationTableMapper, NavigationTable>
		implements INavigationTableService {

	@Resource
	private NavigationTableMapper navigationTableMapper;

	@Override
	public NavigationTable getNavigationTableById(Integer navId) {
		try {
			return navigationTableMapper.selectById(navId);
		} catch (Exception e) {
			throw new RuntimeError("根据ID查询导航详情异常", e);
		}
	}

	@Override
	public int addNavigationTable(NavigationTable navigationTable) {
		try {
			return navigationTableMapper.insert(navigationTable);
		} catch (Exception e) {
			throw new RuntimeError("添加导航栏异常", e);
		}
	}

	@Override
	public int deleteNavigationTableAll(List<Integer> arr) {
		try {
			return navigationTableMapper.deleteBatchIds(arr);
		} catch (Exception e) {
			throw new RuntimeError("删除导航栏以及所有子级导航栏异常", e);
		}
	}

	@Override
	public List<NavigationTable> getSubLevelNav(Integer id) {
		try {
			return navigationTableMapper.selectList(new QueryWrapper<NavigationTable>().eq("pid", id));
		} catch (Exception e) {
			throw new RuntimeError("添加导航栏异常", e);
		}
	}

	@Override
	public List<NavigationTable> getNavByIdList(HashSet<Integer> navIdAll2) {
		try {
			return navigationTableMapper.selectBatchIds(navIdAll2);
		} catch (Exception e) {
			throw new RuntimeError("根据id集合获取全部导航栏信息异常", e);
		}
	}

	@Override
	public List<NavigationTable> getNavigationTableAll() {
		try {
			return navigationTableMapper.selectList(new QueryWrapper<NavigationTable>());
		} catch (Exception e) {
			throw new RuntimeError("根据id集合获取全部导航栏信息异常", e);
		}
	}

	@Override
	public List<NavigationTable> QueryTheChildLevelAccordingToTheParentId(NavigationTable navigationTable) {
		try {
			return navigationTableMapper
					.selectList(new QueryWrapper<NavigationTable>().eq("pid", navigationTable.getId()));
		} catch (Exception e) {
			throw new RuntimeError("根据父id查询子级异常", e);
		}
	}

	@Override
	public int upDataNavigationTableById(NavigationTable navigationTable) {
		try {
			return navigationTableMapper.updateById(navigationTable);
		} catch (Exception e) {
			throw new RuntimeError("根据id更新导航栏内容异常", e);
		}
	}

}
