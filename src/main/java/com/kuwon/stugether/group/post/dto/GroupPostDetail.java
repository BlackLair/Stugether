package com.kuwon.stugether.group.post.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupPostDetail {
	private int id;
	private int groupId;
	private int groupCategoryId;
	private String groupCategoryName;
	private int userId;
	private String userNickname;
	private String title;
	private String content;
	private String imagePath;
	private Date createdAt;
	private Date updatedAt;
}
