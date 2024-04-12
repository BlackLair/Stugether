package com.kuwon.stugether.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuwon.stugether.blog.repository.BlogRepository;
import com.kuwon.stugether.common.EncryptUtils;
import com.kuwon.stugether.common.RegExpUtils;
import com.kuwon.stugether.user.domain.User;
import com.kuwon.stugether.user.dto.UserDTO;
import com.kuwon.stugether.user.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	BlogRepository blogRepository;
	
	// 중복 아이디 검사
	public boolean checkDuplicatedId(String loginId) {
		int count = userRepository.selectExistByLoginId(loginId);
		if(count > 0) {
			return true;
		}
		return false;
	}
	
	// 중복 닉네임 검사
	public boolean checkDuplicatedNickname(String nickname) {
		int count = userRepository.selectExistByNickname(nickname);
		if(count > 0) {
			return true;
		}
		return false;
	}
	
	// 회원 가입 시도
	public int join(String loginId, String password, String nickname, String email) {
		if(RegExpUtils.isValid(RegExpUtils.REGEXP_ID, loginId)
				&& RegExpUtils.isValid(RegExpUtils.REGEXP_PASSWORD, password)
				&& RegExpUtils.isValid(RegExpUtils.REGEXP_NICKNAME, nickname)
				&& RegExpUtils.isValid(RegExpUtils.REGEXP_EMAIL, email)) {
			if(!checkDuplicatedId(loginId) && !checkDuplicatedNickname(nickname)) {
				String encryptPassword = EncryptUtils.sha256(password);
				User user = new User();
				user.setLoginId(loginId);
				user.setNickname(nickname);
				user.setPassword(encryptPassword);
				user.setEmail(email);
				if(userRepository.insertUser(user) == 1);
				return blogRepository.insertCategory(user.getId(), "기본 카테고리");
			}
		}
		return 0;
	}
	
	// 로그인 시도
	public User getUser(String loginId, String password) {
		String encryptPassword = EncryptUtils.sha256(password);
		return userRepository.selectByLoginIdAndPassword(loginId, encryptPassword);
	}
	
	// 유저 정보 반환
	public UserDTO getUser(int userId) {
		User user = userRepository.selectById(userId);
		UserDTO userDTO = new UserDTO(user);
		return userDTO;
	}
}
