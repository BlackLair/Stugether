package com.kuwon.stugether.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kuwon.stugether.user.domain.User;
import com.kuwon.stugether.user.service.UserService;

import jakarta.servlet.http.HttpSession;


@RequestMapping("/user")
@RestController
public class UserRestController {
	@Autowired
	UserService userService;
	
	@GetMapping("/duplicated-id") // 중복 아이디 존재 여부 확인
	public Map<String, Object> checkDuplicatedId(@RequestParam("loginId") String loginId){
		boolean result = userService.checkDuplicatedId(loginId);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("duplicated", result);
		return resultMap;
	}
	
	@GetMapping("/duplicated-nickname") // 중복 닉네임 존재 여부 확인
	public Map<String, Object> checkDuplicatedNickname(@RequestParam("nickname") String nickname){
		boolean result = userService.checkDuplicatedNickname(nickname);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("duplicated", result);
		return resultMap;
	}
	
	@PostMapping("/join") // 회원가입 요청
	public Map<String, String> join(@RequestParam("loginId") String loginId
									, @RequestParam("password") String password
									, @RequestParam("nickname") String nickname
									, @RequestParam("email") String email){
		
		int count = userService.join(loginId, password, nickname, email);
		Map<String, String> resultMap = new HashMap<>();
		if(count == 1) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "failure");
		}
		return resultMap;
	}
	
	@PostMapping("/login") // 로그인 요청
	public Map<String, String> login(@RequestParam("loginId") String loginId
									, @RequestParam("password") String password
									, HttpSession session){
		User user = userService.getUser(loginId, password);
		Map<String, String> resultMap = new HashMap<>();
		if(user != null) {
			resultMap.put("result", "success");
			session.setAttribute("userId", user.getId());
			session.setAttribute("nickname", user.getNickname());
		}else {
			resultMap.put("result", "failure");
		}
		return resultMap;
	}
}
