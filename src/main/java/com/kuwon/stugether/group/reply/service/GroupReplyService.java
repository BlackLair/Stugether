package com.kuwon.stugether.group.reply.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.kuwon.stugether.group.post.domain.GroupPost;
import com.kuwon.stugether.group.post.repository.GroupPostRepository;
import com.kuwon.stugether.group.reply.domain.GroupReply;
import com.kuwon.stugether.group.reply.dto.GroupReplyDTO;
import com.kuwon.stugether.group.reply.repository.GroupReplyRepository;
import com.kuwon.stugether.user.domain.User;
import com.kuwon.stugether.user.repository.UserRepository;

@Service
public class GroupReplyService {
	@Autowired
	GroupReplyRepository groupReplyRepository;
	@Autowired
	GroupPostRepository groupPostRepository;
	@Autowired
	UserRepository userRepository;
	
	// 댓글 추가
	public String addReply(int groupId, int userId, int postId, String content) {
		GroupPost post = groupPostRepository.selectPost(groupId, postId);
		if(post == null) {
			return "not exist";
		}
		
		if(groupReplyRepository.insertReply(postId, userId, HtmlUtils.htmlEscape(content), post.getGroupCategoryId(), groupId) == 1) {
			return "success";
		}
		return "failure";
	}
	
	// 댓글 목록 불러오기
	public List<GroupReplyDTO> getReplyList(int postId){
		List<GroupReply> groupReplyList = groupReplyRepository.selectReplyList(postId);
		List<GroupReplyDTO> groupReplyDTOList = new ArrayList<>();
		for(GroupReply groupReply : groupReplyList) {
			GroupReplyDTO groupReplyDTO = new GroupReplyDTO();
			User user = userRepository.selectById(groupReply.getUserId());
			groupReplyDTO.generateDTO(groupReply, user.getNickname());
			groupReplyDTOList.add(groupReplyDTO);
		}
		return groupReplyDTOList;
	}
	
	// 댓글 삭제
	public String removeReply(int replyId, int userId) {
		if(groupReplyRepository.deleteReply(replyId, userId) == 1) {
			return "success";
		}
		return "failure";
	}
	
}
