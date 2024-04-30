package com.kuwon.stugether.group.common.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kuwon.stugether.group.common.domain.Group;

@Mapper
public interface GroupRepository {
	public int selectExistGroupName(@Param("groupName") String groupName);
	public int insertGroup(@Param("group") Group group);
	public Group selectGroupById(@Param("groupId") int groupId);
	
	public List<Group> selectGroupByName(@Param("groupName") String groupName
										, @Param("page") int page);
	public int selectGroupCountByName(@Param("groupName") String groupName);
}
