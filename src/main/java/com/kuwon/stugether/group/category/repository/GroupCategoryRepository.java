package com.kuwon.stugether.group.category.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GroupCategoryRepository {
	public int insertCategory(@Param("groupId") int groupId
							, @Param("name") String name );
	
}
