package com.kuwon.stugether.blog.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogPostInfo {
	private int id;
	private int userId;
	private String categoryName;
	private int blogCategoryId;
	private String title;
	private String content;
	private Date createdAt;
}
