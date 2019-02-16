package com.ljq.assets.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

public class jwtUtil {

	/*
	 * 过期时间15分钟
	 */
	private static final long EXPIRE_TIME = 15 * 60 * 1000;

	/*
	 * token私钥
	 */
	private static final String TOKEN_SECRET = "fc22444ca03e2c7ef8939f2b8e986c49";

	public static String sgin(String userName, String userId) {
		try {
			Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
			Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
			// 设置信息头
			Map<String, Object> header = new HashMap<>();
			header.put("typ", "JWT");
			header.put("alg", "HS256");
			return JWT.create().withHeader(header).withClaim("loginName", userName).withClaim("userId", userId)
					.withExpiresAt(date).sign(algorithm);
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * 校验token是否正确
	 */
	@SuppressWarnings("unused")
	public static boolean verify(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
			JWTVerifier verifier = JWT.require(algorithm).build();
			DecodedJWT jwt = verifier.verify(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * 获得token中的信息无需secret解密也能获得
	 */
	public static String getUserName(String token) {
		try {
			DecodedJWT jwt = JWT.decode(token);
			return jwt.getClaim("loginName").asString();
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * 获得登录用户ID
	 */
	public static String getUserId(String token) {
		try {
			DecodedJWT jwt = JWT.decode(token);
			return jwt.getClaim("userId").asString();
		} catch (Exception e) {
			return null;
		}
	}
}
