package com.kuwon.stugether.blog;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kuwon.stugether.blog.service.BlogService;
import com.kuwon.stugether.common.FileManager;
import com.kuwon.stugether.editor.service.EditorService;

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
		Map<String, String> resultMap = new HashMap<>();
		String result = blogService.addPost(cotegoryId, title, content, userId, editorToken);
		resultMap.put("result", result);
		return resultMap;
	}
}
