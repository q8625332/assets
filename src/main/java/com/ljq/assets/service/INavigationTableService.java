package com.ljq.assets.service;

import java.util.HashSet;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljq.assets.entity.NavigationTable;

/**
 * <p>
 * 导航表 服务类
 * </p>
 *
 * @author ljq
 * @since 2019-02-18
 */
public interface INavigationTableService extends IService<NavigationTable> {

	NavigationTable getNavigationTableById(Integer navId);

	int addNavigationTable(NavigationTable navigationTable);

	int deleteNavigationTableAll(List<Integer> arr);

	List<NavigationTable> getSubLevelNav(Integer id);

	List<NavigationTable> getNavByIdList(HashSet<Integer> navIdAll2);

	List<NavigationTable> getNavigationTableAll();

	List<NavigationTable> QueryTheChildLevelAccordingToTheParentId(NavigationTable navigationTable);

	int upDataNavigationTableById(NavigationTable navigationTable);

}
