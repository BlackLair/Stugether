package com.kuwon.stugether.group.reply.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kuwon.stugether.group.reply.domain.GroupReply;

@Mapper
public interface GroupReplyRepository {
	public int insertReply(@Param("postId") int postId
						, @Param("userId") int userId
						, @Param("content") String content
						, @Param("groupCategoryId") int groupCategoryId);
	
	public List<GroupReply> selectReplyList(@Param("postId") int postId);
	
	public int deleteReply(@Param("replyId") int replyId
						, @Param("userId") int userId);
	
	public int deleteReplyByGroupPostId(@Param("postId") int postId);
	
	public int deleteReplyByGroupCategoryId(@Param("groupCategoryId") int groupCategoryId);
}
