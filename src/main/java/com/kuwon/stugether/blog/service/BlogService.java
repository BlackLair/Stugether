package com.kuwon.stugether.blog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuwon.stugether.blog.domain.BlogCategory;
import com.kuwon.stugether.blog.domain.BlogPost;
import com.kuwon.stugether.blog.dto.BlogCategoryDTO;
import com.kuwon.stugether.blog.dto.BlogCategoryDetail;
import com.kuwon.stugether.blog.dto.BlogPostInfo;
import com.kuwon.stugether.blog.repository.BlogRepository;
import com.kuwon.stugether.common.FileManager;

@Service
public class BlogService {
	@Autowired
	BlogRepository blogRepository;

	// 블로그 카테고리 목록 로드
	public BlogCategoryDTO getBlogCategoryList(int userId){
		List<BlogCategory> blogCategoryList = blogRepository.selectBlogCategoryList(userId);
		List<BlogCategoryDetail> blogCategoryDetailList = new ArrayList<BlogCategoryDetail>();
		BlogCategoryDTO blogCategoryDTO = new BlogCategoryDTO();
		int allPostCount = 0;
		for(BlogCategory blogCategory : blogCategoryList) {
			BlogCategoryDetail blogCategoryDetail = new BlogCategoryDetail();
			blogCategoryDetail.setId(blogCategory.getId());
			blogCategoryDetail.setName(blogCategory.getName());
			blogCategoryDetail.setPostCount(blogRepository.selectPostCountByCategory(blogCategory.getUserId(), blogCategory.getId()));
			blogCategoryDetailList.add(blogCategoryDetail);
			allPostCount += blogCategoryDetail.getPostCount();
		}
		blogCategoryDTO.setBlogCategoryDetailList(blogCategoryDetailList);
		blogCategoryDTO.setAllPostCount(allPostCount);
		return blogCategoryDTO;
	}
	
	// 각 카테고리 게시글 개수
	
	
	//  블로그 글 목록 로드
	public List<BlogPostInfo> getBlogPostList(int userId, Integer categoryId, int page){
		List<BlogPostInfo> blogPostInfoList = new ArrayList<>();
		List<BlogPost> blogPostList;
		if(categoryId == null)
			blogPostList = blogRepository.selectAllBlogPost(userId, (page - 1) * 10);
		else {
			blogPostList = blogRepository.selectBlogPostByCategoryId(userId, categoryId, (page - 1) * 10);
		}
		for(BlogPost blogPost : blogPostList) {
			BlogPostInfo blogPostInfo = new BlogPostInfo();
			blogPostInfo.setId(blogPost.getId());
			blogPostInfo.setTitle(blogPost.getTitle());
			blogPostInfo.setUserId(blogPost.getUserId());
			blogPostInfo.setCreatedAt(blogPost.getCreatedAt());
			blogPostInfoList.add(blogPostInfo);
		}
		return blogPostInfoList;
	}
	
	// 게시글 등록 작업
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
