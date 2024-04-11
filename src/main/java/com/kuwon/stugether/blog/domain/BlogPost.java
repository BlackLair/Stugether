package com.kuwon.stugether.blog.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogPost {
	private int id;
	private int userId;
	private int blogCategoryId;
	private String title;
	private String content;
	private Date createdAt;
	private Date updatedAt;
}
