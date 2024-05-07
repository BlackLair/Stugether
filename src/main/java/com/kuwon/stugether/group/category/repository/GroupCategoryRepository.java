package com.kuwon.stugether.group.category.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kuwon.stugether.group.category.domain.GroupCategory;

@Mapper
public interface GroupCategoryRepository {
	public int insertCategory(@Param("groupId") int groupId
							, @Param("name") String name );
	public List<GroupCategory> selectCategoryByGroupId(@Param("groupId") int groupId);
	
	public GroupCategory selectCategoryById(@Param("categoryId") int categoryId);
	
	public int selectExistCategory(@Param("groupId") int groupId
								, @Param("name") String name);
	public int deleteCategory(@Param("categoryId") int categoryId);
	
	public int deleteAllCategoryByGroupId(@Param("groupId") int groupId);
}
