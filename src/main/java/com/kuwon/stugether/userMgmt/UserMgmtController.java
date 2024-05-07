package com.kuwon.stugether.userMgmt;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kuwon.stugether.user.dto.UserDTO;
import com.kuwon.stugether.user.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/account")
public class UserMgmtController {
	@Autowired
	UserService userService;
	
	@GetMapping("/config-page")
	public String configView(HttpSession session, Model model) {
		int userId = (int) session.getAttribute("userId");
		UserDTO user = userService.getUser(userId);
		
		long editExpireTime = (long)session.getAttribute("editExpireTime");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String expireTimeStr = dateFormat.format(editExpireTime);
		
		model.addAttribute("expireTimeStr", expireTimeStr);
		model.addAttribute("email", user.getEmail());
		model.addAttribute("loginId", user.getLoginId());
		return "/userMgmt/config-page";
	}
	
	@GetMapping("/authentication-page")
	public String authenticationView(HttpSession session) {
		
		return "/userMgmt/authentication-page";
	}
	
	@GetMapping("/auth-alert-page")
	public String authAlertView() {
		return "/userMgmt/auth-alert-page";
	}
}
