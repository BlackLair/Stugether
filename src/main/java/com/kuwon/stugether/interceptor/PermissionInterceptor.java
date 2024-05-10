package com.kuwon.stugether.interceptor;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import com.kuwon.stugether.group.common.dto.GroupInfo;
import com.kuwon.stugether.group.common.service.GroupService;
import com.kuwon.stugether.group.member.repository.GroupMemberRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PermissionInterceptor implements HandlerInterceptor {
	@Autowired
	GroupMemberRepository groupMemberRepository;
	@Autowired
	GroupService groupService;
	
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
			if(uri.startsWith("/account") && !uri.startsWith("/account/auth")) {
				Long editExpireTime = (Long) session.getAttribute("editExpireTime");
				if(editExpireTime == null || editExpireTime < System.currentTimeMillis()) {
					response.sendRedirect("/account/auth-alert-page");
					return false;
				}	
			}
			if(uri.startsWith("/group")) {
				final Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
				String groupIdStr = pathVariables.get("groupId");
				if(groupIdStr != null) {
					final Integer groupId = Integer.valueOf(pathVariables.get("groupId"));
					if(groupId != null && groupMemberRepository.selectExistMember(userId, groupId) == 0) {
						response.sendRedirect("/group/no-permission-page");
						return false;
					}
				}
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
