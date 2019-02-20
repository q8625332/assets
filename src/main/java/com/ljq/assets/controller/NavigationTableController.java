package com.ljq.assets.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ljq.assets.entity.NavigationTable;
import com.ljq.assets.entity.RolesAndNavigation;
import com.ljq.assets.entity.RolesAndUsers;
import com.ljq.assets.service.INavigationTableService;
import com.ljq.assets.service.IRolesAndNavigationService;
import com.ljq.assets.service.IRolesAndUsersService;
import com.ljq.assets.util.ServiceResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 导航表 前端控制器
 * </p>
 *
 * @author ljq
 * @since 2019-02-18
 */
@RestController
@RequestMapping("/navigationTableController")
@Api(value = "导航表 前端控制器", tags = "导航表", description = "导航表组")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class NavigationTableController {

	@Resource
	private INavigationTableService iNavigationTableService;

	@Resource
	private IRolesAndUsersService iRolesAndUsersService;

	@Resource
	private IRolesAndNavigationService IRolesAndNavigationService;

	@ApiOperation(value = "添加导航栏", notes = "传入参数：导航名称，导航地址，上级导航ID")
	@PostMapping("/addNavigationTable")
	public ServiceResult addNavigationTable(NavigationTable navigationTable) {
		try {
			if (navigationTable.getPid() == 0) {
				navigationTable.setLevel(1);
			} else {
				NavigationTable navigationTable2 = iNavigationTableService
						.getNavigationTableById(navigationTable.getPid());
				navigationTable.setLevel(navigationTable2.getLevel() + 1);
			}
			int res = iNavigationTableService.addNavigationTable(navigationTable);
			if (res > 0) {
				ServiceResult serviceResult = ServiceResult.success("添加导航成功");
				return serviceResult;
			} else {
				ServiceResult serviceResult = ServiceResult.failure("添加导航失败");
				return serviceResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
			ServiceResult serviceResult = ServiceResult.failure("添加导航失败");
			return serviceResult;
		}
	}

	@ApiOperation(value = "删除导航栏以及所有子级导航栏", notes = "传入参数：ids(字符串，以逗号隔开)")
	@PostMapping("/deleteNavigationTableAll")
	public ServiceResult deleteNavigationTableAll(String ids) {
		try {
			String[] zhuan = ids.split(",");
			List<Integer> arr = new ArrayList<>();
			for (int i = 0; i < zhuan.length; i++) {
				arr.add(Integer.parseInt(zhuan[i]));
			}
			for (int i = 0; i < zhuan.length; i++) {
				arr = infiniteLoopAcquisitionSublevel(arr, Integer.parseInt(zhuan[i]));
			}
			int res = iNavigationTableService.deleteNavigationTableAll(arr);
			if (res > 0) {
				ServiceResult serviceResult = ServiceResult.success("删除导航栏以及所有子级导航栏成功");
				return serviceResult;
			} else {
				ServiceResult serviceResult = ServiceResult.failure("删除导航栏以及所有子级导航栏失败");
				return serviceResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
			ServiceResult serviceResult = ServiceResult.failure("删除导航栏以及所有子级导航栏失败");
			return serviceResult;
		}
	}

	@ApiOperation(value = "根据用户id获取用户全部导航栏", notes = "传入参数userId,类型：String")
	@PostMapping("/getUserNavAll")
	public ServiceResult getUserNavAll(String userId) {
		try {
			List<RolesAndUsers> rnList = iRolesAndUsersService.getRolesAndNavigationNavIdAll(userId);
			if (rnList.size() > 0) {
				List<List<RolesAndNavigation>> RolesAndNavigationListAll = new ArrayList<>();
				for (RolesAndUsers rolesAndUsers : rnList) {
					List<RolesAndNavigation> RolesAndNavigationList = IRolesAndNavigationService
							.getNavAllByRolId(rolesAndUsers.getRolId());
					RolesAndNavigationListAll.add(RolesAndNavigationList);
				}
				HashSet<Integer> navIdAll = new HashSet<>();
				HashSet<Integer> navIdAll2 = new HashSet<>();
				for (int i = 0; i < RolesAndNavigationListAll.size(); i++) {
					for (RolesAndNavigation rolesAndNavigation : RolesAndNavigationListAll.get(i)) {
						navIdAll.add(rolesAndNavigation.getNavId());
						navIdAll2.add(rolesAndNavigation.getNavId2());
					}
				}
				List<NavigationTable> navigationTableList = iNavigationTableService.getNavByIdList(navIdAll);
				HashSet<NavigationTable> navigationTableHashSet = new HashSet<>();
				for (NavigationTable navigationTable : navigationTableList) {
					navigationTableHashSet.add(navigationTable);
					HashSet<NavigationTable> navigationTableHashSet2 = getAllParentInformation(navigationTableHashSet,
							navigationTable.getId());
					navigationTableHashSet.addAll(navigationTableHashSet2);
				}
				List<Map<String, Object>> mapList = new ArrayList<>();
				TreeSet<NavigationTable> youxu = new TreeSet<>(navigationTableHashSet);
				for (NavigationTable navigationTable : youxu) {
					Map<String, Object> maps = new HashMap<>();
					if (navigationTable.getPid() == 0) {
						maps.put("id", navigationTable.getId());
						maps.put("navTitle", navigationTable.getNavName());
						maps.put("pages", navigationTable.getNavPath());
						List<Map<String, Object>> res2 = privilegesRecursiveToAllSublevels(navigationTableHashSet,
								navigationTable.getId());
						if (res2.size() > 0) {
							maps.put("navType", 1);
							maps.put("children", res2);
						} else {
							maps.put("navType", 0);
						}
						mapList.add(maps);
					}
				}

				// List<NavigationTable> navigationTableList2 =
				// iNavigationTableService.getNavByIdList(navIdAll2);
				// HttpSession session = request.getSession();
				// session.setAttribute(Constant.USER_NAVIGATIONTABLELIST,
				// navigationTableList2);
				ServiceResult serviceResult = ServiceResult.success(mapList);
				return serviceResult;
			} else {
				ServiceResult serviceResult = ServiceResult.failure("当前用户还未拥有角色，请联系管理员");
				return serviceResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
			ServiceResult serviceResult = ServiceResult.failure("根据用户id获取用户全部导航栏失败");
			return serviceResult;
		}
	}

	@ApiOperation(value = "获取全部导航栏", notes = "不用传参")
	@PostMapping("/getNavigationTableAll")
	public ServiceResult getNavigationTableAll() {
		try {
			List<NavigationTable> res = iNavigationTableService.getNavigationTableAll();
			if (res.size() > 0) {
				List<Map<String, Object>> mapList = new ArrayList<>();
				Map<String, Object> map = new HashMap<>();
				map.put("id", 0);
				map.put("name", "全部");
				List<Map<String, Object>> mapList2 = new ArrayList<>();
				for (NavigationTable n : res) {
					if (n.getPid() == 0) {
						Map<String, Object> maps = new HashMap<>();
						maps.put("id", n.getId());
						maps.put("name", n.getNavName());
						List<Map<String, Object>> res2 = recursiveAcquisitionOfAllSublevels(res, n.getId());
						if (res2.size() > 0) {
							maps.put("children", res2);
						}
						mapList2.add(maps);
					}
				}
				if (mapList2.size() > 0) {
					map.put("children", mapList2);
				}
				mapList.add(map);
				ServiceResult serviceResult = ServiceResult.success(mapList);
				return serviceResult;
			} else {
				ServiceResult serviceResult = ServiceResult.failure("获取全部导航栏空");
				return serviceResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
			ServiceResult serviceResult = ServiceResult.failure("获取全部导航栏失败");
			return serviceResult;
		}
	}

	@ApiOperation(value = "根据父id查询子级", notes = "传入：传入当前的父ID")
	@PostMapping("/QueryTheChildLevelAccordingToTheParentId")
	public ServiceResult QueryTheChildLevelAccordingToTheParentId(NavigationTable navigationTable) {
		try {
			List<NavigationTable> res = iNavigationTableService
					.QueryTheChildLevelAccordingToTheParentId(navigationTable);
			if (res.size() > 0) {
				ServiceResult serviceResult = ServiceResult.success(res);
				return serviceResult;
			} else {
				ServiceResult serviceResult = ServiceResult.failure("根据父id查询子级为空");
				return serviceResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
			ServiceResult serviceResult = ServiceResult.failure("根据父id查询子级失败");
			return serviceResult;
		}
	}

	@ApiOperation(value = "根据id查询导航信息", notes = "传入：传入当前的ID")
	@PostMapping("/getNavigationTableById")
	public ServiceResult getNavigationTableById(NavigationTable navigationTable) {
		try {
			NavigationTable res = iNavigationTableService.getNavigationTableById(navigationTable.getId());
			if (res != null) {
				ServiceResult serviceResult = ServiceResult.success(res);
				return serviceResult;
			} else {
				ServiceResult serviceResult = ServiceResult.failure("根据id查询导航信息为空");
				return serviceResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
			ServiceResult serviceResult = ServiceResult.failure("根据id查询导航信息失败");
			return serviceResult;
		}
	}

	@ApiOperation(value = "根据id更新导航栏内容", notes = "传入：需要改的id及内容")
	@PostMapping("/upDataNavigationTableById")
	public ServiceResult upDataNavigationTableById(NavigationTable navigationTable) {
		try {
			int res = iNavigationTableService.upDataNavigationTableById(navigationTable);
			if (res >= 0) {
				ServiceResult serviceResult = ServiceResult.success("修改成功");
				return serviceResult;
			} else {
				ServiceResult serviceResult = ServiceResult.failure("修改失败");
				return serviceResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
			ServiceResult serviceResult = ServiceResult.failure("修改失败");
			return serviceResult;
		}
	}

	@ApiOperation(value = "获取全部导航栏除操作导航栏", notes = "不需要传值")
	@PostMapping("/getNavigationTableExceptOperationAll")
	public ServiceResult getNavigationTableExceptOperationAll() {
		try {
			List<NavigationTable> res = iNavigationTableService.getNavigationTableAll();
			if (res.size() > 0) {
				List<Map<String, Object>> mapList = new ArrayList<>();
				Map<String, Object> map = new HashMap<>();
				map.put("id", 0);
				map.put("name", "全部");
				List<Map<String, Object>> mapList2 = new ArrayList<>();
				for (NavigationTable n : res) {
					if (n.getPid() == 0) {
						Map<String, Object> maps = new HashMap<>();
						maps.put("id", n.getId());
						maps.put("name", n.getNavName());
						List<Map<String, Object>> res2 = recursiveAcquisitionOfAllSublevelsExceptOperation(res,
								n.getId());
						if (res2.size() > 0) {
							maps.put("children", res2);
						}
						mapList2.add(maps);
					}
				}
				if (mapList2.size() > 0) {
					map.put("children", mapList2);
				}
				mapList.add(map);
				ServiceResult serviceResult = ServiceResult.success(mapList);
				return serviceResult;
			} else {
				ServiceResult serviceResult = ServiceResult.failure("获取全部导航栏除操作导航栏空");
				return serviceResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
			ServiceResult serviceResult = ServiceResult.failure("获取全部导航栏除操作导航栏失败");
			return serviceResult;
		}
	}

	/*
	 * 递归获取所有父级
	 */
	public HashSet<NavigationTable> getAllParentInformation(HashSet<NavigationTable> navigationTableHashSet,
			Integer id) {
		NavigationTable navigationTable = iNavigationTableService.getNavigationTableById(id);
		if (navigationTable.getPid() != 0) {
			navigationTableHashSet.add(navigationTable);
			getAllParentInformation(navigationTableHashSet, navigationTable.getPid());
		} else {
			navigationTableHashSet.add(navigationTable);
		}
		return navigationTableHashSet;
	}

	/*
	 * 递归获取所有子级ID
	 */
	public List<Integer> infiniteLoopAcquisitionSublevel(List<Integer> arr, Integer id) {
		List<NavigationTable> navList = iNavigationTableService.getSubLevelNav(id);
		if (navList.size() > 0) {
			for (NavigationTable navigationTable : navList) {
				arr.add(navigationTable.getId());
				infiniteLoopAcquisitionSublevel(arr, navigationTable.getId());
			}
			return arr;
		} else {
			return arr;
		}
	}

	/*
	 * 剔除所有重复的数据
	 */

	public List<Integer> setList(List<Integer> list) {
		HashSet h = new HashSet(list);
		list.clear();
		list.addAll(h);
		return list;
	}

	/*
	 * 递归获取所有子级
	 */
	public List<Map<String, Object>> recursiveAcquisitionOfAllSublevels(List<NavigationTable> res, Integer id) {
		List<Map<String, Object>> mapList = new ArrayList<>();
		for (NavigationTable n : res) {
			if (Integer.parseInt(n.getPid().toString()) == Integer.parseInt(id.toString())) {
				Map<String, Object> maps = new HashMap<>();
				maps.put("id", n.getId());
				maps.put("name", n.getNavName());
				List<Map<String, Object>> res2 = recursiveAcquisitionOfAllSublevels(res, n.getId());
				if (res2.size() > 0) {
					maps.put("children", res2);
				}
				mapList.add(maps);
			}
		}

		return mapList;
	}

	/*
	 * 递归获取所有子级除操作
	 */
	public List<Map<String, Object>> recursiveAcquisitionOfAllSublevelsExceptOperation(List<NavigationTable> res,
			Integer id) {
		List<Map<String, Object>> mapList = new ArrayList<>();
		for (NavigationTable n : res) {
			if (n.getPid() == id) {
				if (!n.getNavPath().contains("Controller")) {
					Map<String, Object> maps = new HashMap<>();
					maps.put("id", n.getId());
					maps.put("name", n.getNavName());
					maps.put("type", n.getType());
					List<Map<String, Object>> res2 = recursiveAcquisitionOfAllSublevelsExceptOperation(res, n.getId());
					if (res2.size() > 0) {
						maps.put("children", res2);
					}
					mapList.add(maps);
				}
			}
		}
		return mapList;
	}

	/*
	 * 权限里递归获取所有子级
	 */
	public List<Map<String, Object>> privilegesRecursiveToAllSublevels(HashSet<NavigationTable> res, Integer id) {
		List<Map<String, Object>> mapList = new ArrayList<>();
		for (NavigationTable n : res) {
			if (n.getPid() == id) {
				if (!n.getNavPath().contains("Controller")) {
					Map<String, Object> maps = new HashMap<>();
					maps.put("id", n.getId());
					maps.put("name", n.getNavName());
					maps.put("pages", n.getNavPath());
					maps.put("type", n.getType());
					List<Map<String, Object>> res2 = privilegesRecursiveToAllSublevels(res, n.getId());
					if (res2.size() > 0) {
						maps.put("children", res2);
					}
					mapList.add(maps);
				}
			}
		}

		return mapList;
	}

}
