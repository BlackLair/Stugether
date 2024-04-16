package com.kuwon.stugether.blog.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogReplyDTO {
	private int id;
	private int userId;
	private String userNickname;
	private String content;
	private Date createdAt;
}
