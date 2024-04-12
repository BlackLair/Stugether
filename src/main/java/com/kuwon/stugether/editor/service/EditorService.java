package com.kuwon.stugether.editor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kuwon.stugether.blog.repository.BlogRepository;
import com.kuwon.stugether.common.FileManager;

@Service
public class EditorService {
	@Autowired
	BlogRepository blogRepository;
	// 이미지 임시 업로드
	public String uploadTempImage(MultipartFile file, int userId, String editorToken) {
		return FileManager.saveTempFile(file, userId, editorToken);
	}
	
	// 임시 이미지 삭제
	public void deleteTempImage(String fileName, int userId, String editorToken) {
		FileManager.deleteTempImage(fileName, userId, editorToken);
	}
}
