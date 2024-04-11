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
	
	@DeleteMapping("/delete-image")
	public void deleteImage(@RequestParam("file") String fileName
							, HttpSession session) {
		int userId = (int) session.getAttribute("userId");
		blogService.deleteTempImage(fileName, userId);
	}
}
