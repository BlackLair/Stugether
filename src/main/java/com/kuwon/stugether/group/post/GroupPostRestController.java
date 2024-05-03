package com.kuwon.stugether.group.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kuwon.stugether.group.post.domain.GroupPost;
import com.kuwon.stugether.group.post.service.GroupPostService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/group")
public class GroupPostRestController {
	@Autowired
	GroupPostService groupPostService;
	
	// 그룹 게시물 작성
	@PostMapping("/upload-post")
	public Map<String, Object> uploadPost(@RequestParam("groupId") int groupId
										, @RequestParam("categoryId") int categoryId
										, @RequestParam("title") String title
										, @RequestParam("content") String content
										, @RequestParam("editorToken") String editorToken
										, HttpSession session){
		int userId = (int) session.getAttribute("userId");
		Map<String, Object> resultMap = new HashMap<>();
		GroupPost post = new GroupPost();
		String result = groupPostService.uploadGroupPost(userId, groupId, categoryId, title, content, editorToken, post);
		resultMap.put("result", result);
		resultMap.put("postId", post.getId());
		
		return resultMap;
	}
	
	@DeleteMapping("/remove-post")
	public Map<String, String> removePost(@RequestParam("groupId") int groupId
										, @RequestParam("postId") int postId
										, HttpSession session){
		int userId = (int) session.getAttribute("userId");
		String result = groupPostService.removeGroupPost(userId, groupId, postId);
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("result", result);
		return resultMap;
		
	}
	
}
