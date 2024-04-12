package com.kuwon.stugether.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kuwon.stugether.blog.dto.BlogCategoryDTO;
import com.kuwon.stugether.blog.dto.BlogPostInfo;
import com.kuwon.stugether.blog.service.BlogService;
import com.kuwon.stugether.user.dto.UserDTO;
import com.kuwon.stugether.user.service.UserService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/blog")
@Controller
public class BlogController {
	@Autowired
	BlogService blogService;
	@Autowired
	UserService userService;
	
	@GetMapping("/my-page")
	public String myPageView(HttpSession session) {
		int userId = (int)session.getAttribute("userId");
		return "redirect:/blog/list-page?userId=" + userId;
	}
	
	@GetMapping("/list-page")
	public String blogListView(@RequestParam("userId") int ownerId
							, @RequestParam(value="category", required=false) Integer category
							, @RequestParam(value="page", required=false) Integer page
							, Model model) {
		if(page == null) page = 1;
		// 추후 회원 존재 여부 확인 기능 추가
		UserDTO ownerDTO = userService.getUser(ownerId);
		BlogCategoryDTO blogCategoryDTO = blogService.getBlogCategoryList(ownerId);
		model.addAttribute("categoryDTO", blogCategoryDTO);
		model.addAttribute("ownerDTO", ownerDTO);
		List<BlogPostInfo> blogPostInfoList = blogService.getBlogPostList(ownerId, category, page);
		model.addAttribute("postList", blogPostInfoList);
		return "blog/list-page";
	}
	
	@GetMapping("/upload-page")
	public String uploadView(HttpSession session
							, Model model) {
		int userId = (int)session.getAttribute("userId");
		BlogCategoryDTO blogCategoryDTO = blogService.getBlogCategoryList(userId);
		model.addAttribute("categoryDTO", blogCategoryDTO);
		return "blog/upload-post-page";
	}
}
