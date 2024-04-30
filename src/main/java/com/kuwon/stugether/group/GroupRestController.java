package com.kuwon.stugether.group;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.kuwon.stugether.group.common.domain.Group;
import com.kuwon.stugether.group.common.service.GroupService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/group")
public class GroupRestController {
	@Autowired
	GroupService groupService;
	
	@GetMapping("/name-duplicated")
	public Map<String, Boolean> checkDuplicatedGroupName(@RequestParam("groupName") String groupName){
		Map<String, Boolean> resultMap = new HashMap<>();
		resultMap.put("duplicated", groupService.checkDuplicatedGroupName(groupName));
		return resultMap;
	}
	
	@PostMapping("/create")
	public Map<String, Object> createGroup(@RequestParam("groupName") String groupName
										, @RequestParam("description") String description
										, @RequestParam(value="categoryList[]", required=false) String[] categoryList
										, HttpSession session){
		int userId = (int) session.getAttribute("userId");
		Map<String, Object> resultMap = new HashMap<>();
		Group group = new Group();
		group.setGroupName(HtmlUtils.htmlEscape(groupName));
		group.setDescription(HtmlUtils.htmlEscape(description));
		group.setUserId(userId);
		if(categoryList == null) {
			categoryList = new String[0];
		}
		String result = groupService.createGroup(group, categoryList);
		resultMap.put("result", result);
		if(result.equals("success")) {
			resultMap.put("groupId", group.getId());
		}
		return resultMap;
	}
}
