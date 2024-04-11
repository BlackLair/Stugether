package com.kuwon.stugether.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kuwon.stugether.blog.domain.BlogCategory;
import com.kuwon.stugether.blog.service.BlogService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/blog")
@Controller
public class BlogController {
	@Autowired
	BlogService blogService;
	
	@GetMapping("/my-page")
	public String myPageView(HttpSession session) {
		int userId = (int)session.getAttribute("userId");
		return "redirect:/blog/list-page?userId=" + userId;
	}
	
	@GetMapping("/list-page")
	public String blogListView(@RequestParam("userId") int userId
							, @RequestParam(value="category", required=false) Integer category
							, @RequestParam(value="page", required=false) Integer page) {
		if(page == null) page = 1;
		return "blog/list-page";
	}
	
	@GetMapping("/upload-page")
	public String uploadView(HttpSession session
							, Model model) {
		int userId = (int)session.getAttribute("userId");
		List<BlogCategory> categoryList = blogService.getBlogCategoryList(userId);
		model.addAttribute("categoryList", categoryList);
		return "blog/upload-post-page";
	}
}
