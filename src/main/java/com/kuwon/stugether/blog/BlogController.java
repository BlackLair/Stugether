package com.kuwon.stugether.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kuwon.stugether.blog.domain.BlogMemo;
import com.kuwon.stugether.blog.dto.BlogCategoryDTO;
import com.kuwon.stugether.blog.dto.BlogPostInfo;
import com.kuwon.stugether.blog.dto.BlogReplyDTO;
import com.kuwon.stugether.blog.service.BlogCategoryService;
import com.kuwon.stugether.blog.service.BlogMemoService;
import com.kuwon.stugether.blog.service.BlogReplyService;
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
	@Autowired
	BlogCategoryService categoryService;
	@Autowired
	BlogReplyService blogReplyService;
	@Autowired
	BlogMemoService blogMemoService;
	
	@GetMapping("/my-page")
	public String myPageView(HttpSession session) {
		int userId = (int)session.getAttribute("userId");
		return "redirect:/blog/list-page?userId=" + userId;
	}
	
	@GetMapping("/list-page")
	public String blogListView(@RequestParam("userId") int ownerId
							, @RequestParam(value="category", required=false) Integer category
							, @RequestParam(value="page", required=false) Integer page
							, Model model
							, HttpSession session) {
		if(page == null) page = 1;
		// 추후 회원 존재 여부 확인 기능 추가
		UserDTO ownerDTO = userService.getUser(ownerId);
		int userId = (int)session.getAttribute("userId");
		BlogCategoryDTO blogCategoryDTO = categoryService.getBlogCategoryList(ownerId);
		
		List<BlogPostInfo> blogPostInfoList = blogService.getBlogPostList(ownerId, category, page);
		if(category != null) {
			String categoryName = categoryService.getBlogCategoryName(category);
			int pages = categoryService.getBlogCategoryPostCount(ownerId, category) / 10 + 1;
			blogCategoryDTO.setCurrentPages(pages);
			model.addAttribute("categoryName", categoryName);
		}else {
			int pages = blogCategoryDTO.getAllPostCount() / 10 + 1;
			blogCategoryDTO.setCurrentPages(pages);
			model.addAttribute("categoryName", "전체 글");
		}
		blogCategoryDTO.setCurrentCategoryId(category);
		model.addAttribute("categoryDTO", blogCategoryDTO);
		model.addAttribute("ownerDTO", ownerDTO);
		model.addAttribute("postList", blogPostInfoList);
		if(ownerId == userId) {
			List<BlogMemo> blogMemoList = blogMemoService.getBlogMemoList(userId);
			model.addAttribute("blogMemoList", blogMemoList);
		}
		return "blog/list-page";
	}
	
	@GetMapping("/upload-page")
	public String uploadView(HttpSession session
							, Model model) {
		int userId = (int)session.getAttribute("userId");
		BlogCategoryDTO blogCategoryDTO = categoryService.getBlogCategoryList(userId);
		model.addAttribute("categoryDTO", blogCategoryDTO);
		return "blog/upload-post-page";
	}
	
	@GetMapping("/post-detail-page")
	public String postDetailView(@RequestParam("postId") int postId
								, Model model
								, HttpSession session) {
		BlogPostInfo blogPost = blogService.getBlogPost(postId);
		int ownerId = blogPost.getUserId();
		UserDTO ownerDTO = userService.getUser(ownerId);
		BlogCategoryDTO blogCategoryDTO = categoryService.getBlogCategoryList(ownerId);
		List<BlogReplyDTO> replyDTOList = blogReplyService.getReplyList(postId);
		
		model.addAttribute("blogPost", blogPost);
		model.addAttribute("categoryDTO", blogCategoryDTO);
		model.addAttribute("replyDTOList", replyDTOList);
		model.addAttribute("ownerDTO", ownerDTO);
		int userId = (int)session.getAttribute("userId");
		if(ownerId == userId) {
			List<BlogMemo> blogMemoList = blogMemoService.getBlogMemoList(userId);
			model.addAttribute("blogMemoList", blogMemoList);
		}
		return "blog/post-detail-page";
	}
}
