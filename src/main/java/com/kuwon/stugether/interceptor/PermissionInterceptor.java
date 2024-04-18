package com.kuwon.stugether.interceptor;

import java.io.IOException;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PermissionInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException{
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		String uri = request.getRequestURI();
		if(userId != null) {  // 로그인 시
			if(uri.startsWith("/user")) {
				response.sendRedirect("/blog/home-page");
				return false;
			}
		}else {  // 비 로그인 시
			if(uri.startsWith("/blog") || uri.startsWith("/group") || uri.startsWith("/problem-bank")
					|| uri.startsWith("/question") || uri.startsWith("/account")) {
				response.sendRedirect("/user/login-page");
				return false;
			}
		}
		return true;
	}

}
