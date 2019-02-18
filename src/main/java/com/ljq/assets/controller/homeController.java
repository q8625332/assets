package com.ljq.assets.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiDepartmentGetRequest;
import com.dingtalk.api.request.OapiDepartmentListIdsRequest;
import com.dingtalk.api.request.OapiDepartmentListRequest;
import com.dingtalk.api.request.OapiUserGetRequest;
import com.dingtalk.api.request.OapiUserGetuserinfoRequest;
import com.dingtalk.api.request.OapiUserSimplelistRequest;
import com.dingtalk.api.response.OapiDepartmentGetResponse;
import com.dingtalk.api.response.OapiDepartmentListIdsResponse;
import com.dingtalk.api.response.OapiDepartmentListResponse;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.dingtalk.api.response.OapiUserGetuserinfoResponse;
import com.dingtalk.api.response.OapiUserSimplelistResponse;
import com.ljq.assets.config.URLConstant;
import com.ljq.assets.util.AccessTokenUtil;
import com.ljq.assets.util.ServiceResult;
import com.ljq.assets.util.jwtUtil;
import com.taobao.api.ApiException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/homeController")
@Api(value = "钉钉", tags = { "钉钉" }, description = "钉钉功能组")
@SuppressWarnings({ "rawtypes" })
public class homeController {

	/**
	 * @Description: 获取用户id，用户头像，用户部门，用户名字
	 * @Param: [requestAuthCode]
	 * @return: com.tf.customer.util.ServiceResult
	 * @Author: 刘俊秦
	 * @Date: 2018/10/19 0019
	 */
	@ApiOperation(value = "获取用户信息", notes = "获取用户id，用户头像，用户部门，用户名字")
	@RequestMapping(value = "/getUserInformation", method = RequestMethod.POST)
	public ServiceResult getUserInformation(@RequestParam(value = "authCode") String requestAuthCode) {
		try {
			// 获取access_token
			String accessToken = AccessTokenUtil.getToken();

			DingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_GET_USER_INFO);
			OapiUserGetuserinfoRequest request = new OapiUserGetuserinfoRequest();
			request.setCode(requestAuthCode);
			request.setHttpMethod("GET");

			OapiUserGetuserinfoResponse response;

			response = client.execute(request, accessToken);

			// 3.查询得到当前用户的userId
			// 获得到userId之后应用应该处理应用自身的登录会话管理（session）,避免后续的业务交互（前端到应用服务端）每次都要重新获取用户身份，提升用户体验
			String userId = response.getUserid();

			OapiUserGetResponse oapiUserGetResponse = getUserName(accessToken, userId);

			List<Long> departmentList = oapiUserGetResponse.getDepartment();

			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(departmentList.get(0));

			OapiDepartmentGetResponse oapiDepartmentGetResponse = getUserDepartment(accessToken,
					stringBuffer.toString());

			String userIdOU = oapiUserGetResponse.getUserid();
			String userNameOU = oapiUserGetResponse.getName();
			String token = jwtUtil.sgin(userNameOU, userIdOU);

			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("userId", userIdOU);
			resultMap.put("userName", userNameOU);
			resultMap.put("userDepartment", oapiDepartmentGetResponse.getName());
			resultMap.put("avatar", oapiUserGetResponse.getAvatar());
			resultMap.put("position", oapiUserGetResponse.getPosition());
			resultMap.put("token", token);

			ServiceResult serviceResult = ServiceResult.success(resultMap);

			return serviceResult;

		} catch (ApiException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * @Description: 获取子部门id列表
	 * @Param: [accessToken, id]
	 * @return: com.dingtalk.api.response.OapiDepartmentListIdsResponse
	 * @Author: 刘俊秦
	 * @Date: 2018/11/8 0008
	 */
	public OapiDepartmentListIdsResponse getOapiDepartmentListIdsResponse(String accessToken, String id) {
		try {
			DingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_GET_DEPARTMENT_ID_LIST);
			OapiDepartmentListIdsRequest request = new OapiDepartmentListIdsRequest();
			request.setId(id);
			request.setHttpMethod("GET");
			OapiDepartmentListIdsResponse response = client.execute(request, accessToken);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Description: 获取用户信息
	 * @Param: [accessToken, userId]
	 * @return: com.dingtalk.api.response.OapiUserGetResponse
	 * @Author: 刘俊秦
	 * @Date: 2018/11/8 0008
	 */
	public OapiUserGetResponse getUserName(String accessToken, String userId) {
		try {
			DingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_USER_GET);
			OapiUserGetRequest request = new OapiUserGetRequest();
			request.setUserid(userId);
			request.setHttpMethod("GET");
			OapiUserGetResponse response = client.execute(request, accessToken);
			return response;
		} catch (ApiException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Description: 获取部门信息
	 * @Param: [accessToken, DepartmentId]
	 * @return: com.dingtalk.api.response.OapiDepartmentGetResponse
	 * @Author: 刘俊秦
	 * @Date: 2018/11/8 0008
	 */
	public OapiDepartmentGetResponse getUserDepartment(String accessToken, String DepartmentId) {
		try {
			DingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_GET_DEPARTMENT);
			OapiDepartmentGetRequest request = new OapiDepartmentGetRequest();
			request.setId(DepartmentId);
			request.setHttpMethod("GET");
			OapiDepartmentGetResponse response = client.execute(request, accessToken);
			return response;
		} catch (ApiException e) {
			e.printStackTrace();
			return null;
		}
	}

	@ApiOperation(value = "获取全部人员", notes = "不用传参")
	@PostMapping("/getAllPersonnel")
	public ServiceResult getAllPersonnel() {
		try {
			String accessToken = AccessTokenUtil.getToken();
			OapiDepartmentListResponse response = getDepartmentList(accessToken, "1");
			HashSet<Map<String, Object>> mapSet = new HashSet<>();
			for (int i = 0; i < response.getDepartment().size(); i++) {
				OapiUserSimplelistResponse response2 = acquiringDepartmentUsers(accessToken,
						response.getDepartment().get(i).getId());
				for (int j = 0; j < response2.getUserlist().size(); j++) {
					Map<String, Object> map = new HashMap<>();
					map.put("id", response2.getUserlist().get(j).getUserid());
					map.put("name", response2.getUserlist().get(j).getName());
					mapSet.add(map);
				}
			}
			ServiceResult serviceResult = ServiceResult.success(mapSet);
			return serviceResult;
		} catch (Exception e) {
			e.printStackTrace();
			ServiceResult serviceResult = ServiceResult.failure("获取全部人员失败");
			return serviceResult;
		}
	}

	@ApiOperation(value = "获取全部部门", notes = "不用传参")
	@PostMapping("/getAllDepartments")
	public ServiceResult getAllDepartments() {
		try {
			String accessToken = AccessTokenUtil.getToken();
			OapiDepartmentListResponse response = getDepartmentList(accessToken, "1");
			HashSet<Map<String, Object>> mapSet = new HashSet<>();
			for (int i = 0; i < response.getDepartment().size(); i++) {
				Map<String, Object> map = new HashMap<>();
				map.put("id", response.getDepartment().get(i).getId());
				map.put("name", response.getDepartment().get(i).getName());
				mapSet.add(map);
			}
			ServiceResult serviceResult = ServiceResult.success(mapSet);
			return serviceResult;
		} catch (Exception e) {
			e.printStackTrace();
			ServiceResult serviceResult = ServiceResult.failure("获取全部部门失败");
			return serviceResult;
		}
	}

	/*
	 * 获取部门列表
	 */
	public static OapiDepartmentListResponse getDepartmentList(String accessToken, String id) throws ApiException {
		DingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_GET_DEPARTMENT_LIST);
		OapiDepartmentListRequest request = new OapiDepartmentListRequest();
		request.setId(id);
		request.setHttpMethod("GET");
		OapiDepartmentListResponse response = client.execute(request, accessToken);
		return response;
	}

	/*
	 * 获取部门用户
	 */
	public static OapiUserSimplelistResponse acquiringDepartmentUsers(String accessToken, Long id) throws ApiException {
		DingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_USER_SIMPLELIST);
		OapiUserSimplelistRequest request = new OapiUserSimplelistRequest();
		request.setDepartmentId(id);
		request.setHttpMethod("GET");

		OapiUserSimplelistResponse response = client.execute(request, accessToken);
		return response;
	}

