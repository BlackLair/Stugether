package com.kuwon.stugether.group.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
}
