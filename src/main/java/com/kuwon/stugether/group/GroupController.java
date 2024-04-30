package com.kuwon.stugether.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kuwon.stugether.group.common.dto.GroupInfo;
import com.kuwon.stugether.group.common.service.GroupService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/group")
@Controller
public class GroupController {
	@Autowired
	GroupService groupService;
	
	@GetMapping("/search-group-page")
	public String searchGroupView(@RequestParam(value="search", defaultValue="") String search
								, @RequestParam(value="page", defaultValue="1") Integer page
								, HttpSession session, Model model) {
		int userId = (int)session.getAttribute("userId");
		List<GroupInfo> groupInfoList = groupService.getJoinedGroupInfoList(userId);
		List<GroupInfo> searchGroupInfoList = groupService.getSearchedGroupInfoList(search, page);
		int searchCount = groupService.getSearchedGroupCount(search);
		model.addAttribute("pageCount", (Math.max(0, searchCount - 1) / 5) + 1);
		model.addAttribute("searchGroupInfoList", searchGroupInfoList);
		model.addAttribute("groupInfoList", groupInfoList);
		model.addAttribute("search", search);
		return "group/search-group-page";
	}
	@GetMapping("/create-group-page")
	public String createGroupView() {
		return "group/create-group-page";
	}
}
