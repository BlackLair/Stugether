package com.kuwon.stugether.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kuwon.stugether.blog.domain.BlogCategory;
import com.kuwon.stugether.blog.repository.BlogRepository;
import com.kuwon.stugether.common.FileManager;

@Service
public class BlogService {
	@Autowired
	BlogRepository blogRepository;
	
	public String uploadTempImage(MultipartFile file, int userId, String editorToken) {
		return FileManager.saveTempFile(file, userId, editorToken);
	}
	
	public void deleteTempImage(String fileName, int userId, String editorToken) {
		FileManager.deleteTempImage(fileName, userId, editorToken);
	}
	
	public List<BlogCategory> getBlogCategoryList(int userId){
		return blogRepository.selectBlogCategoryList(userId); 
	}
	

	public String addPost(int categoryId, String title, String content, int userId, String editorToken) {
		// 카테고리 존재 유무 확인
		if(blogRepository.selectBlogCategory(userId, categoryId) == null) {
			return "category not exist";
		}
		
		// 글에 포함된 image 태그의 src 주소 변경
		String currentTime = System.currentTimeMillis() + "";
		content = content.replaceAll("/temp/" + userId + "_" + editorToken, "/blog/" + userId + "_" + currentTime);
		
		// 글 정보를 DB에 저장
		if(blogRepository.insertPost(userId, categoryId, title, content) == 0) {
			return "insert failure";
		}
		
		// 글에 포함된 이미지 파일들을 임시 폴더에서 보관 폴더로 이동
		FileManager.saveImage(userId, FileManager.TYPE_BLOG, currentTime, editorToken);
		// 결과 반환
		return "success";
	}
}
