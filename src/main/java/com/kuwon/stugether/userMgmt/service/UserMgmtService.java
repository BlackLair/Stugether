package com.kuwon.stugether.userMgmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kuwon.stugether.blog.dto.BlogCategoryDTO;
import com.kuwon.stugether.blog.dto.BlogCategoryDetail;
import com.kuwon.stugether.blog.repository.BlogMemoRepository;
import com.kuwon.stugether.blog.repository.BlogReplyRepository;
import com.kuwon.stugether.blog.service.BlogCategoryService;
import com.kuwon.stugether.common.EncryptUtils;
import com.kuwon.stugether.common.RegExpUtils;
import com.kuwon.stugether.group.common.domain.Group;
import com.kuwon.stugether.group.common.repository.GroupRepository;
import com.kuwon.stugether.group.member.domain.GroupMember;
import com.kuwon.stugether.group.member.repository.GroupMemberRepository;
import com.kuwon.stugether.group.member.service.GroupMemberService;
import com.kuwon.stugether.problemBank.problem.service.ProblemService;
import com.kuwon.stugether.problemBank.workbook.repository.WorkbookFavoriteRepository;
import com.kuwon.stugether.problemBank.workbook.repository.WorkbookRepository;
import com.kuwon.stugether.problemBank.workbook.service.WorkbookService;
import com.kuwon.stugether.user.domain.User;
import com.kuwon.stugether.user.repository.UserRepository;

@Service
public class UserMgmtService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	GroupRepository groupRepository;
	@Autowired
	GroupMemberRepository groupMemberRepository;
	@Autowired
	WorkbookFavoriteRepository workbookFavoriteRepository;
	@Autowired
	WorkbookRepository workbookRepository;
	@Autowired
	BlogReplyRepository blogReplyRepository;
	@Autowired
	BlogMemoRepository blogMemoRepository;
	@Autowired
	BlogCategoryService blogCategoryService;
	@Autowired
	GroupMemberService groupMemberService;
	@Autowired
	ProblemService problemService;
	
	public String editAccount(int userId
							, String password
							, String email
							, String nickname) {
		if(nickname != null) {
			if(userRepository.selectExistByNickname(nickname) == 1
				|| !RegExpUtils.isValid(RegExpUtils.REGEXP_NICKNAME, nickname)) {
				return "failure";
			}
		}
		if(password != null && !RegExpUtils.isValid(RegExpUtils.REGEXP_PASSWORD, password)) {
			return "failure";
		}
		if(email != null && !RegExpUtils.isValid(RegExpUtils.REGEXP_EMAIL, email)) {
			return "failure";
		}
		if(userRepository.updateUser(userId, EncryptUtils.sha256(password), email, nickname) != 1) {
			return "failure";
		}
		return "success";
		
	}
	
	public String authPassword(int userId, String password) {
		User user = userRepository.selectById(userId);
		String encryptPassword = EncryptUtils.sha256(password);
		if(user.getPassword().equals(encryptPassword)) {
			return "success";
		}
		return "failure";
	}
	
	// 회원 탈퇴
	@Transactional
	public String resignAccount(int userId) throws Exception {
		List<GroupMember> groupMemberList = groupMemberRepository.selectGroupIdByUserId(userId);
		for(GroupMember groupMember : groupMemberList) {
			Group group = groupRepository.selectGroupById(groupMember.getGroupId());
			if(group.getUserId() == userId) { // 그룹장인 그룹 존재 시
				return "group master";
			}
		}
		for(GroupMember groupMember : groupMemberList) {
			groupMemberService.leaveGroup(groupMember.getGroupId(), userId, null); // 모든 그룹 탈퇴 ( 게시물, 댓글 삭제됨 )
		}
		BlogCategoryDTO blogCategoryDTO = blogCategoryService.getBlogCategoryList(userId);
		for(BlogCategoryDetail category : blogCategoryDTO.getBlogCategoryDetailList()) {
			if(blogCategoryService.removeCategory(userId, category.getId(), true).equals("failure")){ // 모든 블로그 카테고리 삭제 (게시물, 댓글 삭제됨)
				throw new Exception();
			}
		}
		blogMemoRepository.deleteBlogMemoByUserId(userId);					// 블로그 모든 메모 삭제
		blogReplyRepository.deleteReplyByUserId(userId); 					// 타인 블로그에 작성한 모든 댓글 삭제
		problemService.removeProblem(userId);								// 제작한 모든 문제 삭제
		workbookFavoriteRepository.deleteWorkbookFavoriteByUserId(userId);	// 문제집 즐겨찾기 항목 모두 삭제
		workbookRepository.deleteWorkbookByUserId(userId);					// 제작한 문제집 모두 삭제
		userRepository.deleteUser(userId);									// 회원 정보 삭제
		return "success";
	}
	
}
