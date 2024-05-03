package com.kuwon.stugether.group.post.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuwon.stugether.common.FileManager;
import com.kuwon.stugether.group.category.domain.GroupCategory;
import com.kuwon.stugether.group.category.repository.GroupCategoryRepository;
import com.kuwon.stugether.group.common.domain.Group;
import com.kuwon.stugether.group.common.repository.GroupRepository;
import com.kuwon.stugether.group.post.domain.GroupPost;
import com.kuwon.stugether.group.post.dto.GroupPostDetail;
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
	@Autowired
	GroupCategoryRepository groupCategoryRepository;
	@Autowired
	GroupRepository groupRepository;
	
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
	
	public GroupPostDetail getGroupPostDetail(int groupId, int postId) {
		GroupPost groupPost = groupPostRepository.selectPost(groupId, postId);
		return generateGroupPostDetail(groupPost);
	}
	
	public String uploadGroupPost(int userId, int groupId, int categoryId, String title, String content, String editorToken, GroupPost post) {
		GroupCategory groupCategory = groupCategoryRepository.selectCategoryById(categoryId);
		Group group = groupRepository.selectGroupById(groupId);
		if(group == null || groupCategory == null || groupCategory.getGroupId() != groupId) {
			return "not exist";
		}
		
		// 글에 포함된 image 태그의 src 주소 변경
		String currentTime = System.currentTimeMillis() + "";
		content = content.replaceAll("/temp/" + userId + "_" + editorToken, "/group/" + userId + "_" + currentTime);
		String filePath = FileManager.saveImage(userId, FileManager.TYPE_GROUP, currentTime, editorToken);

		post.setGroupId(groupId);
		post.setGroupCategoryId(categoryId);
		post.setUserId(userId);
		post.setTitle(title);
		post.setContent(content);
		post.setImagePath(filePath);
		if(groupPostRepository.insertPost(post) == 1) {
			return "success";
		}
		return "failure";
	}
	
	
	
	private GroupPostDetail generateGroupPostDetail(GroupPost groupPost) {
		GroupPostDetail groupPostDetail = new GroupPostDetail();
		groupPostDetail.setId(groupPost.getId());
		groupPostDetail.setGroupId(groupPost.getGroupId());
		groupPostDetail.setGroupCategoryId(groupPost.getGroupCategoryId());
		GroupCategory groupCategory = groupCategoryRepository.selectCategoryById(groupPost.getGroupCategoryId());
		groupPostDetail.setGroupCategoryName(groupCategory.getName());
		groupPostDetail.setUserId(groupPost.getUserId());
		User user = userRepository.selectById(groupPost.getUserId());
		groupPostDetail.setUserNickname(user.getNickname());
		groupPostDetail.setTitle(groupPost.getTitle());
		groupPostDetail.setContent(groupPost.getContent());
		groupPostDetail.setImagePath(groupPost.getImagePath());
		groupPostDetail.setCreatedAt(groupPost.getCreatedAt());
		groupPostDetail.setUpdatedAt(groupPost.getUpdatedAt());
		
		return groupPostDetail;
	}
	
	private GroupPostInfo generateGroupPostInfo(GroupPost groupPost) {
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
