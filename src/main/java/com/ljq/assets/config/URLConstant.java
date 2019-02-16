package com.ljq.assets.config;

public class URLConstant {
	/**
	 * 钉钉网关gettoken地址
	 */
	public static final String URL_GET_TOKKEN = "https://oapi.dingtalk.com/gettoken";

	/**
	 *获取用户在企业内userId的接口URL
	 */
	public static final String URL_GET_USER_INFO = "https://oapi.dingtalk.com/user/getuserinfo";

	/**
	 *获取用户姓名的接口url
	 */
	public static final String URL_USER_GET = "https://oapi.dingtalk.com/user/get";

	/**
	 * 获取部门信息
	 */
	public static final String URL_GET_DEPARTMENT = "https://oapi.dingtalk.com/department/get";

	/**
	 * 文件上传
	 */
	public static final String URL_FILE = "https://oapi.dingtalk.com/media/upload";

	/**
	 *获取部门用户详情的接口url
	 */
	public static final String URL_GET_DEPARTMENT_USER = "https://oapi.dingtalk.com/user/list";

	/**
	 *获取获取子部门ID列表的接口url
	 */
	public static final String URL_GET_DEPARTMENT_ID_LIST = "https://oapi.dingtalk.com/department/list_ids";

	/**
	 *获取获取部门列表的接口url
	 */
	public static final String URL_GET_DEPARTMENT_LIST = "https://oapi.dingtalk.com/department/list";

	/**
	 *获取获取部门详情的接口url
	 */
	public static final String URL_GET_DEPARTMENT_DETAIL = "https://oapi.dingtalk.com/department/get";

	/**
	 *获取企业员工人数的接口url
	 */
	public static final String URL_GET_ORG_USER_COUNT = "https://oapi.dingtalk.com/user/get_org_user_count";

	/**
	 *获取企业角色列表的接口url
	 */
	public static final String URL_GET_ROLE_LIST = "https://oapi.dingtalk.com/topapi/role/list";

	/**
	 *获取企业角色下的员工列表的接口url
	 */
	public static final String URL_GET_ROLE_SIMPLE_LIST = "https://oapi.dingtalk.com/department/get";

	/**
	 *获取外部联系人标签列表的接口url
	 */
	public static final String URL_GET_EXTCONTACT_LIST_LABEL_GRUOPS = "https://oapi.dingtalk.com/topapi/extcontact/listlabelgroups";

	/**
	 *获取外部联系人列表的接口url
	 */
	public static final String URL_GET_EXTCONTACT_LIST = "https://oapi.dingtalk.com/topapi/extcontact/list";

	/**
	 *获取外部联系人详情的接口url
	 */
	public static final String URL_GET_EXTCONTACT_USER = "https://oapi.dingtalk.com/topapi/extcontact/get";

	/**
	 *发送消息的接口url
	 */
	public static final String URL_SEND_MESSAGE = "https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2";

	/**
	 *发起审批实例的接口url
	 */
	public static final String URL_PROCESSINSTANCE_CREATE = "https://oapi.dingtalk.com/topapi/processinstance/create";

	/**
	 *获取审批实例Id的接口url
	 */
	public static final String URL_GET_PROCESSINSTANCE_LIST_IDS = "https://oapi.dingtalk.com/topapi/processinstance/listids";

	/**
	 *获取单个审批实例的接口url
	 */
	public static final String URL_GET_PROCESSINSTANCE = "https://oapi.dingtalk.com/topapi/processinstance/get";

	/**
	 *获取用户待审批数量的接口url
	 */
	public static final String URL_GET_PROCESSINSTANCE_TO_DO_NUM = "https://oapi.dingtalk.com/topapi/process/gettodonum";

	/**
	 *获取企业考勤排班详情的接口url
	 */
	public static final String URL_GET_ATTENDANCE_LIST = "https://oapi.dingtalk.com/topapi/attendance/listschedule";

	/**
	 *获取企业考勤组详情的接口url
	 */
	public static final String URL_GET_ATTENDANCE_SIMPLE_GRUOP = "https://oapi.dingtalk.com/topapi/attendance/getsimplegroups";

	/**
	 *获取打卡详情的接口url
	 */
	public static final String URL_GET_ATTENDANCE_LIST_RECORD = "https://oapi.dingtalk.com/attendance/listRecord";

	/**
	 *获取打卡结果的接口url
	 */
	public static final String URL_GET_ATTENDANCE_RESULT = "https://oapi.dingtalk.com/attendance/list";

	/**
	 *获取用户请假时长的接口url
	 */
	public static final String URL_GET_LEAVE_TIME = "https://oapi.dingtalk.com/topapi/attendance/getleaveapproveduration";

	/**
	 *获取用户考勤组的接口url
	 */
	public static final String URL_GET_USER_ATTENDANCE_GRUOP = "https://oapi.dingtalk.com/topapi/attendance/getusergroup";

	/**
	 *获取用户日志数据的接口url
	 */
	public static final String URL_GET_USER_REPORT_LIST = "https://oapi.dingtalk.com/topapi/report/list";

	/**
	 *获取用户日志未读数的接口url
	 */
	public static final String URL_GET_USER_REPORT_UNREAD_COUNT = "https://oapi.dingtalk.com/topapi/report/getunreadcount";

	/**
	 *上传文件的接口url
	 */
	public static final String URL_UPLOAD_FILE = "https://oapi.dingtalk.com/media/upload";

	/**
	 *下载文件的接口url
	 */
	public static final String URL_DOWNLOAD_FILE = "https://oapi.dingtalk.com/media/downloadFile";

	/**
	 *获取部门用户userid列表
	 */
	public static final String URL_DEPT_MEMBER = "https://oapi.dingtalk.com/user/getDeptMember";

	/*
	 * 获取get_jsapi_ticket
	 */
	public static final String URL_JSAPI_TICKET = "https://oapi.dingtalk.com/get_jsapi_ticket";

	/*
	 * 获取部门用户
	 **/
	public static final String URL_USER_SIMPLELIST = "https://oapi.dingtalk.com/user/simplelist";

	/*
	 * 创建用户
	 **/
	public static final String URL_USER_CREATE = "https://oapi.dingtalk.com/user/create";

	/*
	 * 用户花名册
	 */
	public static final String URL_USER_ROSTER = "https://oapi.dingtalk.com/topapi/smartwork/hrm/employee/list";
}
