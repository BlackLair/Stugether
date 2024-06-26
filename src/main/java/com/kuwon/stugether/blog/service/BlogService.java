package com.kuwon.stugether.blog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.kuwon.stugether.blog.domain.BlogPost;
import com.kuwon.stugether.blog.dto.BlogPostInfo;
import com.kuwon.stugether.blog.repository.BlogCategoryRepository;
import com.kuwon.stugether.blog.repository.BlogReplyRepository;
import com.kuwon.stugether.blog.repository.BlogRepository;
import com.kuwon.stugether.common.FileManager;

@Service
public class BlogService {
	@Autowired
	BlogRepository blogRepository;
	@Autowired
	BlogCategoryRepository categoryRepository;
	@Autowired
	BlogReplyRepository blogReplyRepository;

	
	
	
	//  블로그 글 목록 로드
	public List<BlogPostInfo> getBlogPostList(int userId, Integer categoryId, int page){
		List<BlogPostInfo> blogPostInfoList = new ArrayList<>();
		List<BlogPost> blogPostList;
		if(categoryId == null)
			blogPostList = blogRepository.selectAllBlogPostLIMIT(userId, (page - 1) * 10);
		else {
			blogPostList = blogRepository.selectBlogPostByCategoryId(userId, categoryId, (page - 1) * 10);
		}
		for(BlogPost blogPost : blogPostList) {
			BlogPostInfo blogPostInfo = new BlogPostInfo();
			blogPostInfo.setId(blogPost.getId());
			blogPostInfo.setTitle(blogPost.getTitle());
			blogPostInfo.setUserId(blogPost.getUserId());
			blogPostInfo.setCreatedAt(blogPost.getCreatedAt());
			if(categoryId != null)
				blogPostInfo.setBlogCategoryId(categoryId);
			blogPostInfoList.add(blogPostInfo);
		}
		return blogPostInfoList;
	}
	
	// 블로그 게시글 1개 정보 로드
	public BlogPostInfo getBlogPost(int postId) {
		BlogPost post = blogRepository.selectBlogPostById(postId);
		if(post == null) return null;
		BlogPostInfo postInfo = new BlogPostInfo();
		postInfo.setId(postId);
		postInfo.setTitle(post.getTitle());
		postInfo.setUserId(post.getUserId());
		postInfo.setBlogCategoryId(post.getBlogCategoryId());
		postInfo.setCategoryName(categoryRepository.selectBlogCategoryNameById(postInfo.getBlogCategoryId()));
		postInfo.setContent(post.getContent());
		postInfo.setCreatedAt(post.getCreatedAt());
		return postInfo;
	}
	
	// 게시글 등록 작업
	public String addPost(int categoryId, String title, String content, int userId, String editorToken, BlogPost post) {
		// 카테고리 존재 유무 확인
		if(categoryRepository.selectBlogCategory(userId, categoryId) == null) {
			return "category not exist";
		}
		
		// 글에 포함된 image 태그의 src 주소 변경
		String currentTime = System.currentTimeMillis() + "";
		content = content.replaceAll("/temp/" + userId + "_" + editorToken, "/blog/" + userId + "_" + currentTime);
		
		
		// 글에 포함된 이미지 파일들을 임시 폴더에서 보관 폴더로 이동, 글 정보를 DB에 저장
		String filePath = FileManager.saveImage(userId, FileManager.TYPE_BLOG, currentTime, editorToken);
		post.setUserId(userId);
		post.setBlogCategoryId(categoryId);
		post.setTitle(HtmlUtils.htmlEscape(title));
		post.setContent(content);
		post.setImagePath(filePath);
		int count = blogRepository.insertPost(post);
		if( count == 0) {
			return "insert failure";
		}
		
		// 결과 반환
		return "success";
	}
	
	// 게시물 삭제 작업
	public String removePost(int postId) {
		BlogPost post = blogRepository.selectBlogPostById(postId);
		if(post == null) {
			return "not exist";
		}
		String imagePath = post.getImagePath();
		if(blogRepository.deletePostById(postId) == 1) {
			FileManager.deleteImage(imagePath);
		}
		blogReplyRepository.deleteReplyByPostId(postId);
		return "success";
	}
}
