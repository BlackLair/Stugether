package com.kuwon.stugether.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuwon.stugether.common.EncryptUtils;
import com.kuwon.stugether.common.RegExpUtils;
import com.kuwon.stugether.user.domain.User;
import com.kuwon.stugether.user.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
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
				return userRepository.insertUser(loginId, encryptPassword, nickname, email);
			}
		}
		return 0;
	}
	
	// 로그인 시도
	public User getUser(String loginId, String password) {
		String encryptPassword = EncryptUtils.sha256(password);
		return userRepository.selectByLoginIdAndPassword(loginId, encryptPassword);
	}
}
