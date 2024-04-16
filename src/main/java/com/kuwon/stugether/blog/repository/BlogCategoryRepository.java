package com.kuwon.stugether.blog.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kuwon.stugether.blog.domain.BlogCategory;

@Mapper
public interface BlogCategoryRepository {
	public List<BlogCategory> selectBlogCategoryList(@Param("userId") int userId);
	public BlogCategory selectBlogCategory(@Param("userId") int userId
			, @Param("categoryId") int categoryId);
	public int insertBlogCategory(@Param("userId") int userId
			, @Param("name") String name);
	public String selectBlogCategoryNameById(@Param("categoryId") int categoryId);
	public int selectBlogCategoryByUserIdAndName(@Param("userId") int userId
											, @Param("name") String name);
	public int deleteBlogCategoryById(@Param("userId") int userId
									, @Param("categoryId") int categoryId);
}
