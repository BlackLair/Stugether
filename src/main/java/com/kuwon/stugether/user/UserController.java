package com.kuwon.stugether.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@Controller
public class UserController {
	
	
	@GetMapping("/join-page")
	public String joinView() {
		return "user/join-page";
	}
	
	@GetMapping("/login-page")
	public String loginView() {
		return "user/login-page";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/user/login-page";
	}
}
