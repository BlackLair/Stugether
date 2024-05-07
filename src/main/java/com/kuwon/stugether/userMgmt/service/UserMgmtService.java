package com.kuwon.stugether.userMgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuwon.stugether.common.EncryptUtils;
import com.kuwon.stugether.common.RegExpUtils;
import com.kuwon.stugether.user.domain.User;
import com.kuwon.stugether.user.repository.UserRepository;

@Service
public class UserMgmtService {
	@Autowired
	UserRepository userRepository;
	
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
	
}
