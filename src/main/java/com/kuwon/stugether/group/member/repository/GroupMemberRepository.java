package com.kuwon.stugether.group.member.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kuwon.stugether.group.member.domain.GroupMember;

@Mapper
public interface GroupMemberRepository {
	public List<GroupMember> selectGroupIdByUserId(@Param("userId") int userId);
	public int selectMemberCountByGroupId(@Param("groupId") int groupId);
	public int insertGroupMember(@Param("userId") int userId
								, @Param("groupId") int groupId);
}
