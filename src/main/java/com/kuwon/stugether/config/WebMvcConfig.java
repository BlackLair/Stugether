package com.kuwon.stugether.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kuwon.stugether.common.FileManager;
import com.kuwon.stugether.interceptor.PermissionInterceptor;



@Configuration // 서버 기동 시 설정
public class WebMvcConfig implements WebMvcConfigurer{
	
	
	// /images/로 시작하는 리소스 요청 시 클라이언트에게 반환할 리소스를 찾을 실제 경로 등록
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**")
		.addResourceLocations("file://" + FileManager.FILE_UPLOAD_PATH + "/");
	}
	
	// 각 페이지 접근 권할 설정
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(permissionInterceptor())
		.addPathPatterns("/**")
		.excludePathPatterns("/user/logout", "/image/**", "/static/**")
		.excludePathPatterns("/user/duplicated-nickname"); // 계정 정보 수정 시 사용됨
		
	}
	
	@Bean
	public PermissionInterceptor permissionInterceptor() {
		return new PermissionInterceptor();
	}
}