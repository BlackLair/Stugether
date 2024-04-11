package com.kuwon.stugether.blog.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kuwon.stugether.common.FileManager;

@Service
public class BlogService {
	public String uploadTempImage(MultipartFile file, int userId) {
		return FileManager.saveTempFile(file, userId);
	}
	
	public void deleteTempImage(String fileName, int userId) {
		FileManager.deleteTempImage(fileName, userId);
	}
}
