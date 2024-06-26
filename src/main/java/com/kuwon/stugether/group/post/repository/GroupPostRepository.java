package com.kuwon.stugether.group.post.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kuwon.stugether.group.post.domain.GroupPost;

@Mapper
public interface GroupPostRepository {
	public int selectPostCountByGroupIdAndCategoryId(@Param("groupId") int groupId
													, @Param("categoryId") int categoryId);
	
	public List<GroupPost> selectAllPostList(@Param("groupId") int groupId
											, @Param("page") int page);
	public List<GroupPost> selectPostListByCategoryId(@Param("groupId") int groupId
													, @Param("categoryId") int categoryId
													, @Param("page") int page);
	
	public GroupPost selectPost(@Param("groupId") int groupId
							, @Param("postId") int postId);
	public int insertPost(@Param("groupPost") GroupPost groupPost);
	
	public int deletePost(@Param("postId") int postId);
	
	public List<Integer> selectAllListByCategoryId(@Param("groupId") int groupId
												, @Param("groupCategoryId") int groupCategoryId);
	public List<GroupPost> selectPostListByGroupIdAndUserId(@Param("groupId") int groupId
														, @Param("userId") int userId);
	
}
