package com.kuwon.stugether.blog.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogReply {
	private int id;
	private int blogPostId;
	private int userId;
	private String content;
	private Date createdAt;
	private Date updatedAt;
}
