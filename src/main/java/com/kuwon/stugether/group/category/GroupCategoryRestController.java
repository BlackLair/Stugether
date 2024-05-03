package com.kuwon.stugether.group.category;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kuwon.stugether.group.category.service.GroupCategoryService;

@RestController
@RequestMapping("/group/category")
public class GroupCategoryRestController {
	@Autowired
	GroupCategoryService groupCategoryService;
	
	// 그룹 게시판 카테고리 추가
	@PostMapping("/add")
	public Map<String, String> addCategory(@RequestParam("groupId") int groupId
										, @RequestParam("name") String name){
		String result = groupCategoryService.addCategory(groupId, name);
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("result",  result);
		return resultMap;
	}
	
	// 그룹 게시판 카테고리 제거
	@DeleteMapping("/remove")
	public Map<String, String> removeCategory(@RequestParam("groupId") int groupId
											, @RequestParam("categoryId") int categoryId){
		Map<String, String> resultMap = new HashMap<>();
		String result = groupCategoryService.removeCategory(groupId, categoryId);
		resultMap.put("result", result);
		return resultMap;
	}
}
