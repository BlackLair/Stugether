package com.kuwon.stugether.group.reply.dto;

import java.util.Date;

import com.kuwon.stugether.group.reply.domain.GroupReply;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupReplyDTO {
	private int id;
	private int userId;
	private String userNickname;
	private String content;
	private Date createdAt;
	
	public void generateDTO(GroupReply groupReply, String userNickname) {
		this.id = groupReply.getId();
		this.userId = groupReply.getUserId();
		this.content = groupReply.getContent();
		this.createdAt = groupReply.getCreatedAt();
		this.userNickname = userNickname;
	}
}
