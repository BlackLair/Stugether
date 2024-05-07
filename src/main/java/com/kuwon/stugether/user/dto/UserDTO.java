package com.kuwon.stugether.user.dto;

import com.kuwon.stugether.user.domain.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	private int id;
	private String nickname;
	private String email;
	private String loginId;
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.nickname = user.getNickname();
		this.email = user.getEmail();
		this.loginId = user.getLoginId();
	}
}
