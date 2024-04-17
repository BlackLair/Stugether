package com.kuwon.stugether.blog;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kuwon.stugether.blog.service.BlogMemoService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/blog/memo")
@RestController
public class BlogMemoRestController {
	@Autowired
	BlogMemoService blogMemoService;
	
	@PutMapping("/sort")
	public Map<String, String> sortMemo(@RequestParam(value="memoIdList[]") int[] memoIdList
									, HttpSession session){
		int userId = (int) session.getAttribute("userId");
		Map<String, String> resultMap = new HashMap<>();
		if(blogMemoService.sortMemo(userId, memoIdList)) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "failure");
		}
		return resultMap;
	}
	
	@PostMapping("/upload")
	public Map<String, String> uploadMemo(@RequestParam("content") String content
										, HttpSession session){
		int userId = (int) session.getAttribute("userId");
		Map<String, String> resultMap = new HashMap<>();
		if(blogMemoService.addMemo(userId, content) == 1) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "failure");
		}
		return resultMap;
	}
	
	@DeleteMapping("/remove")
	public Map<String, String> removeMemo(@RequestParam("memoId") int memoId
										, HttpSession session){
		int userId = (int) session.getAttribute("userId");
		Map<String, String> resultMap = new HashMap<>();
		if(blogMemoService.removeMemo(memoId, userId) == 1) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "failure");
		}
		return resultMap;
	}
}
