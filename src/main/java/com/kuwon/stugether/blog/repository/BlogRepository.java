package com.kuwon.stugether.blog.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kuwon.stugether.blog.domain.BlogCategory;

@Mapper
public interface BlogRepository {
	public List<BlogCategory> selectBlogCategoryList(@Param("userId") int userId);
	public BlogCategory selectBlogCategory(@Param("userId") int userId
										, @Param("categoryId") int categoryId);
	public int insertPost(@Param("userId") int userId
						, @Param("categoryId") int categoryId
						, @Param("title") String title
						, @Param("content") String content);
}
