package com.kuwon.stugether.group.common.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.kuwon.stugether.group.category.repository.GroupCategoryRepository;
import com.kuwon.stugether.group.common.domain.Group;
import com.kuwon.stugether.group.common.dto.GroupInfo;
import com.kuwon.stugether.group.common.repository.GroupRepository;
import com.kuwon.stugether.group.member.domain.GroupMember;
import com.kuwon.stugether.group.member.repository.GroupMemberRepository;
import com.kuwon.stugether.user.domain.User;
import com.kuwon.stugether.user.repository.UserRepository;

@Service
public class GroupService {
	@Autowired
	GroupRepository groupRepository;
	@Autowired
	GroupCategoryRepository groupCategoryRepository;
	@Autowired
	GroupMemberRepository groupMemberRepository;
	@Autowired
	UserRepository userRepository;
	
	public boolean checkDuplicatedGroupName(String groupName) {
		if(groupRepository.selectExistGroupName(groupName) > 0) {
			return true;
		}
		return false;
	}
	
	// 그룹 생성
	public String createGroup(Group group, String[] categoryList) {
		if(checkDuplicatedGroupName(group.getGroupName())) {
			return "failure";
		}
		groupRepository.insertGroup(group);
		groupCategoryRepository.insertCategory(group.getId(), "자유게시판");
		for(String category : categoryList) {
			groupCategoryRepository.insertCategory(group.getId(), HtmlUtils.htmlEscape(category));
		}
		groupMemberRepository.insertGroupMember(group.getUserId(), group.getId());
		return "success";
	}
	// 자신이 가입한 그룹 정보 목록
	public List<GroupInfo> getJoinedGroupInfoList(int userId){
		List<GroupInfo> groupInfoList = new ArrayList<>();
		List<GroupMember> groupMemberList = groupMemberRepository.selectGroupIdByUserId(userId);
		for(GroupMember groupMember : groupMemberList) {
			int groupId = groupMember.getGroupId();
			groupInfoList.add(getGroupInfoByGroupId(groupId));
		}
		return groupInfoList;
	}
	// 그룹 검색 결과 목록
	public List<GroupInfo> getSearchedGroupInfoList(String search, int page){
		List<Group> groupList = groupRepository.selectGroupByName(search, (page - 1) * 5);
		List<GroupInfo> groupInfoList = new ArrayList<>();
		for(Group group : groupList) {
			int groupId = group.getId();
			groupInfoList.add(getGroupInfoByGroupId(groupId));
		}
		Collections.sort(groupInfoList, Comparator.reverseOrder());
		return groupInfoList;
	}
	// 그룹 검색 결과 총 그룹 개수
	public int getSearchedGroupCount(String search) {
		return groupRepository.selectGroupCountByName(search);
	}
	
	
	
	// groupId로 GroupInfo 정보 반환
	public GroupInfo getGroupInfoByGroupId(int groupId) {
		GroupInfo groupInfo = new GroupInfo();
		Group group = groupRepository.selectGroupById(groupId);
		groupInfo.setId(group.getId());
		groupInfo.setUserId(group.getUserId());
		groupInfo.setDescription(group.getDescription());
		groupInfo.setMemberCount(groupMemberRepository.selectMemberCountByGroupId(groupId));
		User user = userRepository.selectById(group.getUserId());
		groupInfo.setUserNickname(user.getNickname());
		groupInfo.setGroupName(group.getGroupName());
		return groupInfo;
	}
}
