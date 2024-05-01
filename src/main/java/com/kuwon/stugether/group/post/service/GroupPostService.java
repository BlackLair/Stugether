package com.kuwon.stugether.group.post.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuwon.stugether.group.post.domain.GroupPost;
import com.kuwon.stugether.group.post.dto.GroupPostInfo;
import com.kuwon.stugether.group.post.repository.GroupPostRepository;
import com.kuwon.stugether.user.domain.User;
import com.kuwon.stugether.user.repository.UserRepository;

@Service
public class GroupPostService {
	@Autowired
	GroupPostRepository groupPostRepository;
	@Autowired
	UserRepository userRepository;
	
	public List<GroupPostInfo> getGroupPostInfoList(int groupId, Integer categoryId, int page){
		List<GroupPost> groupPostList = null;
		List<GroupPostInfo> groupPostInfoList = new ArrayList<>();
		if(categoryId == null) {
			groupPostList = groupPostRepository.selectAllPostList(groupId, (page - 1) * 10);
		}else {
			groupPostList = groupPostRepository.selectPostListByCategoryId(groupId, categoryId, (page - 1) * 10);
		}
		for(GroupPost groupPost : groupPostList) {
			GroupPostInfo groupPostInfo = generateGroupPostInfo(groupPost);
			groupPostInfoList.add(groupPostInfo);
		}
		return groupPostInfoList;
	}
	
	public GroupPostInfo generateGroupPostInfo(GroupPost groupPost) {
		GroupPostInfo groupPostInfo = new GroupPostInfo();
		
		groupPostInfo.setId(groupPost.getId());
		groupPostInfo.setUserId(groupPost.getId());
		groupPostInfo.setTitle(groupPost.getTitle());
		groupPostInfo.setGroupId(groupPost.getGroupId());
		groupPostInfo.setGroupCategoryId(groupPost.getGroupCategoryId());
		groupPostInfo.setCreatedAt(groupPost.getCreatedAt());
		
		User user = userRepository.selectById(groupPost.getUserId());
		groupPostInfo.setUserNickname(user.getNickname());
		
		return groupPostInfo;
		
	}
	
}
