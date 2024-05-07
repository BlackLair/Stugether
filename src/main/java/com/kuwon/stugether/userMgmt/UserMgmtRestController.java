package com.kuwon.stugether.userMgmt;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kuwon.stugether.user.service.UserService;
import com.kuwon.stugether.userMgmt.service.UserMgmtService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/account")
public class UserMgmtRestController {
	@Autowired
	UserMgmtService userMgmtService;
	@Autowired
	UserService userService;
	
	@PatchMapping("/edit-account")
	public Map<String, String> editAccount(@RequestParam(value="password", required=false) String password
										, @RequestParam(value="email", required=false) String email
										, @RequestParam(value="nickname", required=false) String nickname
										, HttpSession session){
		int userId = (int) session.getAttribute("userId");
		Map<String, String> resultMap = new HashMap<>();
		String result = userMgmtService.editAccount(userId, password, email, nickname);
		resultMap.put("result", result);
		if(result.equals("success")) {
			if(password != null) {
				session.invalidate();
			}else if(nickname != null) {
				session.setAttribute("nickname", nickname);
			}
		}
		return resultMap;
	}
	
	@PostMapping("/auth")
	public Map<String, String> authPassword(@RequestParam("password") String password
										, HttpSession session){
		int userId = (int) session.getAttribute("userId");
		String result = userMgmtService.authPassword(userId, password);
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("result", result);
		if(result.equals("success")) {
			long editExpireTime = System.currentTimeMillis() + 300000; // 본인 인증 만료 시간 5분
			session.setAttribute("editExpireTime", editExpireTime);
		}
		return resultMap;
	}
}
