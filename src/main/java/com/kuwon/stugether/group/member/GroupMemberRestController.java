package com.kuwon.stugether.group.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kuwon.stugether.group.member.service.GroupMemberService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/group")
public class GroupMemberRestController {
	@Autowired
	GroupMemberService groupMemberService;
	
	@PostMapping("/join")
	public Map<String, String> joinGroup(@RequestParam("groupId") int groupId
										, HttpSession session){
		int userId = (int) session.getAttribute("userId");
		String result = groupMemberService.joinGroup(groupId, userId);
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("result", result);
		return resultMap;
	}
	
	@DeleteMapping("leave")
	public Map<String, String> leaveGroup(@RequestParam("groupId") int groupId
										, @RequestParam(value="assignee", required=false) String assignee
										, HttpSession session){
		Map<String, String> resultMap = new HashMap<>();
		int userId = (int) session.getAttribute("userId");
		String result = groupMemberService.leaveGroup(groupId, userId, assignee);
		resultMap.put("result", result);
		return resultMap;
	}
}
