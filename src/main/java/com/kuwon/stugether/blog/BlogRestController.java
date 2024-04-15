package com.kuwon.stugether.blog;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kuwon.stugether.blog.domain.BlogPost;
import com.kuwon.stugether.blog.dto.BlogPostInfo;
import com.kuwon.stugether.blog.service.BlogService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/blog")
@RestController
public class BlogRestController {
	@Autowired
	BlogService blogService;
	
	// 게시글 등록
	@PostMapping("/upload-post")
	public Map<String, Object> uploadPost(@RequestParam("categoryId") int categoryId
							, @RequestParam("editorToken") String editorToken
							, @RequestParam("title") String title
							, @RequestParam("content") String content
							, HttpSession session){
		int userId = (int)session.getAttribute("userId");
		Map<String, Object> resultMap = new HashMap<>();
		BlogPost post = new BlogPost();
		String result = blogService.addPost(categoryId, title, content, userId, editorToken, post);
		resultMap.put("result", result);
		resultMap.put("postId", post.getId());
		return resultMap;
	}
	
	// 게시물 삭제
	@DeleteMapping("/remove-post")
	public Map<String, String> removePost(@RequestParam("postId") int postId
										, HttpSession session){
		int userId = (int)session.getAttribute("userId");
		BlogPostInfo postInfo = blogService.getBlogPost(postId);
		String result = "";
		if(userId == postInfo.getUserId()) {
			result = blogService.removePost(postId);
		}
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("result", result);
		return resultMap;
	}
	
}
