package com.kuwon.stugether.group.category.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuwon.stugether.group.category.domain.GroupCategory;
import com.kuwon.stugether.group.category.dto.GroupCategoryInfo;
import com.kuwon.stugether.group.category.repository.GroupCategoryRepository;
import com.kuwon.stugether.group.common.domain.Group;
import com.kuwon.stugether.group.common.repository.GroupRepository;
import com.kuwon.stugether.group.post.repository.GroupPostRepository;
import com.kuwon.stugether.group.post.service.GroupPostService;
import com.kuwon.stugether.group.reply.repository.GroupReplyRepository;
import com.kuwon.stugether.user.repository.UserRepository;

@Service
public class GroupCategoryService {
	@Autowired
	GroupCategoryRepository groupCategoryRepository;
	@Autowired
	GroupPostRepository groupPostRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	GroupRepository groupRepository;
	@Autowired
	GroupReplyRepository groupReplyRepository;
	@Autowired
	GroupPostService groupPostService;
	
	// 카테고리 목록 가져오기
	public List<GroupCategoryInfo> getCategoryInfoList(int groupId){
		List<GroupCategory> groupCategoryList = groupCategoryRepository.selectCategoryByGroupId(groupId);
		List<GroupCategoryInfo> groupCategoryInfoList = new ArrayList<>();
		for(GroupCategory groupCategory : groupCategoryList) {
			GroupCategoryInfo groupCategoryInfo = generateGroupCategoryInfo(groupCategory);
			groupCategoryInfoList.add(groupCategoryInfo);
		}
		return groupCategoryInfoList;
	}
	
	// 카테고리 생성
	public String addCategory(int groupId, String name) {
		Group group = groupRepository.selectGroupById(groupId);
		if(groupCategoryRepository.selectExistCategory(groupId, name) == 1) {
			return "duplicated";
		}
		if(groupCategoryRepository.insertCategory(groupId, name) == 1) {
			return "success";
		}
		return "failure";
	}
	
	// 카테고리 제거
	public String removeCategory(int groupId, int categoryId) {
		GroupCategory groupCategory = groupCategoryRepository.selectCategoryById(categoryId);
		if(groupCategory == null || groupCategory.getName().equals("자유게시판")) {
			return "failure";
		}
		List<Integer> postIdList = groupPostRepository.selectAllListByCategoryId(groupId, categoryId);
		for(int postId : postIdList) {
			groupPostService.removeGroupPost(groupId, postId);
		}
		groupCategoryRepository.deleteCategory(categoryId);
		return "success";
	}
	
	
	/**
	 * Create a DTO of GroupCategoryInfo using the GroupCategory object.
	 * GroupCategoryInfo contains the number of posts in the category.
	 * @param
	 * @return GroupCategoryInfo
	 */
	private GroupCategoryInfo generateGroupCategoryInfo(GroupCategory groupCategory) {
		GroupCategoryInfo groupCategoryInfo = new GroupCategoryInfo();
		groupCategoryInfo.setId(groupCategory.getId());
		groupCategoryInfo.setName(groupCategory.getName());
		groupCategoryInfo.setGroupId(groupCategory.getGroupId());
		groupCategoryInfo.setCreatedAt(groupCategory.getCreatedAt());
		groupCategoryInfo.setUpdatedAt(groupCategory.getUpdatedAt());
		int postCount = groupPostRepository.selectPostCountByGroupIdAndCategoryId(groupCategory.getGroupId(), groupCategory.getId());
		groupCategoryInfo.setPostCount(postCount);
		return groupCategoryInfo;
	}
	
	
	/**
	 * Get the total number of posts in the group.
	 * @param groupCategoryInfoList
	 * @return
	 */
	public int getTotalPostCountByGroupCategoryInfoList(List<GroupCategoryInfo> groupCategoryInfoList) {
		int count = 0;
		for(GroupCategoryInfo groupCategoryInfo : groupCategoryInfoList) {
			count += groupCategoryInfo.getPostCount();
		}
		return count;
	}
	
	/**
	 * Get the total number of posts in the category.
	 * @param categoryId
	 * @param groupCategoryInfoList
	 * @return
	 */
	public int getCurrentCategoryPostCount(int categoryId, List<GroupCategoryInfo> groupCategoryInfoList) {
		for(GroupCategoryInfo groupCategoryInfo : groupCategoryInfoList) {
			if(groupCategoryInfo.getId() == categoryId)
				return groupCategoryInfo.getPostCount();
		}
		return 0;
	}
	
	/**
	 * Get a category name by categoryId.
	 * @param categoryId
	 * @return
	 */
	public String getCategoryName(int categoryId) {
		GroupCategory category = groupCategoryRepository.selectCategoryById(categoryId);
		return category.getName();
	}
	
	
}
