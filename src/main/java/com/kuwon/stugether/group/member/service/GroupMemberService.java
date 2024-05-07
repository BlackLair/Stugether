package com.kuwon.stugether.group.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kuwon.stugether.group.category.repository.GroupCategoryRepository;
import com.kuwon.stugether.group.common.domain.Group;
import com.kuwon.stugether.group.common.repository.GroupRepository;
import com.kuwon.stugether.group.member.domain.GroupMember;
import com.kuwon.stugether.group.member.repository.GroupMemberRepository;
import com.kuwon.stugether.group.post.domain.GroupPost;
import com.kuwon.stugether.group.post.repository.GroupPostRepository;
import com.kuwon.stugether.group.post.service.GroupPostService;
import com.kuwon.stugether.group.reply.repository.GroupReplyRepository;
import com.kuwon.stugether.user.domain.User;
import com.kuwon.stugether.user.repository.UserRepository;

@Service
public class GroupMemberService {
	@Autowired
	GroupMemberRepository groupMemberRepository;
	@Autowired
	GroupRepository groupRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	GroupReplyRepository groupReplyRepository;
	@Autowired
	GroupPostRepository groupPostRepository;
	@Autowired
	GroupPostService groupPostService;
	@Autowired
	GroupCategoryRepository groupCategoryRepository;
	
	// 그룹 가입
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
	
	// 그룹 탈퇴
	@Transactional
	public String leaveGroup(int groupId, int userId, String assignee) {
		Group group = groupRepository.selectGroupById(groupId);
		GroupMember groupMember = groupMemberRepository.selectGroupMember(groupId, userId);
		if(group == null || groupMember == null) {
			return "failure";
		}
		User groupMaster = userRepository.selectById(group.getUserId());
		int assigneeId;
		// 그룹장이 탈퇴하려는 경우
		int groupMemberCount = groupMemberRepository.selectMemberCountByGroupId(groupId);
		if(groupMaster.getId() == userId && groupMemberCount > 1) {
			if(assignee == null) {
				return "assignee not exist";
			}
			User user = userRepository.selectUserByNickName(assignee);
			assigneeId = user.getId();
			if(userId == assigneeId) {
				return "self id";
			}
			int count = groupMemberRepository.selectExistMember(assigneeId, groupId);
			if(count == 0) {
				return "assignee not exist";
			}
			groupRepository.updateGroupMaster(assigneeId, groupId);
		}
		// 탈퇴할 사용자의 모든 댓글, 게시물 삭제
		groupReplyRepository.deleteReplyByUserIdAndGroupId(userId, groupId);
		List<GroupPost> groupPostList = groupPostRepository.selectPostListByGroupIdAndUserId(groupId, userId);
		for(GroupPost groupPost : groupPostList) {
			groupPostService.removeGroupPost(groupId, groupPost.getId());
		}
		
		// 그룹 가입 정보 삭제
		groupMemberRepository.deleteGroupMember(userId, groupId);
		
		// 마지막 인원이 탈퇴하는 경우 그룹 폐쇄
		if(groupMemberCount == 1) {
			groupCategoryRepository.deleteAllCategoryByGroupId(groupId);
			groupRepository.deleteGroupById(groupId);
		}
		
		return "success";
	}
}
