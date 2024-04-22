package com.kuwon.stugether.blog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.kuwon.stugether.blog.domain.BlogReply;
import com.kuwon.stugether.blog.dto.BlogReplyDTO;
import com.kuwon.stugether.blog.repository.BlogReplyRepository;
import com.kuwon.stugether.blog.repository.BlogRepository;
import com.kuwon.stugether.user.domain.User;
import com.kuwon.stugether.user.repository.UserRepository;

@Service
public class BlogReplyService {
	@Autowired
	BlogRepository blogRepository;
	@Autowired
	BlogReplyRepository blogReplyRepository;
	@Autowired
	UserRepository userRepository;
	
	public String addReply(int postId, int userId, String content) {
		if(blogRepository.selectBlogPostById(postId) == null) {
			return "not exist";
		}
		String htmlEscapeContent = HtmlUtils.htmlEscape(content);
		int count = blogReplyRepository.insertReply(postId, userId, htmlEscapeContent);
		if(count == 1) {
			return "success";
		}
		return "failure";
	}
	
	public List<BlogReplyDTO> getReplyList(int postId){
		List<BlogReply> replyList = blogReplyRepository.selectReplyByPostId(postId);
		List<BlogReplyDTO> replyDTOList = new ArrayList<>();
		for(BlogReply reply : replyList) {
			BlogReplyDTO replyDTO = new BlogReplyDTO();
			replyDTO.setId(reply.getId());
			replyDTO.setUserId(reply.getUserId());
			replyDTO.setContent(reply.getContent());
			replyDTO.setCreatedAt(reply.getCreatedAt());
			User user = userRepository.selectById(reply.getUserId());
			replyDTO.setUserNickname(user.getNickname());
			replyDTOList.add(replyDTO);
		}
		return replyDTOList;
	}
	
	public String removeReply(int replyId, int userId) {
		int count = blogReplyRepository.deleteReply(replyId, userId);
		if(count == 1)
			return "success";
		return "failure";
	}
}
