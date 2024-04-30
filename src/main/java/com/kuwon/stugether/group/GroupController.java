package com.kuwon.stugether.group;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/group")
@Controller
public class GroupController {
	
	@GetMapping("/search-group-page")
	public String searchGroupView(@RequestParam(value="search", required=false) String search
								, @RequestParam(value="page", defaultValue="1") Integer page
								, HttpSession session, Model model) {
		
		
		return "group/search-group-page";
	}
	@GetMapping("/create-group-page")
	public String createGroupView() {
		return "group/create-group-page";
	}
}
