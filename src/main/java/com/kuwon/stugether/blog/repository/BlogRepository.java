package com.kuwon.stugether.blog.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kuwon.stugether.blog.domain.BlogCategory;
import com.kuwon.stugether.blog.domain.BlogPost;

@Mapper
public interface BlogRepository {
	public List<BlogCategory> selectBlogCategoryList(@Param("userId") int userId);
	public int selectPostCountByCategory(@Param("userId") int userId, @Param("categoryId") int categoryId);
	public BlogCategory selectBlogCategory(@Param("userId") int userId
										, @Param("categoryId") int categoryId);
	public int insertPost(@Param("post") BlogPost post);
	// 특정 유저의 특정 페이지의 모든 게시글 목록 가져옴
	public List<BlogPost> selectAllBlogPost(@Param("userId") int userId
											, @Param("page") int page);
	
	// 특정 카테고리 특정 페이지의 블로그 게시물 목록 가져옴
	public List<BlogPost> selectBlogPostByCategoryId(@Param("userId") int userId
													, @Param("categoryId") int categoryId
													, @Param("page") int page);
	
	public BlogPost selectBlogPostById(@Param("postId") int postId);
	
	public int insertCategory(@Param("userId") int userId
							, @Param("name") String name);
	
	public String selectCategoryNameById(@Param("categoryId") int categoryId);
}
