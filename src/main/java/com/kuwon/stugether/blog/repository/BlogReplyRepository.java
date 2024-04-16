package com.kuwon.stugether.blog.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kuwon.stugether.blog.domain.BlogReply;

@Mapper
public interface BlogReplyRepository {
	public int insertReply(@Param("postId") int postId
						, @Param("userId") int userId
						, @Param("content") String content);
	
	public List<BlogReply> selectReplyByPostId(@Param("postId") int postId);
	
	public int deleteReply(@Param("replyId") int replyId
						, @Param("userId") int userId);
	public int deleteReplyByPostId(@Param("postId") int postId);
}
