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
	
}
