package com.kuwon.stugether.group.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuwon.stugether.group.common.repository.GroupRepository;
import com.kuwon.stugether.group.member.repository.GroupMemberRepository;

@Service
public class GroupMemberService {
	@Autowired
	GroupMemberRepository groupMemberRepository;
	@Autowired
	GroupRepository groupRepository;
	
	public String joinGroup(int groupId, int userId) {
		if(groupMemberRepository.selectExistMember(userId, groupId) > 0) {
			return "joined";
		}
		if(groupRepository.selectGroupById(groupId) != null) {
			if(groupMemberRepository.insertGroupMember(userId, groupId) == 1) {
				return "success";
			}
		}
		return "failure";
	}
}
