package com.kuwon.stugether.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuwon.stugether.common.EncryptUtils;
import com.kuwon.stugether.common.RegExpUtils;
import com.kuwon.stugether.user.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public boolean checkDuplicatedId(String loginId) {
		int count = userRepository.selectExistByLoginId(loginId);
		if(count > 0) {
			return true;
		}
		return false;
	}
	
	public boolean checkDuplicatedNickname(String nickname) {
		int count = userRepository.selectExistByNickname(nickname);
		if(count > 0) {
			return true;
		}
		return false;
	}
	
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
}
