package com.ljq.assets.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ljq.assets.util.jwtUtil;

@Component
public class LoginInterceptor implements HandlerInterceptor {

	private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

	@SuppressWarnings("unused")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestUrl = request.getRequestURI();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String token = request.getHeader("accessToken");
		// token不存在
		if (null != token) {
			boolean result = jwtUtil.verify(token);
			if (result) {
				System.out.println("-----------成功--------");
				return true;
			}
		}
		PrintWriter printWriter = response.getWriter();
		printWriter.write("你没有权限，请联系管理员开通");
		log.info("------------没有权限，拦截了------------");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("postHandle被调用");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("afterCompletion被调用");
	}

}
