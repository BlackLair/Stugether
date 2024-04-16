package com.kuwon.stugether.blog;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kuwon.stugether.blog.service.BlogReplyService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/blog/reply")
@RestController
public class BlogReplyRestController {
	@Autowired
	BlogReplyService blogReplyService;
	
	@PostMapping("/upload")
	public Map<String, String> uploadReply(@RequestParam("postId") int postId
										, @RequestParam("content") String content
										, HttpSession session){
		int userId = (int)session.getAttribute("userId");
		Map<String, String> resultMap = new HashMap<>();
		String result = blogReplyService.addReply(postId, userId, content);
		resultMap.put("result", result);
		return resultMap;
	}
	
	@DeleteMapping("/remove")
	public Map<String, String> removeReply(@RequestParam("replyId") int replyId
										, HttpSession session){
		int userId = (int)session.getAttribute("userId");
		Map<String, String> resultMap = new HashMap<>();
		String result = blogReplyService.removeReply(replyId, userId);
		resultMap.put("result", result);
		return resultMap;
	}
}
