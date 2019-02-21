package com.ljq.assets.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiServiceGetCorpTokenRequest;
import com.dingtalk.api.response.OapiServiceGetCorpTokenResponse;
import com.ljq.assets.config.Constant;
import com.ljq.assets.config.URLConstant;
import com.taobao.api.ApiException;

/**
 * 获取access_token工具类
 */
public class AccessTokenUtil {
	private static final Logger bizLogger = LoggerFactory.getLogger(AccessTokenUtil.class);

	public static String getToken(String corpid) throws RuntimeException {
		try {
			DefaultDingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_ENTERPRISE_AUTHORIZATION);
			OapiServiceGetCorpTokenRequest req = new OapiServiceGetCorpTokenRequest();
			req.setAuthCorpid(corpid);
			OapiServiceGetCorpTokenResponse execute = client.execute(req, Constant.SUITE_KEY, Constant.SUITE_SECRET,
					"");
			String accessToken = execute.getAccessToken();
			return accessToken;
		} catch (ApiException e) {
			bizLogger.error("getAccessToken failed", e);
			throw new RuntimeException();
		}

	}

}
