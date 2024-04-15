package com.kuwon.stugether.blog;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kuwon.stugether.blog.service.CategoryService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/blog/category")
@RestController
public class CategoryRestController {
	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/add")
	public Map<String, String> addCategory(@RequestParam("name") String name
										, HttpSession session){
		int userId = (int)session.getAttribute("userId");
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("result", categoryService.addCategory(userId, name));
		return resultMap;
	}
	
	@DeleteMapping("remove")
	public Map<String, String> removeCategory(@RequestParam("categoryId") int categoryId
											, HttpSession session){
		int userId = (int)session.getAttribute("userId");
		Map<String, String> resultMap = new HashMap<>();
		String result = categoryService.removeCategory(userId, categoryId);
		resultMap.put("result", result);
		return resultMap;
	}
	
}
