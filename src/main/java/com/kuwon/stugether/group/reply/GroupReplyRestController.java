package com.kuwon.stugether.group.reply;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kuwon.stugether.group.reply.service.GroupReplyService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/group/reply")
public class GroupReplyRestController {
	@Autowired
	GroupReplyService groupReplyService;
	
	// 그룹 게시글 댓글 작성
	@PostMapping("/upload")
	public Map<String, String> uploadReply(@RequestParam("groupId") int groupId
										, @RequestParam("postId") int postId
										, @RequestParam("content") String content
										, HttpSession session){
		int userId = (int) session.getAttribute("userId");
		String result = groupReplyService.addReply(groupId, userId, postId, content);
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("result", result);
		return resultMap;
	}
	
	// 그룹 게시글 댓글 삭제
	@DeleteMapping("/remove")
	public Map<String, String> removeReply(@RequestParam("replyId") int replyId
										, HttpSession session){
		int userId = (int) session.getAttribute("userId");
		String result = groupReplyService.removeReply(replyId, userId);
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("result", result);
		return resultMap;
	}
	
}
