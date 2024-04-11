package com.kuwon.stugether.blog;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kuwon.stugether.blog.service.BlogService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/blog")
@RestController
public class BlogRestController {
	@Autowired
	BlogService blogService;
	
	// 블로그 에디터 삽입 이미지 임시 저장
	@PostMapping("/upload-image")
	public Map<String, String> uploadImage(@RequestParam("file") MultipartFile file
										, HttpSession session){
		int userId = (int)session.getAttribute("userId");
		Map<String, String> resultMap = new HashMap<>();
		String path = blogService.uploadTempImage(file, userId);
		if(path != null) {
			resultMap.put("responseCode", "success");
			resultMap.put("url", path);
		}else {
			resultMap.put("responseCode", "error");
		}
		return resultMap;
	}
	
	// 블로그 에디터 이미지 제거
	@DeleteMapping("/delete-image")
	public void deleteImage(@RequestParam("file") String fileName
							, HttpSession session) {
		int userId = (int) session.getAttribute("userId");
		blogService.deleteTempImage(fileName, userId);
	}
	
	@PostMapping("/upload-post")
	public Map<String, String> uploadPost(@RequestParam("categoryId") int cotegoryId
							, @RequestParam("title") String title
							, @RequestParam("content") String content
							, HttpSession session){
		int userId = (int)session.getAttribute("userId");
		Map<String, String> resultMap = new HashMap<>();
		String result = blogService.addPost(cotegoryId, title, content, userId);
		resultMap.put("result", result);
		return resultMap;
	}
}
