package com.kuwon.stugether.group.post.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupPost {
	private int id;
	private int groupId;
	private int groupCategoryId;
	private int userId;
	private String title;
	private String content;
	private String imagePath;
	private Date createdAt;
	private Date updatedAt;
}
