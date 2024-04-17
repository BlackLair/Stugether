package com.kuwon.stugether.blog.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogMemo {
	private int id;
	private int userId;
	private String content;
	private int order;
	private Date createdAt;
	private Date updatedAt;
}
