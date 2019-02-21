package com.ljq.assets.config;

/**
 * 项目中的常量定义类
 */
public class Constant {

	/**
	 * 应用的SuiteKey，登录开发者后台，点击应用管理，进入应用详情可见
	 */
	public static final String SUITE_KEY = "suitexlpaun0frvn11wyz";
	/**
	 * 应用的SuiteSecret，登录开发者后台，点击应用管理，进入应用详情可见
	 */
	public static final String SUITE_SECRET = "MfTN17os9NkpE6IjCNl8lZt5wnwSOXZ8gJZiDdMI6tCY6Sx2c0EETXZlzlEdNdCZ";
	/**
	 * 回调URL加解密用。应用的数据加密密钥，登录开发者后台，点击应用管理，进入应用详情可见
	 */
	public static final String ENCODING_AES_KEY = "";
	/**
	 * 回调URL签名用。应用的签名Token, 登录开发者后台，点击应用管理，进入应用详情可见
	 */
	public static final String TOKEN = "";

	/**
	 * Message类型
	 */
	public static final String TEXT = "text";
	public static final String IMAGE = "image";
	public static final String FILE = "file";
	public static final String LINK = "link";
	public static final String MARKDOWN = "markdown";
	public static final String OA = "oa";
	public static final String ACTION_CARD = "action_card";

	/*
	 * 服务器文件储存地址
	 */
	public static final String SERVER_FILE_ADDRESS = "/src/main/resources/static/img/";

	/*
	 * 服务器访问img
	 */
	public static final String SERVER_FILE_IMG = "/img/";

	/*
	 * 图片显示所需地址前缀
	 */
	public static final String PICTURE_PREFIX = "http://tf.vaiwan.com";

	/*
	 * logo
	 */
	public static final String LOGO_ICON = "http://tf.vaiwan.com/img/icon/shenpi.png";

}