	@ApiOperation(value = "获取部门树", notes = "不用传值")
	@PostMapping("/branchTree")
	public ServiceResult BranchTree() {
		try {
			List<Map<String, Object>> mapList = new ArrayList<>();
			Map<String, Object> maps = null;
			String accessToken = AccessTokenUtil.getToken();
			OapiDepartmentListIdsResponse response = getOapiDepartmentListIdsResponse(accessToken, "1");
			if (null != response.getSubDeptIdList()) {
				for (int i = 0; i < response.getSubDeptIdList().size(); i++) {
					maps = new HashMap<>();
					String id = response.getSubDeptIdList().get(i).toString();
					OapiDepartmentGetResponse departmentResponse = getUserDepartment(accessToken, id);
					String dId = departmentResponse.getId().toString();
					maps.put("id", dId);
					maps.put("name", departmentResponse.getName());
					List<Map<String, Object>> subsidiaryDepartment = recursiveAcquisitionSubdivisions(accessToken, dId);
					if (!subsidiaryDepartment.isEmpty()) {
						maps.put("children", subsidiaryDepartment);
					}
					mapList.add(maps);
				}
				ServiceResult serviceResult = ServiceResult.success(maps);
				return serviceResult;
			} else {
				ServiceResult serviceResult = ServiceResult.failure("异步获取主部门树为空");
				return serviceResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
			ServiceResult serviceResult = ServiceResult.failure("异步获取主部门树失败");
			return serviceResult;
		}
	}

	public List<Map<String, Object>> recursiveAcquisitionSubdivisions(String accessToken, String id) {
		List<Map<String, Object>> mapList = new ArrayList<>();
		OapiDepartmentListIdsResponse response = getOapiDepartmentListIdsResponse(accessToken, id);
		if (!response.getSubDeptIdList().isEmpty()) {
			for (int i = 0; i < response.getSubDeptIdList().size(); i++) {
				Map<String, Object> map = new HashMap<>();
				String zid = response.getSubDeptIdList().get(i).toString();
				OapiDepartmentGetResponse departmentResponse = getUserDepartment(accessToken, zid);
				String dId = departmentResponse.getId().toString();
				map.put("id", dId);
				map.put("name", departmentResponse.getName());
				List<Map<String, Object>> subsidiaryDepartment = recursiveAcquisitionSubdivisions(accessToken, dId);
				if (!subsidiaryDepartment.isEmpty()) {
					map.put("children", subsidiaryDepartment);
				}
				mapList.add(map);
			}
		}
		return mapList;
	}

}
