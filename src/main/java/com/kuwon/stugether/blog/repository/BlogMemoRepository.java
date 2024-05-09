package com.kuwon.stugether.blog.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kuwon.stugether.blog.domain.BlogMemo;

@Mapper
public interface BlogMemoRepository {
	public List<BlogMemo> selectBlogMemoList(@Param("userId") int userId);
	public int updateBlogMemoOrder(@Param("userId") int userId
								, @Param("memoId") int memoId
								, @Param("order") int order);
	public int insertBlogMemo(@Param("userId") int userId
							, @Param("content") String content);
	
	public int deleteBlogMemo(@Param("memoId") int memoId
							, @Param("userId") int userId);
	
	public int deleteBlogMemoByUserId(@Param("userId") int userId);
}
