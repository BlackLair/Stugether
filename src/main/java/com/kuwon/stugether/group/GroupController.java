package com.kuwon.stugether.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kuwon.stugether.group.category.dto.GroupCategoryInfo;
import com.kuwon.stugether.group.category.service.GroupCategoryService;
import com.kuwon.stugether.group.common.dto.GroupInfo;
import com.kuwon.stugether.group.common.service.GroupService;
import com.kuwon.stugether.group.post.dto.GroupPostInfo;
import com.kuwon.stugether.group.post.service.GroupPostService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/group")
@Controller
public class GroupController {
	@Autowired
	GroupService groupService;
	@Autowired
	GroupCategoryService groupCategoryService;
	@Autowired
	GroupPostService groupPostService;
	
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
	
	@GetMapping("/{groupId}/list-page")
	public String listView(@PathVariable("groupId") int groupId
						, @RequestParam(value="category", required=false) Integer category
						, @RequestParam(value="page", defaultValue="1") Integer page
						, HttpSession session, Model model) {
		int userId = (int) session.getAttribute("userId");
		
		GroupInfo groupInfo = groupService.getGroupInfoByGroupId(groupId);
		List<GroupCategoryInfo> groupCategoryInfoList = groupCategoryService.getCategoryInfoList(groupId);
		List<GroupPostInfo> groupPostInfoList = groupPostService.getGroupPostInfoList(groupId, category, page);
		int totalPostCount = groupCategoryService.getTotalPostCountByGroupCategoryInfoList(groupCategoryInfoList);
		int currentPostCount = totalPostCount;
		if(category == null) {
			model.addAttribute("categoryName", "전체 글");
		}else {
			model.addAttribute("categoryName", groupCategoryService.getCategoryName(category));
			currentPostCount = groupCategoryService.getCurrentCategoryPostCount(category, groupCategoryInfoList);
			model.addAttribute("currentPostCount", currentPostCount);
		}
		model.addAttribute("groupInfo", groupInfo);
		model.addAttribute("groupPostInfoList", groupPostInfoList);
		model.addAttribute("groupCategoryInfoList", groupCategoryInfoList);
		model.addAttribute("totalPostCount", totalPostCount);
		model.addAttribute("pageCount", (Math.max(0,  currentPostCount - 1) / 10) + 1);
		
		return "group/list-page";
	}
}
