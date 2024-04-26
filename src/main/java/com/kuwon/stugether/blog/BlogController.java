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
	
	@GetMapping("/home-page")
	public String myPageView(HttpSession session) {
		int userId = (int)session.getAttribute("userId");
		return "redirect:/blog/list-page?userId=" + userId; // 추후 변경 예정
	}
	
	// 블로그 게시글 목록 페이지
	@GetMapping("/list-page")
	public String blogListView(@RequestParam("userId") int ownerId
							, @RequestParam(value="category", required=false) Integer category
							, @RequestParam(value="page", required=false) Integer page
							, Model model
							, HttpSession session) {
		if(page == null) page = 1; // 페이지 정보가 없으면 1페이지 글 목록 가져오기
		// 추후 회원 존재 여부 확인 기능 추가
		UserDTO ownerDTO = userService.getUser(ownerId); // 방문한 블로그 주인의 정보
		int userId = (int)session.getAttribute("userId");
		
		// 방문한 블로그의 카테고리 정보 가져오기
		BlogCategoryDTO blogCategoryDTO = categoryService.getBlogCategoryList(ownerId);
		
		// 요청한 카테고리 정보 가져오기
		List<BlogPostInfo> blogPostInfoList = blogService.getBlogPostList(ownerId, category, page);
		
		// 현재 보고 있는 카테고리의 글 개수에 따라 페이지 개수 설정, 현재 카테고리 이름 model에 등록
		if(category != null) { // 요청에 category 조건이 존재하는 경우
			String categoryName = categoryService.getBlogCategoryName(category);
			int pages = categoryService.getBlogCategoryPostCount(ownerId, category) / 10 + 1;
			blogCategoryDTO.setCurrentPages(pages);
			model.addAttribute("categoryName", categoryName);
		}else { // 요청에 category 조건이 존재하지 않는 경우 (전체 글 요청)
			int pages = (Math.max(0, blogCategoryDTO.getAllPostCount() - 1) / 10) + 1;
			blogCategoryDTO.setCurrentPages(pages);
			model.addAttribute("categoryName", "전체 글");
		}
		
		blogCategoryDTO.setCurrentCategoryId(category); // 현재 보고 있는 카테고리의 id 저장
		
		// model에 데이터 등록
		model.addAttribute("categoryDTO", blogCategoryDTO);
		model.addAttribute("ownerDTO", ownerDTO);
		model.addAttribute("postList", blogPostInfoList);
		
		// model에 블로그 메모 데이터 등록
		if(ownerId == userId) {
			List<BlogMemo> blogMemoList = blogMemoService.getBlogMemoList(userId);
			model.addAttribute("blogMemoList", blogMemoList);
		}
		return "blog/list-page";
	}
	
	// 게시물 작성 페이지
	@GetMapping("/upload-page")
	public String uploadView(HttpSession session
							, Model model) {
		int userId = (int)session.getAttribute("userId");
		// 작성자 블로그의 카테고리 목록
		BlogCategoryDTO blogCategoryDTO = categoryService.getBlogCategoryList(userId);
		model.addAttribute("categoryDTO", blogCategoryDTO);
		return "blog/upload-post-page";
	}
	
	// 게시글 상세 내용 페이지
	@GetMapping("/post-detail-page")
	public String postDetailView(@RequestParam("postId") int postId
								, Model model
								, HttpSession session) {
		//  게시글 정보 가져오기
		BlogPostInfo blogPost = blogService.getBlogPost(postId);
		int ownerId = blogPost.getUserId(); // 방문한 게시글 작성자 id
		UserDTO ownerDTO = userService.getUser(ownerId); // 게시글 작성자 주인의 정보
		
		// 방문한 블로그 카테고리 정보 가져오기
		BlogCategoryDTO blogCategoryDTO = categoryService.getBlogCategoryList(ownerId);
		// 방문한 게시글의 댓글 목록 가져오기
		List<BlogReplyDTO> replyDTOList = blogReplyService.getReplyList(postId);
		
		// model에 데이터 등록
		model.addAttribute("blogPost", blogPost);
		model.addAttribute("categoryDTO", blogCategoryDTO);
		model.addAttribute("replyDTOList", replyDTOList);
		model.addAttribute("ownerDTO", ownerDTO);
		
		// model에 블로그 메모 데이터 등록
		int userId = (int)session.getAttribute("userId");
		if(ownerId == userId) {
			List<BlogMemo> blogMemoList = blogMemoService.getBlogMemoList(userId);
			model.addAttribute("blogMemoList", blogMemoList);
		}
		return "blog/post-detail-page";
	}
}
