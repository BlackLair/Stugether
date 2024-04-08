package com.kuwon.stugether.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {
	
	
	@GetMapping("/join-page")
	public String joinView() {
		return "user/join-page";
	}
}
