package com.kuwon.stugether.blog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.kuwon.stugether.blog.domain.BlogCategory;
import com.kuwon.stugether.blog.domain.BlogPost;
import com.kuwon.stugether.blog.dto.BlogCategoryDTO;
import com.kuwon.stugether.blog.dto.BlogCategoryDetail;
import com.kuwon.stugether.blog.repository.BlogRepository;
import com.kuwon.stugether.blog.repository.BlogCategoryRepository;

@Service
public class BlogCategoryService {
	@Autowired
	BlogCategoryRepository blogCategoryRepository;
	@Autowired
	BlogRepository blogRepository;
	@Autowired
	BlogService blogService;
	
	// 블로그 카테고리 목록 로드
		public BlogCategoryDTO getBlogCategoryList(int userId){
			List<BlogCategory> blogCategoryList = blogCategoryRepository.selectBlogCategoryList(userId);
			List<BlogCategoryDetail> blogCategoryDetailList = new ArrayList<BlogCategoryDetail>();
			BlogCategoryDTO blogCategoryDTO = new BlogCategoryDTO();
			int allPostCount = 0;
			for(BlogCategory blogCategory : blogCategoryList) {
				BlogCategoryDetail blogCategoryDetail = new BlogCategoryDetail();
				blogCategoryDetail.setId(blogCategory.getId());
				blogCategoryDetail.setName(blogCategory.getName());
				blogCategoryDetail.setPostCount(getBlogCategoryPostCount(blogCategory.getUserId(), blogCategory.getId()));
				blogCategoryDetailList.add(blogCategoryDetail);
				allPostCount += blogCategoryDetail.getPostCount();
			}
			blogCategoryDTO.setBlogCategoryDetailList(blogCategoryDetailList);
			blogCategoryDTO.setAllPostCount(allPostCount);
			return blogCategoryDTO;
		}
		public int getBlogCategoryPostCount(int userId, int categoryId) {
			return blogRepository.selectPostCountByCategory(userId, categoryId);
		}
		
		// 카테고리 이름 가져오기
		public String getBlogCategoryName(int categoryId) {
			return blogCategoryRepository.selectBlogCategoryNameById(categoryId);
		}
		
		// 카테고리 생성
		public String addCategory(int userId, String name) {
			if(blogCategoryRepository.selectBlogCategoryByUserIdAndName(userId, name) == 1) {
				return "already exist";
			}
			if(blogCategoryRepository.insertBlogCategory(userId, HtmlUtils.htmlEscape(name)) == 1) {
				return "success";
			};
			return "failure";
			
		}
		
		public String removeCategory(int userId, int categoryId, boolean removeDefault) {
			BlogCategory blogCategory = blogCategoryRepository.selectBlogCategory(userId, categoryId);
			if(blogCategory != null) {
				if(blogCategory.getName().equals("기본 카테고리") && !removeDefault) {
					return "default category";
				}
				List<BlogPost> postList = blogRepository.selectAllBlogPostByUserIdAndCategoryId(userId, categoryId);
				for(BlogPost post : postList) {
					int postId = post.getId();
					blogService.removePost(postId);
				}
				blogCategoryRepository.deleteBlogCategoryById(userId, categoryId);
				return "success";
			}
			return "failure";
		}
}
